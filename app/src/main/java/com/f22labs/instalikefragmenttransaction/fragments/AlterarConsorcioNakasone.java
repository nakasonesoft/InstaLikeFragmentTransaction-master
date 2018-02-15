package com.f22labs.instalikefragmenttransaction.fragments;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;


public class AlterarConsorcioNakasone extends BaseFragment implements Spinner.OnItemSelectedListener
{

    EditText alterardescricaoconsorcio,alterarcontadoconsorcio,alterarvalorconsorcio,alterarpagamentoconsorcio,alterardataconsorcio;

    Button alterarsalvarconsorcio;

    ProgressDialog loading;

    //region Spinner Variaveis
    private Spinner spinner;
    private ArrayList<String> students;

    ArrayList<String> ids;
    private JSONArray result;
    static String id_spinner;
    //endregion

    private String current = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_alterar_consorcio, container, false);
        //region FindViews
        alterardescricaoconsorcio = (EditText) view.findViewById(R.id.alterardescricaoconsorcio);
        alterarcontadoconsorcio = (EditText) view.findViewById(R.id.alterarcontadoconsorcio);
        alterarvalorconsorcio = (EditText) view.findViewById(R.id.alterarvalorconsorcio);
        alterarpagamentoconsorcio = (EditText) view.findViewById(R.id.alterarpagamentoconsorcio);
        alterardataconsorcio = (EditText) view.findViewById(R.id.alterardataconsorcio);
        alterarsalvarconsorcio = (Button) view.findViewById(R.id.alterarsalvarconsorcio);



        students = new ArrayList<String>();
        ids = new ArrayList<String>();
        spinner = (Spinner) view.findViewById(R.id.spinner_alterar_consorcio);
        spinner.setOnItemSelectedListener(this);


        //endregion
        //region Máscaras
        alterardataconsorcio.addTextChangedListener(MaskEditUtil.mask(alterardataconsorcio, MaskEditUtil.FORMAT_DATE));

        alterarvalorconsorcio.addTextChangedListener(new MoneyTextWatcher(alterarvalorconsorcio));
        //endregion
        //region Clique do Botão
        alterarsalvarconsorcio.setOnClickListener(new View.OnClickListener()
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
        getData();
        getData1();
        return view;
    }

    //region Editar Dados
    public void UpdateEvento()
    {

        String descricao_consorcio = alterardescricaoconsorcio.getText().toString();
        String valor_consorcio = alterarvalorconsorcio.getText().toString();
        String conta_consorcio = alterarcontadoconsorcio.getText().toString();
        String comofoipago_consorcio = alterarpagamentoconsorcio.getText().toString();
        String data_consorcio = alterardataconsorcio.getText().toString();
        String id_consorcio = String.valueOf(Static.getId_consorcio()).trim();

        UpdateToDatabase(descricao_consorcio, valor_consorcio, conta_consorcio, comofoipago_consorcio, data_consorcio,id_consorcio);

    }

    private void UpdateToDatabase(String descricao_consorcio, String valor_consorcio, String conta_consorcio, String comofoipago_consorcio,String data_consorcio, String id_consorcio) {
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


                String descricao_consorcio = alterardescricaoconsorcio.getText().toString();
                String valor_consorcio = alterarvalorconsorcio.getText().toString();
                String conta_consorcio = alterarcontadoconsorcio.getText().toString();
                String comofoipago_consorcio = alterarpagamentoconsorcio.getText().toString();
                String data_consorcio = alterardataconsorcio.getText().toString();
                String id_consorcio = String.valueOf(Static.getId_consorcio()).trim();


                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("descricao_consorcio", descricao_consorcio));
                nameValuePairs.add(new BasicNameValuePair("valor_consorcio", valor_consorcio));
                nameValuePairs.add(new BasicNameValuePair("conta_consorcio", conta_consorcio));
                nameValuePairs.add(new BasicNameValuePair("comofoipago_consorcio", comofoipago_consorcio));
                nameValuePairs.add(new BasicNameValuePair("data_consorcio", data_consorcio));
                nameValuePairs.add(new BasicNameValuePair("id_consorcio", id_consorcio));



                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost("http://premiumcontrol.com.br/NakasoneSoftapp/update/alterar_consorcio.php");
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
        sendPostReqAsyncTask.execute(descricao_consorcio, valor_consorcio, conta_consorcio, comofoipago_consorcio, data_consorcio,id_consorcio);
    }

    //endregion

    //region Pesquisas para mostrar nos campos
    private void getData1()
    {

        loading = ProgressDialog.show(getActivity(), "Carregando informações do Consórcio!", "Por favor, aguarde", false, false);

        String url =  ConfigRetrieve.DATA_URL_CONSORCIO + String.valueOf(Static.getId_consorcio());

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
        String descricao_consorcio ="";
        String valor_consorcio ="";
        String conta_consorcio = "";
        String comofoipago_consorcio = "";
        String data_consorcio = "";

        try
        {
            JSONObject jsonObject = new JSONObject(response);

            JSONArray result = jsonObject.getJSONArray(ConfigRetrieve.JSON_ARRAY);

            JSONObject collegeData = result.getJSONObject(0);

            descricao_consorcio = collegeData.getString(ConfigRetrieve.DESCRICAO_CONSORCIO);
            valor_consorcio = collegeData.getString(ConfigRetrieve.VALOR_CONSORCIO);
            conta_consorcio = collegeData.getString(ConfigRetrieve.CONTA_CONSORCIO);
            comofoipago_consorcio = collegeData.getString(ConfigRetrieve.COMOFOIPAGO_CONSORCIO);
            data_consorcio = collegeData.getString(ConfigRetrieve.DATA_CONSORCIO);

        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        //region COLOCANDO OS CAMPOS NA TELA
        alterardescricaoconsorcio.setText(descricao_consorcio);
        alterarcontadoconsorcio.setText(conta_consorcio);
        alterarvalorconsorcio.setText(valor_consorcio);
        alterarpagamentoconsorcio.setText(comofoipago_consorcio);
        alterardataconsorcio.setText(data_consorcio);

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
