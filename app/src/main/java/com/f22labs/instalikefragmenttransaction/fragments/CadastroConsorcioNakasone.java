package com.f22labs.instalikefragmenttransaction.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Config;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.f22labs.instalikefragmenttransaction.R;
import com.f22labs.instalikefragmenttransaction.activities.MainActivity;
import com.f22labs.instalikefragmenttransaction.utils.MaskEditUtil;
import com.f22labs.instalikefragmenttransaction.utils.MoneyTextWatcher;
import com.f22labs.instalikefragmenttransaction.utils.Static;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.ButterKnife;


public class CadastroConsorcioNakasone extends BaseFragment
{
    EditText descricaoconsorcio,contadoconsorcio,valorconsorcio,pagamentoconsorcio,dataconsorcio;

    Button salvarconsorcio;

    String resposta;

    //region spinner variaveis
    private Spinner spinner;
    private ArrayList<String> students;

    private ArrayList<String> ids;
    private JSONArray result;
    static String id_spinner;
//endregion

    //region spinner variaveis 2
    private Spinner spinner2;
    private ArrayList<String> students2;

    private ArrayList<String> ids2;
    private JSONArray result2;
    static String id_spinner2;
    //endregion

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void insert(){

        String  descricao_consorcio = descricaoconsorcio.getText().toString();
        String  valor_consorcio = valorconsorcio.getText().toString();
        String  conta_consorcio = id_spinner2.toString();
        String  comofoipago_consorcio = id_spinner.toString();
        String  data_consorcio = dataconsorcio.getText().toString();
        String  id_cliente = String.valueOf(Static.getId_cliente());



        insertToDatabase(descricao_consorcio, valor_consorcio, conta_consorcio, comofoipago_consorcio, data_consorcio, id_cliente);
    }

