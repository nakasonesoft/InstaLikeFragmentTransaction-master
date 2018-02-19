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

public class AlterarContaNakasone extends BaseFragment implements Spinner.OnItemSelectedListener
{

    EditText txtDescricaoContaAlterar,txtGrupoContaAlterar,txtSaldoInicialContaAlterar,datafechamentoContaAlterar;

    Button SalvarContaAlterar, ExcluirContaAlterar;

    ProgressDialog loading;

    String resposta;

    private String current = "";

    //region Spinner Variaveis
    private Spinner spinner;
    private ArrayList<String> students;
    private JSONArray result;
    static String id_spinner = "1";
    //endregion

    public void insert(){

        String  id_conta = String.valueOf(Static.getId_conta());
        insertToDatabase(id_conta);

    }

    private void insertToDatabase(String id_conta){
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {
                String paramid_conta = params[0];

                //InputStream is = null;

                String  id_conta = String.valueOf(Static.getId_conta());

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("id_conta", id_conta));


                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "http://premiumcontrol.com.br/NakasoneSoftapp/delete/delete_conta.php");
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
                    mFragmentNavigation.pushFragment(new LancamentoNakasone());
                    Toast.makeText(getActivity(), "Conta apagada com sucesso", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getActivity(), "Falha ao apagar conta", Toast.LENGTH_LONG).show();
                }




            }



        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(id_conta);
    }






    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_alterar_contas, container, false);
        //region FindViews
        txtDescricaoContaAlterar = (EditText) view.findViewById(R.id.txtDescricaoContaAlterar);
        txtGrupoContaAlterar = (EditText) view.findViewById(R.id.txtGrupoContaAlterar);
        txtSaldoInicialContaAlterar = (EditText) view.findViewById(R.id.txtSaldoInicialContaAlterar);
        datafechamentoContaAlterar = (EditText) view.findViewById(R.id.datafechamentoContaAlterar);
        SalvarContaAlterar = (Button) view.findViewById(R.id.SalvarContaAlterar);
        ExcluirContaAlterar = (Button) view.findViewById(R.id.ExcluirContaAlterar);

        students = new ArrayList<String>();
        spinner = (Spinner) view.findViewById(R.id.spinner_conta);
        spinner.setOnItemSelectedListener(this);

        //endregion
        //region Máscaras
        datafechamentoContaAlterar.addTextChangedListener(MaskEditUtil.mask(datafechamentoContaAlterar, MaskEditUtil.FORMAT_DATE));

        txtSaldoInicialContaAlterar.addTextChangedListener(new MoneyTextWatcher(txtSaldoInicialContaAlterar));
        //endregion
        //region Clique do Botão
        ExcluirContaAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert();
            }
        });



        SalvarContaAlterar.setOnClickListener(new View.OnClickListener()
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

        ( (MainActivity)getActivity()).updateToolbarTitle("Alteração de Saldo inicial");
        //endregion
        getData();
        getData1();
        return view;
    }

    //region Editar Dados
    public void UpdateEvento()
    {

        String nome_conta = txtDescricaoContaAlterar.getText().toString();
        String id_grupo = id_spinner.toString();
        String saldoinicial_conta = txtSaldoInicialContaAlterar.getText().toString();
        String datafechamento_conta = datafechamentoContaAlterar.getText().toString();
        String id_conta = String.valueOf(Static.getId_conta()).trim();

        UpdateToDatabase(nome_conta, id_grupo, saldoinicial_conta, datafechamento_conta, id_conta);

    }

    private void UpdateToDatabase(String nome_conta, String id_grupo, String saldoinicial_conta, String datafechamento_conta,String id_conta) {
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



                String nome_conta = txtDescricaoContaAlterar.getText().toString();
                String id_grupo = id_spinner.toString();
                String saldoinicial_conta = txtSaldoInicialContaAlterar.getText().toString();
                String datafechamento_conta = datafechamentoContaAlterar.getText().toString();
                String id_conta = String.valueOf(Static.getId_conta()).trim();


                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("nome_conta", nome_conta));
                nameValuePairs.add(new BasicNameValuePair("id_grupo", id_grupo));
                nameValuePairs.add(new BasicNameValuePair("saldoinicial_conta", saldoinicial_conta));
                nameValuePairs.add(new BasicNameValuePair("datafechamento_conta", datafechamento_conta));
                nameValuePairs.add(new BasicNameValuePair("id_conta", id_conta));




                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost("http://premiumcontrol.com.br/NakasoneSoftapp/update/alterar_conta.php");
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
        sendPostReqAsyncTask.execute(nome_conta, id_grupo, saldoinicial_conta, datafechamento_conta, id_conta);
    }

    //endregion

    //region Pesquisas para mostrar nos campos
    private void getData1()
    {

        loading = ProgressDialog.show(getActivity(), "Carregando informações da Conta!", "Por favor, aguarde", false, false);

        String url =  ConfigRetrieve.DATA_URL_CONTAS + String.valueOf(Static.getId_conta());
        Log.d("QUEROISSO",url);
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                loading.dismiss();
                showJSON(response);
            }
        },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        try
                        {
                            Toast.makeText(getActivity(), error.getMessage().toString(), Toast.LENGTH_LONG).show();
                        } catch (Exception e)

                        {
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
        String id_grupo ="";
        String nome_conta ="";
        String saldoinicial_conta = "";
        String datafechamento_conta = "";


        try
        {
            JSONObject jsonObject = new JSONObject(response);

            JSONArray result = jsonObject.getJSONArray(ConfigRetrieve.JSON_ARRAY);

            JSONObject collegeData = result.getJSONObject(0);

            id_grupo = collegeData.getString(ConfigRetrieve.ID_GRUPO_CONTA);
            nome_conta = collegeData.getString(ConfigRetrieve.NOME_CONTA);
            saldoinicial_conta = collegeData.getString(ConfigRetrieve.SALDOINICIAL_CONTA);
            datafechamento_conta = collegeData.getString(ConfigRetrieve.DATAFECHAMENTO_CONTA);


        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        //region COLOCANDO OS CAMPOS NA TELA
        txtDescricaoContaAlterar.setText(nome_conta);
        txtGrupoContaAlterar.setText(id_grupo);
        txtSaldoInicialContaAlterar.setText(saldoinicial_conta);
        datafechamentoContaAlterar.setText(datafechamento_conta);


        //endregion


    }


    //endregion

    //region Spinner
    private void getData(){
        StringRequest stringRequest = new StringRequest("http://premiumcontrol.com.br/NakasoneSoftapp/select/testebusca.php",
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

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        spinner.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, students));
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        id_spinner = String.valueOf(position + 1);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    //endregion
}
