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


public class AlterarTransferenciaNakasone extends BaseFragment implements Spinner.OnItemSelectedListener
{

    EditText alterardescricaotransferencia,alterarondefoitransferencia,alterarvalortransferencia,alterarondeveiotransferencia,alterardatatransferencia;

    Button alterarsalvartransferencia, alterarexcluirtransferencia;

    ProgressDialog loading;

    String resposta;

    //region Spinner Variaveis
    private Spinner spinner;
    private ArrayList<String> students;

    ArrayList<String> ids;
    private JSONArray result;
    static String id_spinner;
    //endregion








    public void insert(){

        String  id_transferencia = String.valueOf(Static.getId_transferencia());
        insertToDatabase(id_transferencia);

    }

    private void insertToDatabase(String id_transferencia){
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {
                String paramid_transferencia = params[0];

                //InputStream is = null;

                String  id_transferencia = String.valueOf(Static.getId_transferencia());

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("id_transferencia", id_transferencia));


                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "http://premiumcontrol.com.br/NakasoneSoftapp/delete/delete_transferencia.php");
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
        sendPostReqAsyncTask.execute(id_transferencia);
    }










    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_alterar_transferencia, container, false);
        //region FindViews
        alterardescricaotransferencia = (EditText) view.findViewById(R.id.alterardescricaotransferencia);
        alterarondefoitransferencia = (EditText) view.findViewById(R.id.alterarondefoitransferencia);
        alterarvalortransferencia = (EditText) view.findViewById(R.id.alterarvalortransferencia);
        alterarondeveiotransferencia = (EditText) view.findViewById(R.id.alterarondeveiotransferencia);
        alterardatatransferencia = (EditText) view.findViewById(R.id.alterardatatransferencia);
        alterarsalvartransferencia = (Button) view.findViewById(R.id.alterarsalvartransferencia);
        alterarexcluirtransferencia = (Button) view.findViewById(R.id.alterarexcluirtransferencia);
        alterarvalortransferencia.addTextChangedListener(new MoneyTextWatcher(alterarvalortransferencia));


        students = new ArrayList<String>();
        ids = new ArrayList<String>();
        spinner = (Spinner) view.findViewById(R.id.spinner_alterar_transferencia);
        spinner.setOnItemSelectedListener(this);



        //endregion
        //region Máscara da data
        alterarexcluirtransferencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert();
            }
        });




        alterardatatransferencia.addTextChangedListener(MaskEditUtil.mask(alterardatatransferencia, MaskEditUtil.FORMAT_DATE));
        //endregion
        //region Clique do Botão
        alterarsalvartransferencia.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                UpdateEvento();

            }
        });
        //endregion
        //region outros
        ButterKnife.bind(this, view);

        ( (MainActivity)getActivity()).updateToolbarTitle("Alteração da Transferência");
        //endregion
        getData();
        getData1();
        return view;
    }

    //region Editar Dados
    public void UpdateEvento()
    {

        String descricao_transferencia = alterardescricaotransferencia.getText().toString();
        String valor_transferencia = alterarvalortransferencia.getText().toString();
        String praondefoi_transferencia = id_spinner.toString();
        String contaondeveio_transferencia  = alterarondeveiotransferencia.getText().toString();
        String data_transferencia = alterardatatransferencia.getText().toString();
        String id_transferencia = String.valueOf(Static.getId_transferencia()).trim();

        UpdateToDatabase(descricao_transferencia,valor_transferencia,praondefoi_transferencia,contaondeveio_transferencia,data_transferencia,id_transferencia);

    }

    private void UpdateToDatabase(String descricao_transferencia, String valor_transferencia, String praondefoi_transferencia, String contaondeveio_transferencia,String data_transferencia, String id_transferencia) {
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

                String descricao_transferencia = alterardescricaotransferencia.getText().toString();
                String valor_transferencia = alterarvalortransferencia.getText().toString();
                String praondefoi_transferencia = id_spinner.toString();
                String contaondeveio_transferencia  = alterarondeveiotransferencia.getText().toString();
                String data_transferencia = alterardatatransferencia.getText().toString();
                String id_transferencia = String.valueOf(Static.getId_transferencia()).trim();

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("descricao_transferencia", descricao_transferencia));
                nameValuePairs.add(new BasicNameValuePair("valor_transferencia", valor_transferencia));
                nameValuePairs.add(new BasicNameValuePair("praondefoi_transferencia", praondefoi_transferencia));
                nameValuePairs.add(new BasicNameValuePair("contaondeveio_transferencia", contaondeveio_transferencia));
                nameValuePairs.add(new BasicNameValuePair("data_transferencia", data_transferencia));
                nameValuePairs.add(new BasicNameValuePair("id_transferencia", id_transferencia));



                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost("http://premiumcontrol.com.br/NakasoneSoftapp/update/alterar_transferencia.php");
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
        sendPostReqAsyncTask.execute(descricao_transferencia,valor_transferencia,praondefoi_transferencia,contaondeveio_transferencia,data_transferencia,id_transferencia);
    }

    //endregion

    //region Pesquisa para preencher os campos
    private void getData1()
    {

        loading = ProgressDialog.show(getActivity(), "Carregando informações do evento!", "Por favor, aguarde", false, false);

        String url =  ConfigRetrieve.DATA_URL_TRANSFERENCIA + String.valueOf(Static.getId_transferencia());

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
        String descricao_transferencia ="";
        String valor_transferencia ="";
        String praondefoi_transferencia = "";
        String contaondeveio_transferencia = "";
        String data_transferencia = "";

        try
        {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(ConfigRetrieve.JSON_ARRAY);
            JSONObject collegeData = result.getJSONObject(0);

            descricao_transferencia = collegeData.getString(ConfigRetrieve.DESCRICAO_TRANSFERENCIA);
            valor_transferencia = collegeData.getString(ConfigRetrieve.VALOR_TRANSFERENCIA);
            praondefoi_transferencia = collegeData.getString(ConfigRetrieve.PRAONDEFOI_TRANSFERENCIA);
            contaondeveio_transferencia = collegeData.getString(ConfigRetrieve.CONTAONDEVEIO_TRANSFERENCIA);
            data_transferencia = collegeData.getString(ConfigRetrieve.DATA_TRANSFERENCIA);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        //region COLOCANDO OS CAMPOS NA TELA
        alterardescricaotransferencia.setText(descricao_transferencia);
        alterarvalortransferencia.setText(valor_transferencia);
        alterarondefoitransferencia.setText(praondefoi_transferencia);
        alterarondeveiotransferencia.setText(contaondeveio_transferencia);
        alterardatatransferencia.setText(data_transferencia);
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
    //endregion
}