    private void insertToDatabase(String descricao_consorcio, String valor_consorcio,String conta_consorcio,String comofoipago_consorcio, String data_consorcio, String id_cliente){
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {
                String paramdescricao_consorcio = params[0];
                String paramvalor_consorcio = params[1];
                String paramconta_consorcio = params[2];
                String paramcomofoipago_consorcio = params[3];
                String paramdata_consorcio = params[4];
                String paramid_cliente = params[5];

                //InputStream is = null;

                String  descricao_consorcio = descricaoconsorcio.getText().toString();
                String  valor_consorcio = valorconsorcio.getText().toString();
                String  conta_consorcio = id_spinner2.toString();
                String  comofoipago_consorcio = id_spinner.toString();
                String  data_consorcio = dataconsorcio.getText().toString();
                String  id_cliente = String.valueOf(Static.getId_cliente());


                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("descricao_consorcio", descricao_consorcio));
                nameValuePairs.add(new BasicNameValuePair("valor_consorcio", valor_consorcio));
                nameValuePairs.add(new BasicNameValuePair("conta_consorcio", conta_consorcio));
                nameValuePairs.add(new BasicNameValuePair("comofoipago_consorcio", comofoipago_consorcio));
                nameValuePairs.add(new BasicNameValuePair("data_consorcio", data_consorcio));
                nameValuePairs.add(new BasicNameValuePair("id_cliente", id_cliente));


                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "http://premiumcontrol.com.br/NakasoneSoftapp/cadastro_consorcio_nakasone.php");
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
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                Toast.makeText(getActivity(), result, Toast.LENGTH_LONG).show();
                UltimoID();
                //TextView textViewResult = (TextView) findViewById(R.id.textViewResult);
                // textViewResult.setText("Inserted");
            }

        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(descricao_consorcio, valor_consorcio, conta_consorcio, comofoipago_consorcio, data_consorcio, id_cliente);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_cadastro_consorcio, container, false);

        //region FindViews
        descricaoconsorcio = (EditText) view.findViewById(R.id.descricaoconsorcio);
        contadoconsorcio = (EditText) view.findViewById(R.id.contadoconsorcio);
        valorconsorcio = (EditText) view.findViewById(R.id.valorconsorcio);
        pagamentoconsorcio = (EditText) view.findViewById(R.id.pagamentoconsorcio);
        dataconsorcio = (EditText) view.findViewById(R.id.dataconsorcio);
        salvarconsorcio = (Button) view.findViewById(R.id.rsalvarconsorcio);
        //endregion

        //region declarar variaveis spinner
        students = new ArrayList<String>();
        ids = new ArrayList<String>();
        spinner = (Spinner) view.findViewById(R.id.spinner_consorcio1);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                id_spinner = ids.get(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                ids.get(spinner.getSelectedItemPosition());

            }
        });
        //endregion

        //region variaveis spinner 2
        students2 = new ArrayList<String>();
        ids2 = new ArrayList<String>();
        spinner2 = (Spinner) view.findViewById(R.id.spinner_consorcio2);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                id_spinner2 = ids2.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                ids2.get(spinner2.getSelectedItemPosition());
            }
        });
        //endregion

        //region Máscaras
        dataconsorcio.addTextChangedListener(MaskEditUtil.mask(dataconsorcio, MaskEditUtil.FORMAT_DATE));
        valorconsorcio.addTextChangedListener(new MoneyTextWatcher(valorconsorcio));
        //endregion

        //region Clique do Botão
        salvarconsorcio.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                insert();

            }
        });
        //endregion

        //region Toolbar
        ButterKnife.bind(this, view);

        ( (MainActivity)getActivity()).updateToolbarTitle("Lançamento de consórcio");
        //endregion

        //region CHAMAR MÉTODOS
        getData();
        getData2();
        //endregion

        return view;
    }

    //region Spinner
    private void getData(){
        StringRequest stringRequest = new StringRequest("http://premiumcontrol.com.br/NakasoneSoftapp/select/select_grupos.php",
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
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

    private void getStudents(JSONArray j){

        for(int i=0;i<j.length();i++){
            try {
                JSONObject json = j.getJSONObject(i);
                students.add(json.getString("nome_grupo"));
                ids.add(json.getString("id_grupo"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        spinner.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, students));
    }

    //endregion

    //region Spinner2
    private void getData2(){
        StringRequest stringRequest = new StringRequest("http://premiumcontrol.com.br/NakasoneSoftapp/teste.php?id_cliente="+Static.getId_cliente()+"",
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
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

    private void getStudents2(JSONArray j){

        for(int i=0;i<j.length();i++)
        {
            try
            {
                JSONObject json = j.getJSONObject(i);
                students2.add(json.getString("nome_conta"));
                ids2.add(json.getString("id_conta"));
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
        }

        spinner2.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, students2));

    }


    //endregion




    //region Necessário para o Diário.

    private void UltimoID(){
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {

                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "http://premiumcontrol.com.br/NakasoneSoftapp/select/select_ultimo_consorcio.php");


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
                return resposta;


            }

            @Override
            protected void onPostExecute(String result)
            {
                super.onPostExecute(result);
                try
                {
                    insertDiario();
                }
                catch (Exception e){Toast.makeText(getActivity(),"Por favor, recarregue a página para calcular seus dados", Toast.LENGTH_LONG);}

            }


        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute();

    }


    public void insertDiario()
    {
        String  origem_diario = id_spinner.toString();
        String  destino_diario = id_spinner2.toString();
        String  descricao_diario = descricaoconsorcio.getText().toString();
        String  valor_diario = valorconsorcio.getText().toString();
        String  data_diario = dataconsorcio.getText().toString();
        String  tipo_diario = "Consorcio";
        String  idtipo_diario = resposta;
        String  id_cliente = String.valueOf(Static.getId_cliente());

        insertToDatabaseDiario(origem_diario,destino_diario,descricao_diario,valor_diario,data_diario,tipo_diario,idtipo_diario,id_cliente);
    }

    private void insertToDatabaseDiario(String origem_diario, String destino_diario,String descricao_diario,String valor_diario,String data_diario,String tipo_diario,String idtipo_diario,String id_cliente)
    {
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String>
        {
            @Override
            protected String doInBackground(String... params)
            {
                String paramorigem_diario = params[0];
                String paramdestino_diario = params[1];
                String paramdescricao_diario = params[2];
                String paramvalor_diario = params[3];
                String paramdata_diario = params[4];
                String paramtipo_diario = params[5];
                String paramidtipo_diario = params[6];
                String paramid_cliente = params[7];

                String  origem_diario = id_spinner.toString();
                String  destino_diario = id_spinner2.toString();
                String  descricao_diario = descricaoconsorcio.getText().toString();
                String  valor_diario = valorconsorcio.getText().toString();
                String  data_diario = dataconsorcio.getText().toString();
                String  tipo_diario = "Consorcio";
                String  idtipo_diario = resposta;
                String  id_cliente = String.valueOf(Static.getId_cliente());

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("origem_diario", origem_diario));
                nameValuePairs.add(new BasicNameValuePair("destino_diario", destino_diario));
                nameValuePairs.add(new BasicNameValuePair("descricao_diario", descricao_diario));
                nameValuePairs.add(new BasicNameValuePair("valor_diario", valor_diario));
                nameValuePairs.add(new BasicNameValuePair("data_diario", data_diario));
                nameValuePairs.add(new BasicNameValuePair("tipo_diario", tipo_diario));
                nameValuePairs.add(new BasicNameValuePair("idtipo_diario", idtipo_diario));
                nameValuePairs.add(new BasicNameValuePair("id_cliente", id_cliente));

                try
                {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "http://premiumcontrol.com.br/NakasoneSoftapp/Cadastrar_Diario_Final.php");
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                    HttpResponse response = httpClient.execute(httpPost);

                    HttpEntity entity = response.getEntity();

                    //is = entity.getContent();

                }
                catch (ClientProtocolException e)
                {

                }
                catch (IOException e)
                {

                }
                return "success";
            }

            @Override
            protected void onPostExecute(String result)
            {
                super.onPostExecute(result);
                Toast.makeText(getActivity(), result, Toast.LENGTH_LONG).show();

            }
        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(origem_diario,destino_diario,descricao_diario,valor_diario,data_diario,tipo_diario,idtipo_diario,id_cliente);
    }


    //endregion



}
