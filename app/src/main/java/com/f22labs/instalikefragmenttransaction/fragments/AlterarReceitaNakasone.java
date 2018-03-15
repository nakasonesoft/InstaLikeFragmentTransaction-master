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


public class AlterarReceitaNakasone extends BaseFragment
{

    EditText alterardescricaoreceita,alterarcontareceita,alterarvalorreceita,alterarparaondefoireceita,alterardatareceita;

    Button alterarsalvarreceita, alterarexcluirreceita;
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

        String  id_receita = String.valueOf(Static.getId_receita());
        insertToDatabase(id_receita);

    }


    private void insertToDatabase(String id_receita){
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {
                String paramid_receita = params[0];

                //InputStream is = null;

                String  id_receita = String.valueOf(Static.getId_receita());

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("id_receita", id_receita));


                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "http://premiumcontrol.com.br/NakasoneSoftapp/delete/delete_receita.php");
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
        sendPostReqAsyncTask.execute(id_receita);
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_alterar_receita, container, false);

        //region FindViews
        alterardescricaoreceita = (EditText) view.findViewById(R.id.alterardescricaoreceita);
        alterarcontareceita = (EditText) view.findViewById(R.id.alterarcontareceita);
        alterarvalorreceita = (EditText) view.findViewById(R.id.alterarvalorreceita);
        alterarparaondefoireceita = (EditText) view.findViewById(R.id.alterarparaondefoireceita);
        alterardatareceita = (EditText) view.findViewById(R.id.alterardatareceita);
        alterarsalvarreceita = (Button) view.findViewById(R.id.alterarsalvarreceita);
        alterarexcluirreceita = (Button) view.findViewById(R.id.alterarexcluirreceita);



        students = new ArrayList<String>();

        ids = new ArrayList<String>();

        students2 = new ArrayList<String>();

        ids2 = new ArrayList<String>();

        spinner = (Spinner) view.findViewById(R.id.spinner_alterar_receita);
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

        spinner_alterar_pagamento = (Spinner) view.findViewById(R.id.spinner_alterar_receita2);
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
        //region Máscaras
        alterarvalorreceita.addTextChangedListener(new MoneyTextWatcher(alterarvalorreceita));
        alterardatareceita.addTextChangedListener(MaskEditUtil.mask(alterardatareceita, MaskEditUtil.FORMAT_DATE));
        //endregion


        //region Clique do Botão
        alterarexcluirreceita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert();
            }
        });

        alterarsalvarreceita.setOnClickListener(new View.OnClickListener()
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

        ( (MainActivity)getActivity()).updateToolbarTitle("Alteração da Receita");
        //endregion
        getData();
        getData1();
        getData2();
        return view;
    }

    //region Editar Dados
    public void UpdateEvento()
    {

        String descricao_receita = alterardescricaoreceita.getText().toString();
        String id_conta = id_spinner.toString();
        String valor_receita = alterarvalorreceita.getText().toString();
        String praondefoi_receita = alterarparaondefoireceita.getText().toString();
        String data_receita = alterardatareceita.getText().toString();
        String id_receita = String.valueOf(Static.getId_receita()).trim();

        UpdateToDatabase(descricao_receita,id_conta,valor_receita,praondefoi_receita,data_receita,id_receita);

    }

    private void UpdateToDatabase(String descricao_receita, String id_conta, String valor_receita, String praondefoi_receita,String data_receita, String id_receita) {
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

                String descricao_receita = alterardescricaoreceita.getText().toString();
                String id_conta = id_spinner.toString();
                String valor_receita = alterarvalorreceita.getText().toString();
                String praondefoi_receita = alterarparaondefoireceita.getText().toString();
                String data_receita = alterardatareceita.getText().toString();
                String id_receita = String.valueOf(Static.getId_receita()).trim();

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("descricao_receita", descricao_receita));
                nameValuePairs.add(new BasicNameValuePair("id_conta", id_conta));
                nameValuePairs.add(new BasicNameValuePair("valor_receita", valor_receita));
                nameValuePairs.add(new BasicNameValuePair("praondefoi_receita", praondefoi_receita));
                nameValuePairs.add(new BasicNameValuePair("data_receita", data_receita));
                nameValuePairs.add(new BasicNameValuePair("id_receita", id_receita));



                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost("http://premiumcontrol.com.br/NakasoneSoftapp/update/alterar_receita.php");
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
        sendPostReqAsyncTask.execute(descricao_receita,id_conta,valor_receita,praondefoi_receita,data_receita,id_receita);
    }

    //endregion

    //region Pesquisa para mostrar nos campos
    private void getData1()
    {

        loading = ProgressDialog.show(getActivity(), "Carregando informações do evento!", "Por favor, aguarde", false, false);

        String url =  ConfigRetrieve.DATA_URL_RECEITA + String.valueOf(Static.getId_receita());

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
        String descricao_receita ="";
        String id_conta ="";
        String valor_receita = "";
        String praondefoi_receita = "";
        String data_receita = "";


        try
        {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(ConfigRetrieve.JSON_ARRAY);
            JSONObject collegeData = result.getJSONObject(0);

            descricao_receita = collegeData.getString(ConfigRetrieve.DESCRICAO_RECEITA);
            id_conta = collegeData.getString(ConfigRetrieve.ID_CONTA);
            valor_receita = collegeData.getString(ConfigRetrieve.VALOR_RECEITA);
            praondefoi_receita = collegeData.getString(ConfigRetrieve.PRAONDEFOI_RECEITA);
            data_receita = collegeData.getString(ConfigRetrieve.DATA_RECEITA);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }



        //region COLOCANDO OS CAMPOS NA TELA
        alterardescricaoreceita.setText(descricao_receita);
        alterarcontareceita.setText(id_conta);
        alterarvalorreceita.setText(valor_receita);
        alterarparaondefoireceita.setText(praondefoi_receita);
        alterardatareceita.setText(data_receita);
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
        StringRequest stringRequest = new StringRequest("http://premiumcontrol.com.br/NakasoneSoftapp/select/select_grupos.php",
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
                students2.add(json.getString("nome_grupo"));
                ids2.add(json.getString("id_grupo"));
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
