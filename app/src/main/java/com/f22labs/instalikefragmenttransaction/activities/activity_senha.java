package com.f22labs.instalikefragmenttransaction.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class activity_senha extends AppCompatActivity {

    EditText email_cliente2, senha_cliente2, data_cliente2, confirmar_senha2;

    Button recuperar;

    String resposta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_senha);

        email_cliente2 = (EditText)findViewById(R.id.email_cliente);
        senha_cliente2 = (EditText)findViewById(R.id.senha_cliente);
        data_cliente2 = (EditText)findViewById(R.id.data_cliente);
        confirmar_senha2 = (EditText)findViewById(R.id.confirmar_senha);
        recuperar = (Button)findViewById(R.id.recuperar);

        recuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateEvento();
            }
        });

    }


    //region Editar Dados
    public void UpdateEvento()
    {

        String email_cliente = email_cliente2.getText().toString();
        String senha_cliente = senha_cliente2.getText().toString();
        String data_cliente = data_cliente2.getText().toString();
        String confirmar_senha = confirmar_senha2.getText().toString();

        UpdateToDatabase(email_cliente,senha_cliente,data_cliente,confirmar_senha);

    }

    private void UpdateToDatabase(String email_cliente, String senha_cliente, String data_cliente, String confirmar_senha) {
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String>
        {
            @Override
            protected String doInBackground(String... params)
            {
                String paramemail_cliente = params[0];
                String paramsenha_cliente = params[1];
                String paramdata_cliente = params[2];
                String paramconfirmar_senha = params[3];

                String email_cliente = email_cliente2.getText().toString();
                String senha_cliente = senha_cliente2.getText().toString();
                String data_cliente = data_cliente2.getText().toString();
                String confirmar_senha = confirmar_senha2.getText().toString();


                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("email_cliente", email_cliente));
                nameValuePairs.add(new BasicNameValuePair("senha_cliente", senha_cliente));
                nameValuePairs.add(new BasicNameValuePair("data_cliente", data_cliente));
                nameValuePairs.add(new BasicNameValuePair("confirmar_senha", confirmar_senha));



                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost("http://premiumcontrol.com.br/NakasoneSoftapp/update/alterar_senha.php");
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
                } catch (ClientProtocolException e) {

                } catch (IOException e) {

                }
                return "success";
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                if (Integer.parseInt(result) == 1) {
                    startActivity(new Intent(getApplicationContext(), activity_login.class));
                    Static.setId_cliente(0);
                    Toast.makeText(activity_senha.this, "Senha alterada com sucesso", Toast.LENGTH_LONG).show();
                } else {
                    if (Integer.parseInt(result) == 2) {
                        Toast.makeText(activity_senha.this, "Senhas diferentes", Toast.LENGTH_LONG).show();
                    }

                    if (Integer.parseInt(result) == 3) {
                        Toast.makeText(activity_senha.this, "Não existe esse nome/email", Toast.LENGTH_LONG).show();
                    }
                }
            }


        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(email_cliente, senha_cliente, data_cliente, confirmar_senha);
    }

    //endregion











}
