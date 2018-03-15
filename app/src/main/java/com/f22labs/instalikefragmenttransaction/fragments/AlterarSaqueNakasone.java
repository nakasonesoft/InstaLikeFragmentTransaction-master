package com.f22labs.instalikefragmenttransaction.fragments;

import android.app.ProgressDialog;
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
import com.f22labs.instalikefragmenttransaction.adapters.ConfigRetrieve;
import com.f22labs.instalikefragmenttransaction.utils.MaskEditUtil;
import com.f22labs.instalikefragmenttransaction.utils.MoneyTextWatcher;
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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;


public class AlterarSaqueNakasone extends BaseFragment
{

    EditText alterardescricaosaque,alterarondefoisaque,alterarvalorsaque,alterarondeveiosaque,alterardatasaque;

    Button alterarsalvarsaque, alterarexcluirsaque;

    ProgressDialog loading;

    String resposta;

    //region Spinner Variaveis
    private Spinner spinner;
    private ArrayList<String> students;

    ArrayList<String> ids;
    private JSONArray result;
    static String id_spinner;
    //endregion

    //region Spinner Variaveis 2

    private ArrayList<String> students2;
    private Spinner spinner_alterar_pagamento;
    ArrayList<String> ids2;
    private JSONArray result2;
    static String id_spinner2;
    //endregion



    public void insert(){

        String  id_saque = String.valueOf(Static.getId_saque());
        insertToDatabase(id_saque);

    }

    private void insertToDatabase(String id_saque){
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {
                String paramid_saque = params[0];

                //InputStream is = null;

                String  id_saque = String.valueOf(Static.getId_saque());

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("id_saque", id_saque));


                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "http://premiumcontrol.com.br/NakasoneSoftapp/delete/delete_saque.php");
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

                Toast.makeText(getActivity(), result, Toast.LENGTH_LONG).show();

