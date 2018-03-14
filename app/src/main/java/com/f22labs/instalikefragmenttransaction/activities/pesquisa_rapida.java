package com.f22labs.instalikefragmenttransaction.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class pesquisa_rapida extends AppCompatActivity implements View.OnClickListener
{

    Button face, insta, redes, google, email, indicacao, outros;

     String valor;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisa_rapida);
        face = (Button)findViewById(R.id.face);
        insta = (Button)findViewById(R.id.insta);
        redes = (Button)findViewById(R.id.redes);
        google = (Button)findViewById(R.id.google);
        email = (Button)findViewById(R.id.email);
        indicacao = (Button)findViewById(R.id.indicacao);
        outros = (Button)findViewById(R.id.outros);

        face.setOnClickListener(this);
        insta.setOnClickListener(this);
        redes.setOnClickListener(this);
        google.setOnClickListener(this);
        email.setOnClickListener(this);
        indicacao.setOnClickListener(this);
        outros.setOnClickListener(this);

    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.face:
                valor = face.getText().toString();
                insert();
                break;
            case R.id.insta:
                valor = insta.getText().toString();
                insert();
                break;
            case R.id.redes:
                valor = redes.getText().toString();
                insert();
                break;
            case R.id.google:
                valor = google.getText().toString();
                insert();
                break;
            case R.id.email:
                valor = email.getText().toString();
                insert();
                break;
            case R.id.indicacao:
                valor = indicacao.getText().toString();
                insert();
                break;
            case R.id.outros:
                valor = outros.getText().toString();
                insert();
                break;
        }

    }

    public void insert() {

        String  meio = valor.toString();

        insertToDatabase(meio);

    }


    private void insertToDatabase(String meio){
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {
                String paramvalor= params[0];



                //InputStream is = null;

                String  meio = valor.toString();


                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("meio", meio));


                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "http://premiumcontrol.com.br/NakasoneSoftapp/cadastro_pesquisa.php");
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                    HttpResponse response = httpClient.execute(httpPost);

                    HttpEntity entity = response.getEntity();

                    //is = entity.getContent();

                } catch (ClientProtocolException e) {

                } catch (IOException e) {

                }
                return "success";
            }



            @Override
            protected void onPostExecute(String result)
            {
                super.onPostExecute(result);
                insertContaPadrao();
                Intent intent = new Intent(getApplicationContext(), activity_login.class );
                startActivity(intent);
                //TextView textViewResult = (TextView) findViewById(R.id.textViewResult);
                // textViewResult.setText("Inserted");
            }



        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(meio);
    }


    //region Insert de conta padrão.
    public void insertContaPadrao() {

        String  meio = valor.toString();

        insertToDatabaseContaPadrao();

    }


    private void insertToDatabaseContaPadrao()
    {
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String>
        {
            @Override
            protected String doInBackground(String... params)
            {


                try
                {
                    HttpClient httpClient = new DefaultHttpClient();

                    HttpPost httpPost = new HttpPost("http://premiumcontrol.com.br/NakasoneSoftapp/cadastrar_padrao.php?id_cliente="+ Static.getId_cliente()+"");

                    //httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                    HttpResponse response = httpClient.execute(httpPost);

                    HttpEntity entity = response.getEntity();

                    //is = entity.getContent();

                }

                catch (ClientProtocolException e) {}

                catch (IOException e) {}
                return "success";
            }

            @Override
            protected void onPostExecute(String result) {super.onPostExecute(result);}

        }

        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();

        sendPostReqAsyncTask.execute();
    }


    //endregion
}
