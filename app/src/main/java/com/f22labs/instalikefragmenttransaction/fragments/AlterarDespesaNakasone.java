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
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.f22labs.instalikefragmenttransaction.R;
import com.f22labs.instalikefragmenttransaction.activities.MainActivity;
import com.f22labs.instalikefragmenttransaction.adapters.ConfigRetrieve;
import com.f22labs.instalikefragmenttransaction.adapters.ServerImageParseAdapter;
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


public class AlterarDespesaNakasone extends BaseFragment implements Spinner.OnItemSelectedListener
{
    private ProgressDialog loading;
    EditText alterardescricaodespesa,alterarvalordespesa,alterarcontadespesa,alterardatadespesa,alterarcomofoipagadespesa;

    Button alterarsalvardespesa;

    //region Spinner Variaveis
    private Spinner spinner;
    private ArrayList<String> students;

    ArrayList<String> ids;
    private JSONArray result;
    static String id_spinner;
    //endregion

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_alterar_despesa, container, false);

        //region FindViews
        alterardescricaodespesa = (EditText) view.findViewById(R.id.alterardescricaodespesa);
        alterarcontadespesa = (EditText) view.findViewById(R.id.alterarcontadespesa);
        alterarvalordespesa = (EditText) view.findViewById(R.id.alterarvalordespesa);
        alterardatadespesa = (EditText) view.findViewById(R.id.alterardatadespesa);
        alterarcomofoipagadespesa = (EditText) view.findViewById(R.id.alterarcomofoipagadespesa);
        alterarsalvardespesa = (Button) view.findViewById(R.id.alterarsalvardespesa);


        students = new ArrayList<String>();
        ids = new ArrayList<String>();
        spinner = (Spinner) view.findViewById(R.id.spinner_alterar_despesa);
        spinner.setOnItemSelectedListener(this);

        //endregion
        //region Máscaras
        alterardatadespesa.addTextChangedListener(MaskEditUtil.mask(alterardatadespesa, MaskEditUtil.FORMAT_DATE));
        alterarvalordespesa.addTextChangedListener(new MoneyTextWatcher(alterarvalordespesa));
        //endregion
        //region Clique do Botão
        alterarsalvardespesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                UpdateEvento();

            }
        });
        //endregion

        //region Outros
        ButterKnife.bind(this, view);

        ( (MainActivity)getActivity()).updateToolbarTitle("Alteração de Despesa");

        //endregion
        getData();
        getData1();
        return view;
    }


    //region Editar Dados
    public void UpdateEvento()
    {

        String descricao_despesas = alterardescricaodespesa.getText().toString();
        String conta_despesas = id_spinner.toString();
        String valor_despesas = alterarvalordespesa.getText().toString();
        String comofoipago_despesas = alterarcomofoipagadespesa.getText().toString();
        String data_despesas = alterardatadespesa.getText().toString();
        String id_despesas = String.valueOf(Static.getId_depesas()).trim();

        UpdateToDatabase(descricao_despesas,conta_despesas,valor_despesas,comofoipago_despesas,data_despesas,id_despesas);

    }

    private void UpdateToDatabase(String descricao_despesas, String conta_despesas, String valor_despesas, String comofoipago_despesas,String data_despesas, String id_despesas) {
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

                String descricao_despesas = alterardescricaodespesa.getText().toString();
                String conta_despesas = id_spinner.toString();
                String valor_despesas = alterarvalordespesa.getText().toString();
                String comofoipago_despesas = alterarcomofoipagadespesa.getText().toString();
                String data_despesas = alterardatadespesa.getText().toString();
                String id_despesas = String.valueOf(Static.getId_depesas()).trim();


                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("descricao_despesas", descricao_despesas));
                nameValuePairs.add(new BasicNameValuePair("conta_despesas", conta_despesas));
                nameValuePairs.add(new BasicNameValuePair("valor_despesas", valor_despesas));
                nameValuePairs.add(new BasicNameValuePair("comofoipago_despesas", comofoipago_despesas));
                nameValuePairs.add(new BasicNameValuePair("data_despesas", data_despesas));
                nameValuePairs.add(new BasicNameValuePair("id_despesas", id_despesas));



                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost("http://premiumcontrol.com.br/NakasoneSoftapp/update/alterar_despesa.php");
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
        sendPostReqAsyncTask.execute(descricao_despesas,conta_despesas,valor_despesas,comofoipago_despesas,data_despesas,id_despesas);
    }

    //endregion



    //region Pesquisa para mostrar nos campos
    private void getData1()
    {


        loading = ProgressDialog.show(getActivity(), "Carregando informações do evento!", "Por favor, aguarde", false, false);

        String url =  ConfigRetrieve.DATA_URL_DESPESAS + String.valueOf(Static.getId_depesas());

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
        String descricao_despesas ="";
        String conta_despesas ="";
        String valor_despesas = "";
        String comofoipago_despesas = "";
        String data_despesas = "";


        try
        {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(ConfigRetrieve.JSON_ARRAY);
            JSONObject collegeData = result.getJSONObject(0);

            descricao_despesas = collegeData.getString(ConfigRetrieve.DESCRICAO_DESPESA);
            conta_despesas = collegeData.getString(ConfigRetrieve.CONTA_DESPESA);
            valor_despesas = collegeData.getString(ConfigRetrieve.VALOR_DESPESAS);
            comofoipago_despesas = collegeData.getString(ConfigRetrieve.COMOFOIPAGO_DESPESAS);
            data_despesas = collegeData.getString(ConfigRetrieve.DATA_DESPESAS);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }



        //region COLOCANDO OS CAMPOS NA TELA
        alterardescricaodespesa.setText(descricao_despesas);
        alterarcontadespesa.setText(conta_despesas);
        alterarvalordespesa.setText(valor_despesas);
        alterarcomofoipagadespesa.setText(comofoipago_despesas);
        alterardatadespesa.setText(data_despesas);
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