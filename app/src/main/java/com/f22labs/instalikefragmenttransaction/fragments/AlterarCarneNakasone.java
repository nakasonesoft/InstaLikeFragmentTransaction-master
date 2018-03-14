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


public class AlterarCarneNakasone extends BaseFragment
{

    EditText alterardescricaocarne,alterarvalorcarne,alterardatacarne,alterarparcelas;

    Button alterarsalvarcarne, alterarexcluircarne;

    ProgressDialog loading;

    String resposta;


    private String current = "";

    public void insert(){

        String  id_carne = String.valueOf(Static.getId_carne());
        insertToDatabase(id_carne);

    }

    private void insertToDatabase(String id_carne){
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {
                String paramid_carne = params[0];

                //InputStream is = null;

                String  id_carne = String.valueOf(Static.getId_carne());

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("id_carne", id_carne));


                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "http://premiumcontrol.com.br/NakasoneSoftapp/delete/delete_carne.php");
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
                    mFragmentNavigation.pushFragment(new RecyclerAltExcCarne());
                    Toast.makeText(getActivity(), "Conta apagada com sucesso", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getActivity(), "Falha ao apagar conta", Toast.LENGTH_LONG).show();
                }




            }



        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(id_carne);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_alterar_carne, container, false);
        //region FindViews
        alterardescricaocarne = (EditText) view.findViewById(R.id.alterardescricaocarne);
        alterarvalorcarne = (EditText) view.findViewById(R.id.alterarvalorcarne);
        alterardatacarne = (EditText) view.findViewById(R.id.alterardatacarne);
        alterarparcelas = (EditText) view.findViewById(R.id.alterarparcelas);

        alterarexcluircarne = (Button) view.findViewById(R.id.alterarexcluircarne);
        alterarsalvarcarne = (Button) view.findViewById(R.id.alterarsalvarcarne);
        //endregion

        //region Máscaras
        alterardatacarne.addTextChangedListener(MaskEditUtil.mask(alterardatacarne, MaskEditUtil.FORMAT_DATE));

        alterarvalorcarne.addTextChangedListener(new MoneyTextWatcher(alterarvalorcarne));
        //endregion
        //region Clique do Botão
        alterarexcluircarne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert();
            }
        });

        alterarsalvarcarne.setOnClickListener(new View.OnClickListener()
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

        ( (MainActivity)getActivity()).updateToolbarTitle("Alteração de carnê");
        //endregion
        getData1();
        return view;
    }

    //region Editar Dados
    public void UpdateEvento()
    {

        String descricao_carne = alterardescricaocarne.getText().toString();
        String valor_carne = alterarvalorcarne.getText().toString().replace(",",".");
        String datafinal_carne = alterardatacarne.getText().toString();
        String qntd_carne = alterarparcelas.getText().toString();
        String id_carne = String.valueOf(Static.getId_carne()).trim();

        UpdateToDatabase(descricao_carne, valor_carne, datafinal_carne, qntd_carne, id_carne);

    }

    private void UpdateToDatabase(String descricao_carne, String valor_carne, String datafinal_carne, String qntd_carne,String id_carne){
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String>
        {
            @Override
            protected String doInBackground(String... params)
            {


                String paramdescricao_carne = params[0];
                String paramvalor_carne = params[1];
                String paramdatafinal_carne = params[2];
                String paramqntd_carne = params[3];
                String paramid_carne = params[4];


                String descricao_carne = alterardescricaocarne.getText().toString();
                String valor_carne = alterarvalorcarne.getText().toString().replace(",",".");
                String datafinal_carne = alterardatacarne.getText().toString();
                String qntd_carne = alterarparcelas.getText().toString();
                String id_carne = String.valueOf(Static.getId_carne()).trim();


                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("descricao_carne", descricao_carne));
                nameValuePairs.add(new BasicNameValuePair("valor_carne", valor_carne));
                nameValuePairs.add(new BasicNameValuePair("datafinal_carne", datafinal_carne));
                nameValuePairs.add(new BasicNameValuePair("qntd_carne", qntd_carne));
                nameValuePairs.add(new BasicNameValuePair("id_carne", id_carne));


                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost("http://premiumcontrol.com.br/NakasoneSoftapp/update/alterar_carne.php");
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
        sendPostReqAsyncTask.execute(descricao_carne, valor_carne, datafinal_carne, qntd_carne, id_carne);
    }

    //endregion

    //region Pesquisas para mostrar nos campos
    private void getData1()
    {

        loading = ProgressDialog.show(getActivity(), "Carregando informações do Carnê!", "Por favor, aguarde", false, false);

        String url =  ConfigRetrieve.DATA_URL_CARNE + String.valueOf(Static.getId_carne());

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
        String descricao_carne ="";
        String valor_carne ="";
        String datafinal_carne = "";
        String qntd_carne = "";

        try
        {
            JSONObject jsonObject = new JSONObject(response);

            JSONArray result = jsonObject.getJSONArray(ConfigRetrieve.JSON_ARRAY);

            JSONObject collegeData = result.getJSONObject(0);

            descricao_carne = collegeData.getString(ConfigRetrieve.DESCRICAO_CARNE);
            valor_carne = collegeData.getString(ConfigRetrieve.VALOR_CARNE);
            datafinal_carne = collegeData.getString("datafinal_carne");
            qntd_carne = collegeData.getString(ConfigRetrieve.QNTD_CARNE);

        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        //region COLOCANDO OS CAMPOS NA TELA
        alterardescricaocarne.setText(descricao_carne);
        alterarvalorcarne.setText(valor_carne);
        alterardatacarne.setText(datafinal_carne);
        alterarparcelas.setText(qntd_carne);

        //endregion


    }


    //endregion

}
