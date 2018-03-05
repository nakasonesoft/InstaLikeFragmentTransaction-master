package com.f22labs.instalikefragmenttransaction.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.f22labs.instalikefragmenttransaction.activities.MainActivity;
import com.f22labs.instalikefragmenttransaction.utils.MaskEditUtil;
import com.f22labs.instalikefragmenttransaction.utils.MoneyTextWatcher;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;


public class CadastroOutrosNakasone extends BaseFragment
{

    EditText descricaoOutros,contadoOutros,valorOutros,contaOutros2,dataOutros;

    String resposta;

    Button salvarOutros;

    //region Spinner Variaveis
    private Spinner spinner;
    private ArrayList<String> students;

    ArrayList<String> ids;
    private JSONArray result;
    static String id_spinner;
    //endregion


    //region Spinner Variaveis2
    private Spinner spinner2;
    private ArrayList<String> students2;

    ArrayList<String> ids2;
    private JSONArray result2;
    static String id_spinner2;
    //endregion

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void insert(){

        String  descricao_outros = descricaoOutros.getText().toString();
        String  id_grupo = id_spinner.toString();
        String  id_grupo2 = id_spinner2.toString();
        String  valor_outros = valorOutros.getText().toString();
        String  data_outros = dataOutros.getText().toString();



        insertToDatabase(descricao_outros, id_grupo, id_grupo2, valor_outros, data_outros);

    }

    private void insertToDatabase(String descricao_outros, String id_grupo,String id_grupo2,String valor_outros, String data_outros){
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {
                String paramdescricao_outros = params[0];
                String paramid_grupo = params[1];
                String paramid_grupo2 = params[2];
                String paramvalor_outros = params[3];
                String paramdata_outros = params[4];


                //InputStream is = null;

                String  descricao_outros = descricaoOutros.getText().toString();
                String  id_grupo = id_spinner.toString();
                String  id_grupo2 = id_spinner2.toString();
                String  valor_outros = valorOutros.getText().toString();
                String  data_outros = dataOutros.getText().toString();

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("descricao_outros", descricao_outros));
                nameValuePairs.add(new BasicNameValuePair("id_grupo", id_grupo));
                nameValuePairs.add(new BasicNameValuePair("id_grupo2", id_grupo2));
                nameValuePairs.add(new BasicNameValuePair("valor_outros", valor_outros));
                nameValuePairs.add(new BasicNameValuePair("data_outros", data_outros));


                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "http://premiumcontrol.com.br/NakasoneSoftapp/cadastro_outros_nakasone.php");
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
        sendPostReqAsyncTask.execute(descricao_outros, id_grupo, id_grupo2, valor_outros, data_outros);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_cadastro_outros, container, false);

        //region FindViews
        descricaoOutros = (EditText) view.findViewById(R.id.descricaoOutros);
        contadoOutros = (EditText) view.findViewById(R.id.contadoOutros);
        valorOutros = (EditText) view.findViewById(R.id.valorOutros);
        contaOutros2 = (EditText) view.findViewById(R.id.contaOutros2);
        dataOutros = (EditText) view.findViewById(R.id.dataOutros);
        salvarOutros = (Button) view.findViewById(R.id.salvarOutros);

        students = new ArrayList<String>();
        ids = new ArrayList<String>();
        spinner = (Spinner) view.findViewById(R.id.spinner_outros);
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

        students2 = new ArrayList<String>();
        ids2 = new ArrayList<String>();
        spinner2 = (Spinner) view.findViewById(R.id.spinner_outros2);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
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
        valorOutros.addTextChangedListener(new MoneyTextWatcher(valorOutros));
        dataOutros.addTextChangedListener(MaskEditUtil.mask(dataOutros, MaskEditUtil.FORMAT_DATE));
        //endregion



        //region Clique do botão
        salvarOutros.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                insert();
            }
        });
        //endregion



        //region Outros
        ButterKnife.bind(this, view);

        ( (MainActivity)getActivity()).updateToolbarTitle("Lançamento de Outros");
     //endregion


        getData();
        getData2();
        return view;
    }

    //region Spinner
    private void getData(){
        StringRequest stringRequest = new StringRequest("http://premiumcontrol.com.br/NakasoneSoftapp/teste.php",
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
        students.add("");
        for(int i=0;i<j.length();i++)
        {
            try
            {
                JSONObject json = j.getJSONObject(i);
                students.add(json.getString("nome_conta"));
                ids.add(json.getString("id_conta"));
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
        }

        spinner.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, students));

    }



    //endregion

    //region Spinner2
    private void getData2(){
        StringRequest stringRequest = new StringRequest("http://premiumcontrol.com.br/NakasoneSoftapp/teste.php",
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
        students2.add("");
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
                            "http://premiumcontrol.com.br/NakasoneSoftapp/select/select_ultimo_outros.php");


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
        String  descricao_diario = descricaoOutros.getText().toString();
        String  valor_diario = valorOutros.getText().toString();
        String  data_diario = dataOutros.getText().toString();
        String  tipo_diario = "Outros";
        String  idtipo_diario = resposta;
        String  id_cliente = "1";

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
                String  descricao_diario = descricaoOutros.getText().toString();
                String  valor_diario = valorOutros.getText().toString();
                String  data_diario = dataOutros.getText().toString();
                String  tipo_diario = "Outros";
                String  idtipo_diario = resposta;
                String  id_cliente = "1";

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
