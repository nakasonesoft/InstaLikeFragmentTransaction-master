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
import android.widget.ImageView;
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


public class CadastroTransferenciaNakasone extends BaseFragment{
    EditText descricaotransferencia,ondefoitransferencia,valortransferencia,ondeveiotransferencia,datatransferencia;
    Button salvartransferencia;
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

        String  descricao_transferencia = descricaotransferencia.getText().toString();
        String  valor_transferencia = valortransferencia.getText().toString();
        String  praondefoi_transferencia = id_spinner.toString();
        String  contaondeveio_transferencia = id_spinner2.toString();
        String  data_transferencia = datatransferencia.getText().toString();



        insertToDatabase(descricao_transferencia, valor_transferencia, praondefoi_transferencia, contaondeveio_transferencia, data_transferencia);

    }


    private void insertToDatabase(String descricao_transferencia, String valor_transferencia,String praondefoi_transferencia,String contaondeveio_transferencia, String data_transferencia){
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {
                String paramdescricao_transferencia = params[0];
                String paramvalor_transferencia = params[1];
                String parampraondefoi_transferencia = params[2];
                String paramcontaondeveio_transferencia = params[3];
                String paramdata_transferencia = params[4];


                //InputStream is = null;

                String  descricao_transferencia = descricaotransferencia.getText().toString();
                String  valor_transferencia = valortransferencia.getText().toString();
                String  praondefoi_transferencia = id_spinner.toString();
                String  contaondeveio_transferencia = id_spinner2.toString();
                String  data_transferencia = datatransferencia.getText().toString();

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("descricao_transferencia", descricao_transferencia));
                nameValuePairs.add(new BasicNameValuePair("valor_transferencia", valor_transferencia));
                nameValuePairs.add(new BasicNameValuePair("praondefoi_transferencia", praondefoi_transferencia));
                nameValuePairs.add(new BasicNameValuePair("contaondeveio_transferencia", contaondeveio_transferencia));
                nameValuePairs.add(new BasicNameValuePair("data_transferencia", data_transferencia));


                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "http://premiumcontrol.com.br/NakasoneSoftapp/cadastro_transferencia_nakasone.php");
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
        sendPostReqAsyncTask.execute(descricao_transferencia, valor_transferencia, praondefoi_transferencia, contaondeveio_transferencia, data_transferencia);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cadastro_transferencia, container, false);
        //region FindViews
        descricaotransferencia = (EditText) view.findViewById(R.id.descricaotransferencia);
        ondefoitransferencia = (EditText) view.findViewById(R.id.ondefoitransferencia);
        valortransferencia = (EditText) view.findViewById(R.id.valortransferencia);
        ondeveiotransferencia = (EditText) view.findViewById(R.id.ondeveiotransferencia);
        datatransferencia = (EditText) view.findViewById(R.id.datatransferencia);
        salvartransferencia = (Button) view.findViewById(R.id.salvartransferencia);
        valortransferencia.addTextChangedListener(new MoneyTextWatcher(valortransferencia));
        //endregion

        //region declarar variaveis spinner
        students = new ArrayList<String>();
        ids = new ArrayList<String>();
        spinner = (Spinner) view.findViewById(R.id.spinner_transferencia);
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
        spinner2 = (Spinner) view.findViewById(R.id.spinner_transferencia1);
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

        //region Clique do Botão
        salvartransferencia.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
        insert();
            }
        });
        //endregion

        //region outros
        ButterKnife.bind(this, view);

        ( (MainActivity)getActivity()).updateToolbarTitle("Lançamento de Transferência");
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
        for(int i=0;i<j.length();i++){
            try {
                JSONObject json = j.getJSONObject(i);
                students.add(json.getString("nome_conta"));
                ids.add(json.getString("id_conta"));
            } catch (JSONException e) {
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
                            "http://premiumcontrol.com.br/NakasoneSoftapp/select/select_ultimo_transferencia.php");


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
        String  descricao_diario = descricaotransferencia.getText().toString();
        String  valor_diario = valortransferencia.getText().toString();
        String  data_diario = datatransferencia.getText().toString();
        String  tipo_diario = "Consorcio";
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
                String  descricao_diario = descricaotransferencia.getText().toString();
                String  valor_diario = valortransferencia.getText().toString();
                String  data_diario = datatransferencia.getText().toString();
                String  tipo_diario = "Consorcio";
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
