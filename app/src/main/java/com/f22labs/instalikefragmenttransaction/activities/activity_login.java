package com.f22labs.instalikefragmenttransaction.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.f22labs.instalikefragmenttransaction.R;
import com.f22labs.instalikefragmenttransaction.utils.Static;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class activity_login extends AppCompatActivity {

    EditText email, senha;
    Button entrar;
    TextView cadastrar, recuperar;
    String resposta;
    public static final String PREFS_NAME = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SharedPreferences settings = getApplicationContext().getSharedPreferences(PREFS_NAME, getApplicationContext().MODE_PRIVATE);

        String login = settings.getString("0", "");

        if(login != "")
        {
            Static.setId_cliente(Integer.parseInt(login));
        }
        if(Static.getId_cliente() == 0)
        {

        }
        else
        {
            if(Static.getId_cliente() == 1){
                startActivity(new Intent(getApplicationContext(), AdminActivity.class));
                finish();
            }
            else{
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        }

        email = (EditText)findViewById(R.id.email);
        senha = (EditText)findViewById(R.id.senha);
        entrar = (Button)findViewById(R.id.entrar);
        cadastrar = (TextView)findViewById(R.id.cadastrar);
        recuperar = (TextView)findViewById(R.id.recuperar);
        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Logpes();
            }
        });

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), activity_cadastro.class );
                startActivity(intent);
            }
        });



        recuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), activity_senha.class );
                startActivity(intent);
            }
        });



    }

    //region Login Normal
    public void Logpes()
    {
        String login = email.getText().toString();
        String senhas = senha.getText().toString();

        LogpesToDatabase(login, senhas);
    }

    private void LogpesToDatabase(String login, String senhas){
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {
                String paramUsername = params[0];
                String paramAddress = params[1];


                //InputStream is = null;

                String login = email.getText().toString();
                String senhas = senha.getText().toString();

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("email_cliente", login));
                nameValuePairs.add(new BasicNameValuePair("senha_cliente", senhas));


                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "http://premiumcontrol.com.br/NakasoneSoftapp/Testando123.php");
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                    HttpResponse response = httpClient.execute(httpPost);

                    HttpEntity entity = response.getEntity();
                    BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

                    StringBuffer sb = new StringBuffer("");
                    String line="";

                    while ((line = in.readLine()) != null) {
                        sb.append(line);
                        break;
                    }
                    in.close();
                    resposta = sb.toString();
                    Log.d("TAG", resposta);
                    return sb.toString();

                    //is = entity.getContent();


                } catch (ClientProtocolException e) {

                } catch (IOException e) {

                }
                return "success";


            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);


                final ProgressDialog dialog =
                        new ProgressDialog(activity_login.this);
                dialog.setMessage("Enviando dados... aguarde");
                dialog.setIndeterminate(false);
                dialog.setCanceledOnTouchOutside(true);
                dialog.setCancelable(true);
                dialog.show();

                if(Integer.parseInt(result) != 0) {

                    //Intent tela = new Intent(LoginA.this, Testefundo1.class);startActivity(tela);
                    Toast.makeText(getApplicationContext(), "Logado com sucesso", Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                    // finish();
                    //ID.setId(Integer.parseInt(result));
                    Static.setId_cliente(Integer.parseInt(resposta));

                    if(Static.getId_cliente() == 1)
                    {
                        //startActivity(new Intent(getApplicationContext(), AdminActivity.class));
                        startActivity(new Intent(getApplicationContext(), AdminActivity.class));
                        finish();
                    }
                    else{
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                    }




                    SharedPreferences settings = getApplicationContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

                    SharedPreferences.Editor editor = settings.edit();
                    editor.putString("0", resposta);

                    //Confirma a gravação dos dados
                    editor.commit();

                }
                else {Toast.makeText(getApplicationContext(), "LoginA e/ou senha incorretos", Toast.LENGTH_LONG).show();dialog.cancel();}






            }


        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(login, senhas);

    }
    //endregion



}
