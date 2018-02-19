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

public class AlterarDepositoNakasone extends BaseFragment implements Spinner.OnItemSelectedListener
{
    EditText alterardescricaodeposito,alterarondefoideposito,alterarvalordeposito,alterardeondeveiodeposito,alterardatadeposito;

    Button alterarsalvardeposito, alterarexcluirdeposito;

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

        String  id_deposito = String.valueOf(Static.getId_consorcio());
        insertToDatabase(id_deposito);

    }

    private void insertToDatabase(String id_deposito){
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {
                String paramid_deposito = params[0];

                //InputStream is = null;

                String  id_deposito = String.valueOf(Static.getId_deposito());

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("id_deposito", id_deposito));


                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "http://premiumcontrol.com.br/NakasoneSoftapp/delete/delete_deposito.php");
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
        sendPostReqAsyncTask.execute(id_deposito);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_alterar_deposito, container, false);

        //region FindViews
        alterardescricaodeposito = (EditText) view.findViewById(R.id.alterardescricaodeposito);
        alterarondefoideposito = (EditText) view.findViewById(R.id.alterarondefoideposito);
        alterarvalordeposito = (EditText) view.findViewById(R.id.alterarvalordeposito);
        alterardeondeveiodeposito = (EditText) view.findViewById(R.id.alterardeondeveiodeposito);
        alterardatadeposito = (EditText) view.findViewById(R.id.alterardatadeposito);
        alterarsalvardeposito = (Button) view.findViewById(R.id.alterarsalvardeposito);
        alterarexcluirdeposito = (Button) view.findViewById(R.id.alterarexcluirdeposito);

        students = new ArrayList<String>();
        ids = new ArrayList<String>();
        spinner = (Spinner) view.findViewById(R.id.spinner_alterar_deposito);
        spinner.setOnItemSelectedListener(this);



        //endregion
        //region Máscara
        alterardatadeposito.addTextChangedListener(MaskEditUtil.mask(alterardatadeposito, MaskEditUtil.FORMAT_DATE));
        alterarvalordeposito.addTextChangedListener(new MoneyTextWatcher(alterarvalordeposito));
        //endregion
        //region Clique do Botão
        alterarsalvardeposito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                UpdateEvento();
            }
        });

        alterarexcluirdeposito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert();
            }
        });

        //endregion
        //region Outros
        ButterKnife.bind(this, view);
        ( (MainActivity)getActivity()).updateToolbarTitle("Alteração do Depósito");
        //endregion
        getData();
        getData1();
        return view;
    }

    //region Editar Dados
    public void UpdateEvento()
    {

        String descricao_deposito = alterardescricaodeposito.getText().toString();
        String valor_deposito = alterarvalordeposito.getText().toString();
        String praondefoi_deposito = id_spinner.toString();
        String contaondeveio_deposito = alterardeondeveiodeposito.getText().toString();
        String data_deposito = alterardatadeposito.getText().toString();
        String id_deposito = String.valueOf(Static.getId_deposito()).trim();

        UpdateToDatabase(descricao_deposito,valor_deposito,praondefoi_deposito,contaondeveio_deposito,data_deposito,id_deposito);

    }

    private void UpdateToDatabase(String descricao_deposito, String valor_deposito, String praondefoi_deposito, String contaondeveio_deposito,String data_deposito, String id_deposito) {
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

                String descricao_deposito = alterardescricaodeposito.getText().toString();
                String valor_deposito = alterarvalordeposito.getText().toString();
                String praondefoi_deposito = id_spinner.toString();
                String contaondeveio_deposito = alterardeondeveiodeposito.getText().toString();
                String data_deposito = alterardatadeposito.getText().toString();
                String id_deposito = String.valueOf(Static.getId_deposito()).trim();


                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("descricao_deposito", descricao_deposito));
                nameValuePairs.add(new BasicNameValuePair("valor_deposito", valor_deposito));
                nameValuePairs.add(new BasicNameValuePair("praondefoi_deposito", praondefoi_deposito));
                nameValuePairs.add(new BasicNameValuePair("contaondeveio_deposito", contaondeveio_deposito));
                nameValuePairs.add(new BasicNameValuePair("data_deposito", data_deposito));
                nameValuePairs.add(new BasicNameValuePair("id_deposito", id_deposito));



                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost("http://premiumcontrol.com.br/NakasoneSoftapp/update/alterar_deposito.php");
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
        sendPostReqAsyncTask.execute(descricao_deposito,valor_deposito,praondefoi_deposito,contaondeveio_deposito,data_deposito,id_deposito);
    }

    //endregion

    //region Pesquisa para mostrar nos campos

    private void getData1()
    {

        loading = ProgressDialog.show(getActivity(), "Carregando informações do evento!", "Por favor, aguarde", false, false);

        String url =  ConfigRetrieve.DATA_URL_DEPOSITO + String.valueOf(Static.getId_deposito());

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
        String descricao_deposito ="";
        String valor_deposito ="";
        String praondefoi_deposito = "";
        String contaondeveio_deposito = "";
        String data_deposito = "";

        try
        {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(ConfigRetrieve.JSON_ARRAY);
            JSONObject collegeData = result.getJSONObject(0);

            descricao_deposito = collegeData.getString(ConfigRetrieve.DESCRICAO_DEPOSITO);
            valor_deposito = collegeData.getString(ConfigRetrieve.VALOR_DEPOSITO);
            praondefoi_deposito = collegeData.getString(ConfigRetrieve.PRAONDEFOI_DEPOSITO);
            contaondeveio_deposito = collegeData.getString(ConfigRetrieve.CONTAONDEVEIO_DEPOSITO);
            data_deposito = collegeData.getString(ConfigRetrieve.DATA_DEPOSITO);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        //region COLOCANDO OS CAMPOS NA TELA
        alterardescricaodeposito.setText(descricao_deposito);
        alterarondefoideposito.setText(valor_deposito);
        alterarvalordeposito.setText(praondefoi_deposito);
        alterardeondeveiodeposito.setText(contaondeveio_deposito);
        alterardatadeposito.setText(data_deposito);
        // getData1();
        //endregion


    }

    //endregion

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
