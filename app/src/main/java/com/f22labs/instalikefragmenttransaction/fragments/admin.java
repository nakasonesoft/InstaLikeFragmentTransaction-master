package com.f22labs.instalikefragmenttransaction.fragments;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.f22labs.instalikefragmenttransaction.R;
import com.f22labs.instalikefragmenttransaction.activities.MainActivity;
import com.f22labs.instalikefragmenttransaction.adapters.ConfigRetrieve;
import com.f22labs.instalikefragmenttransaction.utils.Static;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import butterknife.ButterKnife;


public class admin extends BaseFragment{

    ProgressDialog loading;
    TextView usuarios, facebook, instagram, redes, google, email, indicacao, outros;
    String resposta1,resposta2,resposta3,resposta4,resposta5,resposta6,resposta7,resposta8;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_admin, container, false);

        usuarios = (TextView)view.findViewById(R.id.usuarios);
        facebook = (TextView)view.findViewById(R.id.facebook);
        instagram = (TextView)view.findViewById(R.id.instagram);
        redes = (TextView)view.findViewById(R.id.redes);
        google = (TextView)view.findViewById(R.id.google);
        email = (TextView)view.findViewById(R.id.email);
        indicacao = (TextView)view.findViewById(R.id.indicacao);
        outros = (TextView)view.findViewById(R.id.outros);


        ButterKnife.bind(this, view);

        ( (MainActivity)getActivity()).updateToolbarTitle("Administração");
        LogpesToDatabase();
        LogpesToDatabase1();
        LogpesToDatabase3();
        LogpesToDatabase4();
        LogpesToDatabase5();
        LogpesToDatabase6();
        LogpesToDatabase7();
        LogpesToDatabase8();
        return view;
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
                catch (Exception e){Toast.makeText(getActivity(),"Por favor, recarregue a página para calcular seus dados", Toast.LENGTH_LONG);}

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
                catch (Exception e){Toast.makeText(getActivity(),"Por favor, recarregue a página para calcular seus dados", Toast.LENGTH_LONG);}

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
                catch (Exception e){Toast.makeText(getActivity(),"Por favor, recarregue a página para calcular seus dados", Toast.LENGTH_LONG);}

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
                catch (Exception e){Toast.makeText(getActivity(),"Por favor, recarregue a página para calcular seus dados", Toast.LENGTH_LONG);}

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
                catch (Exception e){Toast.makeText(getActivity(),"Por favor, recarregue a página para calcular seus dados", Toast.LENGTH_LONG);}

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
                catch (Exception e){Toast.makeText(getActivity(),"Por favor, recarregue a página para calcular seus dados", Toast.LENGTH_LONG);}

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
                catch (Exception e){Toast.makeText(getActivity(),"Por favor, recarregue a página para calcular seus dados", Toast.LENGTH_LONG);}

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
                catch (Exception e){Toast.makeText(getActivity(),"Por favor, recarregue a página para calcular seus dados", Toast.LENGTH_LONG);}

            }


        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute();

    }
    //endregion


}