                if(Integer.parseInt(resposta) == 1)
                {
                    mFragmentNavigation.pushFragment(new AltExcLancamentoNakasone());
                    Toast.makeText(getActivity(), "Conta apagada com sucesso", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getActivity(), "Falha ao apagar conta", Toast.LENGTH_LONG).show();
                }




            }



        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(id_saque);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_alterar_saque, container, false);
        //region FindViews
        alterardescricaosaque = (EditText) view.findViewById(R.id.alterardescricaosaque);
        alterarondefoisaque = (EditText) view.findViewById(R.id.alterarondefoisaque);
        alterarvalorsaque = (EditText) view.findViewById(R.id.alterarvalorsaque);
        alterarondeveiosaque = (EditText) view.findViewById(R.id.alterarondeveiosaque);
        alterardatasaque = (EditText) view.findViewById(R.id.alterardatasaque);
        alterarsalvarsaque = (Button) view.findViewById(R.id.alterarsalvarsaque);
        alterarexcluirsaque = (Button) view.findViewById(R.id.alterarexcluirsaque);
        alterarvalorsaque.addTextChangedListener(new MoneyTextWatcher(alterarvalorsaque));

        students = new ArrayList<String>();
        ids = new ArrayList<String>();
        spinner = (Spinner) view.findViewById(R.id.spinner_alterar_saque);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {

                id_spinner = ids.get(position);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
                ids.get(spinner.getSelectedItemPosition());
            }
        });


        spinner_alterar_pagamento = (Spinner) view.findViewById(R.id.spinner_alterar_saque2);
        spinner_alterar_pagamento.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {

                id_spinner2 = ids2.get(position);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
                ids2.get(spinner_alterar_pagamento.getSelectedItemPosition());
            }
        });





        //endregion
        //region Máscara da data
        alterardatasaque.addTextChangedListener(MaskEditUtil.mask(alterardatasaque, MaskEditUtil.FORMAT_DATE));
        //endregion
        //region Clique do Botão



        alterarexcluirsaque.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert();
            }
        });



        alterarsalvarsaque.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                UpdateEvento();
            }
        });
        //endregion
        //region Outros
        ButterKnife.bind(this, view);

        ( (MainActivity)getActivity()).updateToolbarTitle("Alteração do Saque");

        //endregion
        getData();
        getData1();
        getData2();
        return view;
    }

    //region Editar Dados
    public void UpdateEvento()
    {

        String descricao_saque = alterardescricaosaque.getText().toString();
        String valor_saque = alterarvalorsaque.getText().toString();
        String praondefoi_saque = alterarondefoisaque.getText().toString();
        String contaondeveio_saque  = id_spinner.toString();
        String data_saque = alterardatasaque.getText().toString();
        String id_saque = String.valueOf(Static.getId_saque()).trim();

        UpdateToDatabase(descricao_saque,valor_saque,praondefoi_saque,contaondeveio_saque,data_saque,id_saque);

    }

    private void UpdateToDatabase(String descricao_saque, String valor_saque, String praondefoi_saque, String contaondeveio_saque,String data_saque, String id_saque) {
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String>
        {
            @Override
            protected String doInBackground(String... params)
            {
                String paramDesc = params[0];
                String paramValor = params[1];
                String paramConta = params[2];
                String paramComoFoiPago = params[3];
                String paramData = params[4];
                String paramID = params[5];

                String descricao_saque = alterardescricaosaque.getText().toString();
                String valor_saque = alterarvalorsaque.getText().toString();
                String praondefoi_saque = alterarondefoisaque.getText().toString();
                String contaondeveio_saque  = id_spinner.toString();
                String data_saque = alterardatasaque.getText().toString();
                String id_saque = String.valueOf(Static.getId_saque()).trim();

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("descricao_saque", descricao_saque));
                nameValuePairs.add(new BasicNameValuePair("valor_saque", valor_saque));
                nameValuePairs.add(new BasicNameValuePair("praondefoi_saque", praondefoi_saque));
                nameValuePairs.add(new BasicNameValuePair("contaondeveio_saque", contaondeveio_saque));
                nameValuePairs.add(new BasicNameValuePair("data_saque", data_saque));
                nameValuePairs.add(new BasicNameValuePair("id_saque", id_saque));



                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost("http://premiumcontrol.com.br/NakasoneSoftapp/update/alterar_saque.php");
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    HttpResponse response = httpClient.execute(httpPost);
                    HttpEntity entity = response.getEntity();
                } catch (ClientProtocolException e) {

                } catch (IOException e) {

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
        sendPostReqAsyncTask.execute(descricao_saque,valor_saque,praondefoi_saque,contaondeveio_saque,data_saque,id_saque);
    }

    //endregion

    //region Pesquisa para preencher os campos
    private void getData1()
    {

        loading = ProgressDialog.show(getActivity(), "Carregando informações do evento!", "Por favor, aguarde", false, false);

        String url =  ConfigRetrieve.DATA_URL_SAQUE + String.valueOf(Static.getId_saque());

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                loading.dismiss();
                showJSON(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        try {
                            Toast.makeText(getActivity(), error.getMessage().toString(), Toast.LENGTH_LONG).show();
                        } catch (Exception e) {
                            // loading.setCancelable(true);
                            e.printStackTrace();
                        }
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

    private void showJSON(String response)
    {
        String descricao_saque ="";
        String valor_saque ="";
        String praondefoi_saque = "";
        String contaondeveio_saque = "";
        String data_saque = "";

        try
        {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(ConfigRetrieve.JSON_ARRAY);
            JSONObject collegeData = result.getJSONObject(0);

            descricao_saque = collegeData.getString(ConfigRetrieve.DESCRICAO_SAQUE);
            valor_saque = collegeData.getString(ConfigRetrieve.VALOR_SAQUE);
            praondefoi_saque = collegeData.getString(ConfigRetrieve.PRAONDEFOI_SAQUE);
            contaondeveio_saque = collegeData.getString(ConfigRetrieve.CONTAONDEVEIO_SAQUE);
            data_saque = collegeData.getString(ConfigRetrieve.DATA_SAQUE);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        //region COLOCANDO OS CAMPOS NA TELA
        alterardescricaosaque.setText(descricao_saque);
        alterarondefoisaque.setText(valor_saque);
        alterarvalorsaque.setText(praondefoi_saque);
        alterarondeveiosaque.setText(contaondeveio_saque);
        alterardatasaque.setText(data_saque);
        // getData1();
        //endregion


    }
    //endregion

    //region Spinner
    private void getData(){
        StringRequest stringRequest = new StringRequest("http://premiumcontrol.com.br/NakasoneSoftapp/teste.php?id_cliente="+Static.getId_cliente()+"",
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


    //region Spinner 2
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

        spinner_alterar_pagamento.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, students2));

    }



    //endregion




}
