package com.f22labs.instalikefragmenttransaction.activities;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
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
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class activity_cadastro extends AppCompatActivity {

    //region Spinner Variaveis
    private Spinner spinner2;
    private ArrayList<String> students2;

    ArrayList<String> ids2;
    private JSONArray result2;
    static String id_spinner_cidade;
    //endregion

    //region Spinner Variaveis
    private Spinner spinner;
    private ArrayList<String> students;

    ArrayList<String> ids;
    private JSONArray result;
    static String id_spinner_estado = "1";
    //endregion

    EditText nome,email,senha, data;

    Button cadastrar;

    InputStream a;
    String recebido;

    //region Spinner
    private void getData(){
        StringRequest stringRequest = new StringRequest("http://premiumcontrol.com.br/NakasoneSoftapp/select/select_estado.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject j = null;
                        try {
                            j = new JSONObject(response);
                            result = j.getJSONArray("result");
                            getStudents(result);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void getStudents(JSONArray j){
        for(int i=0;i<j.length();i++)
        {
            try
            {
                JSONObject json = j.getJSONObject(i);
                students.add(json.getString("nome"));
                ids.add(json.getString("id"));
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
        }

        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, students));

    }


    //endregion

    //region Spinner2
    private void getData2(){
        StringRequest stringRequest = new StringRequest("http://premiumcontrol.com.br/NakasoneSoftapp/select/select_cidade.php?estado="+id_spinner_estado+"",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject j = null;
                        try {
                            j = new JSONObject(response);
                            result2 = j.getJSONArray("result");
                            getStudents2(result2);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void getStudents2(JSONArray j){
        for(int i=0;i<j.length();i++)
        {
            try
            {
                JSONObject json = j.getJSONObject(i);
                students2.add(json.getString("nome"));
                ids2.add(json.getString("id"));
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
        }

        spinner2.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, students2));

    }


    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_cadastro);
        cadastrar = (Button) findViewById(R.id.cadastrar);
        nome = (EditText)findViewById(R.id.nome);
        email = (EditText)findViewById(R.id.email);
        senha = (EditText)findViewById(R.id.senha);
        data = (EditText)findViewById(R.id.data);
        spinner = (Spinner)findViewById(R.id.spinner_estado);
        spinner2 = (Spinner)findViewById(R.id.spinner_cidade);
        students = new ArrayList<String>();
        ids = new ArrayList<String>();
        students2 = new ArrayList<String>();
        ids2 = new ArrayList<String>();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {

                id_spinner_estado = ids.get(position);
                students2 = new ArrayList<String>();
                //ids2 = new ArrayList<String>();
                getData2();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
                ids.get(spinner.getSelectedItemPosition());

            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {

                id_spinner_cidade = ids2.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                ids2.get(spinner2.getSelectedItemPosition());
            }
        });

        getData();
        getData2();

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert();

            }
        });

    }



    public void insert(){

        String  nome_cliente = nome.getText().toString();
        String  email_cliente = email.getText().toString();
        String  senha_cliente = senha.getText().toString();
        String  data_cliente = data.getText().toString();
        String  cidade_cliente = spinner2.getSelectedItem().toString();
        String  estado_cliente = spinner.getSelectedItem().toString();



        insertToDatabase(nome_cliente, email_cliente, senha_cliente, data_cliente, cidade_cliente, estado_cliente);

    }






    private void insertToDatabase(String nome_cliente, String email_cliente,String senha_cliente,String data_cliente, String cidade_cliente, String estado_cliente){
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {
                String paramnome_cliente = params[0];
                String paramemail_cliente = params[1];
                String paramsenha_cliente = params[2];
                String paramdata_cliente = params[3];
                String paramcidade_cliente = params[4];
                String paramestado_cliente = params[5];


                //InputStream is = null;

                String  nome_cliente = nome.getText().toString();
                String  email_cliente = email.getText().toString();
                String  senha_cliente = senha.getText().toString();
                String  data_cliente = data.getText().toString();
                String  cidade_cliente = spinner2.getSelectedItem().toString();
                String  estado_cliente = spinner.getSelectedItem().toString();

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("nome_cliente", nome_cliente));
                nameValuePairs.add(new BasicNameValuePair("email_cliente", email_cliente));
                nameValuePairs.add(new BasicNameValuePair("senha_cliente", senha_cliente));
                nameValuePairs.add(new BasicNameValuePair("data_cliente", data_cliente));
                nameValuePairs.add(new BasicNameValuePair("cidade_cliente", cidade_cliente));
                nameValuePairs.add(new BasicNameValuePair("estado_cliente", estado_cliente));


                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "http://premiumcontrol.com.br/NakasoneSoftapp/cadastrar_cliente.php");
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                    HttpResponse response = httpClient.execute(httpPost);

                    HttpEntity entity = response.getEntity();

                    String inputLine ;
                    BufferedReader br = new BufferedReader(new InputStreamReader(entity.getContent()));
                    try
                    {
                        while ((inputLine = br.readLine()) != null)
                        {

                            System.out.println(inputLine);

                            recebido = inputLine;

                            Static.setLogin(Integer.parseInt(inputLine.trim().toString()));


                        }
                        br.close();
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }



                } catch (ClientProtocolException e) {

                } catch (IOException e) {

                }
                return recebido;
            }

            @Override
            protected void onPostExecute(String result)
            {
                super.onPostExecute(result);

                if(Static.getLogin() == 0)
                {
                    Toast.makeText(getApplicationContext(), "Cliente já cadastrado", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Cadastrado com sucesso", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), activity_login.class );
                    startActivity(intent);
                }

            }



        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(nome_cliente, email_cliente, senha_cliente, data_cliente, cidade_cliente, estado_cliente);
    }






}
