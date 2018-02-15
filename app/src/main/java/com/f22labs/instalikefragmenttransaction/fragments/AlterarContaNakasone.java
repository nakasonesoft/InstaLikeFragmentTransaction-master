package com.f22labs.instalikefragmenttransaction.fragments;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;


public class AlterarContaNakasone extends BaseFragment
{

    EditText txtDescricaoContaAlterar,txtGrupoContaAlterar,txtSaldoInicialContaAlterar,datafechamentoContaAlterar;

    Button SalvarContaAlterar;

    ProgressDialog loading;

    private String current = "";

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
        //endregion
        //region Máscaras
        datafechamentoContaAlterar.addTextChangedListener(MaskEditUtil.mask(datafechamentoContaAlterar, MaskEditUtil.FORMAT_DATE));

        txtSaldoInicialContaAlterar.addTextChangedListener(new MoneyTextWatcher(txtSaldoInicialContaAlterar));
        //endregion
        //region Clique do Botão
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

        ( (MainActivity)getActivity()).updateToolbarTitle("Alteração de consórcio");
        //endregion
        getData1();
        return view;
    }

    //region Editar Dados
    public void UpdateEvento()
    {

        String nome_conta = txtDescricaoContaAlterar.getText().toString();
        String id_grupo = txtGrupoContaAlterar.getText().toString();
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
                String id_grupo = txtGrupoContaAlterar.getText().toString();
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


}
