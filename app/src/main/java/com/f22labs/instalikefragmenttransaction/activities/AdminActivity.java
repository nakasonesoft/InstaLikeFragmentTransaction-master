package com.f22labs.instalikefragmenttransaction.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import butterknife.ButterKnife;

public class AdminActivity extends AppCompatActivity {

    ProgressDialog loading;
    TextView usuarios, facebook, instagram, redes, google, email, indicacao, outros;
    String resposta1,resposta2,resposta3,resposta4,resposta5,resposta6,resposta7,resposta8;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_admin);

            usuarios = (TextView)findViewById(R.id.usuarios);
            facebook = (TextView)findViewById(R.id.facebook);
            instagram = (TextView)findViewById(R.id.instagram);
            redes = (TextView)findViewById(R.id.redes);
            google = (TextView)findViewById(R.id.google);
            email = (TextView)findViewById(R.id.email);
            indicacao = (TextView)findViewById(R.id.indicacao);
            outros = (TextView)findViewById(R.id.outros);

            LogpesToDatabase();
            LogpesToDatabase1();
            LogpesToDatabase3();
            LogpesToDatabase4();
            LogpesToDatabase5();
            LogpesToDatabase6();
            LogpesToDatabase7();
            LogpesToDatabase8();
    }
    //region USUARIOS
    private void LogpesToDatabase(){
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {

                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "http://premiumcontrol.com.br/NakasoneSoftapp/select/usuario.php");


                    HttpResponse response = httpClient.execute(httpPost);

                    HttpEntity entity = response.getEntity();
                    BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

                    StringBuffer sb = new StringBuffer("");
                    String line="";

                    while ((line = in.readLine()) != null) {
                        sb.append(line + "");
                        break;
                    }
                    in.close();
                    resposta1 = sb.toString();
                    Log.d("TAG", resposta1);
                    return sb.toString();

                    //is = entity.getContent();


                } catch (ClientProtocolException e) {

                } catch (IOException e) {

                }
                return resposta1;


            }

            @Override
            protected void onPostExecute(String result)
            {
                super.onPostExecute(result);
                try {

                    usuarios.setText(resposta1);


                }
                catch (Exception e){Toast.makeText(AdminActivity.this,"Por favor, recarregue a página para calcular seus dados", Toast.LENGTH_LONG);}

            }


        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute();

    }
    //endregion

    //region FACEBOOK
    private void LogpesToDatabase1(){
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {

                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "http://premiumcontrol.com.br/NakasoneSoftapp/select/facebook.php");


                    HttpResponse response = httpClient.execute(httpPost);

                    HttpEntity entity = response.getEntity();
                    BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

                    StringBuffer sb = new StringBuffer("");
                    String line="";

                    while ((line = in.readLine()) != null) {
                        sb.append(line + "");
                        break;
                    }
                    in.close();
                    resposta2 = sb.toString();
                    Log.d("TAG", resposta2);
                    return sb.toString();

                    //is = entity.getContent();


                } catch (ClientProtocolException e) {

                } catch (IOException e) {

                }
                return resposta2;


            }

            @Override
            protected void onPostExecute(String result)
            {
                super.onPostExecute(result);
                try {

                    facebook.setText(resposta2);


                }
                catch (Exception e){Toast.makeText(AdminActivity.this,"Por favor, recarregue a página para calcular seus dados", Toast.LENGTH_LONG);}

            }


        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute();

    }
    //endregion

    //region INSTAGRAM
    private void LogpesToDatabase3(){
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {

                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "http://premiumcontrol.com.br/NakasoneSoftapp/select/instagram.php");


                    HttpResponse response = httpClient.execute(httpPost);

                    HttpEntity entity = response.getEntity();
                    BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

                    StringBuffer sb = new StringBuffer("");
                    String line="";

                    while ((line = in.readLine()) != null) {
                        sb.append(line + "");
                        break;
                    }
                    in.close();
                    resposta3 = sb.toString();
                    Log.d("TAG", resposta3);
                    return sb.toString();

                    //is = entity.getContent();


                } catch (ClientProtocolException e) {

                } catch (IOException e) {

                }
                return resposta3;


            }

            @Override
            protected void onPostExecute(String result)
            {
                super.onPostExecute(result);
                try {

                    instagram.setText(resposta3);


                }
                catch (Exception e){Toast.makeText(AdminActivity.this,"Por favor, recarregue a página para calcular seus dados", Toast.LENGTH_LONG);}

            }


        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute();

    }
    //endregion

    //region OUTRAS REDES
    private void LogpesToDatabase4(){
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {

                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "http://premiumcontrol.com.br/NakasoneSoftapp/select/redes.php");


                    HttpResponse response = httpClient.execute(httpPost);

                    HttpEntity entity = response.getEntity();
                    BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

                    StringBuffer sb = new StringBuffer("");
                    String line="";

                    while ((line = in.readLine()) != null) {
                        sb.append(line + "");
                        break;
                    }
                    in.close();
                    resposta4 = sb.toString();
                    Log.d("TAG", resposta4);
                    return sb.toString();

                    //is = entity.getContent();


                } catch (ClientProtocolException e) {

                } catch (IOException e) {

                }
                return resposta4;


            }

            @Override
            protected void onPostExecute(String result)
            {
                super.onPostExecute(result);
                try {

                    redes.setText(resposta4);


                }
                catch (Exception e){Toast.makeText(AdminActivity.this,"Por favor, recarregue a página para calcular seus dados", Toast.LENGTH_LONG);}

            }


        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute();

    }
    //endregion

    //region GOOGLE
    private void LogpesToDatabase5(){
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {

                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "http://premiumcontrol.com.br/NakasoneSoftapp/select/google.php");


                    HttpResponse response = httpClient.execute(httpPost);

                    HttpEntity entity = response.getEntity();
                    BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

                    StringBuffer sb = new StringBuffer("");
                    String line="";

                    while ((line = in.readLine()) != null) {
                        sb.append(line + "");
                        break;
                    }
                    in.close();
                    resposta5 = sb.toString();
                    Log.d("TAG", resposta5);
                    return sb.toString();

                    //is = entity.getContent();


                } catch (ClientProtocolException e) {

                } catch (IOException e) {

                }
                return resposta5;


            }

            @Override
            protected void onPostExecute(String result)
            {
                super.onPostExecute(result);
                try {

                    google.setText(resposta5);


                }
                catch (Exception e){Toast.makeText(AdminActivity.this,"Por favor, recarregue a página para calcular seus dados", Toast.LENGTH_LONG);}

            }


        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute();

    }
    //endregion

    //region E-mail
    private void LogpesToDatabase6(){
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {

                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "http://premiumcontrol.com.br/NakasoneSoftapp/select/email.php");


                    HttpResponse response = httpClient.execute(httpPost);

                    HttpEntity entity = response.getEntity();
                    BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

                    StringBuffer sb = new StringBuffer("");
                    String line="";

                    while ((line = in.readLine()) != null) {
                        sb.append(line + "");
                        break;
                    }
                    in.close();
                    resposta6 = sb.toString();
                    Log.d("TAG", resposta6);
                    return sb.toString();

                    //is = entity.getContent();


                } catch (ClientProtocolException e) {

                } catch (IOException e) {

                }
                return resposta6;


            }

            @Override
            protected void onPostExecute(String result)
            {
                super.onPostExecute(result);
                try {

                    email.setText(resposta6);


                }
                catch (Exception e){Toast.makeText(AdminActivity.this,"Por favor, recarregue a página para calcular seus dados", Toast.LENGTH_LONG);}

            }


        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute();

    }
    //endregion

    //region Indicaçao
    private void LogpesToDatabase7(){
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {

                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "http://premiumcontrol.com.br/NakasoneSoftapp/select/indicacao.php");


                    HttpResponse response = httpClient.execute(httpPost);

                    HttpEntity entity = response.getEntity();
                    BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

                    StringBuffer sb = new StringBuffer("");
                    String line="";

                    while ((line = in.readLine()) != null) {
                        sb.append(line + "");
                        break;
                    }
                    in.close();
                    resposta7 = sb.toString();
                    Log.d("TAG", resposta7);
                    return sb.toString();

                    //is = entity.getContent();


                } catch (ClientProtocolException e) {

                } catch (IOException e) {

                }
                return resposta7;


            }

            @Override
            protected void onPostExecute(String result)
            {
                super.onPostExecute(result);
                try {

                    indicacao.setText(resposta7);


                }
                catch (Exception e){Toast.makeText(AdminActivity.this,"Por favor, recarregue a página para calcular seus dados", Toast.LENGTH_LONG);}

            }


        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute();

    }
    //endregion

    //region Outros
    private void LogpesToDatabase8(){
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {

                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "http://premiumcontrol.com.br/NakasoneSoftapp/select/outros_meios.php");


                    HttpResponse response = httpClient.execute(httpPost);

                    HttpEntity entity = response.getEntity();
                    BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

                    StringBuffer sb = new StringBuffer("");
                    String line="";

                    while ((line = in.readLine()) != null) {
                        sb.append(line + "");
                        break;
                    }
                    in.close();
                    resposta8 = sb.toString();
                    Log.d("TAG", resposta8);
                    return sb.toString();

                    //is = entity.getContent();


                } catch (ClientProtocolException e) {

                } catch (IOException e) {

                }
                return resposta8;


            }

            @Override
            protected void onPostExecute(String result)
            {
                super.onPostExecute(result);
                try {

                    outros.setText(resposta8);


                }
                catch (Exception e){Toast.makeText(AdminActivity.this,"Por favor, recarregue a página para calcular seus dados", Toast.LENGTH_LONG);}

            }


        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute();

    }
    //endregion
}
