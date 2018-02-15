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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class AlterarOutrosNakasone extends BaseFragment
{
    EditText alterardescricaoOutros,alterarcontadoOutros,alterarvalorOutros,alterarcontaOutros2,alterardataOutros;

    Button alterarsalvarOutros;

    ProgressDialog loading;

    //region Spinner Variaveis
    private Spinner spinner;
    private ArrayList<String> students;

    ArrayList<String> ids;
    private JSONArray result;
    static String id_spinner;
    //endregion

    //region Spinner Variaveis2
    private Spinner spinner2;
    private ArrayList<String> students2;

    ArrayList<String> ids2;
    private JSONArray result2;
    static String id_spinner2;
    //endregion

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_alterar_outros, container, false);

        //region FindViews
        alterardescricaoOutros = (EditText) view.findViewById(R.id.alterardescricaoOutros);
        alterarcontadoOutros = (EditText) view.findViewById(R.id.alterarcontadoOutros);
        alterarvalorOutros = (EditText) view.findViewById(R.id.alterarvalorOutros);
        alterarcontaOutros2 = (EditText) view.findViewById(R.id.alterarcontaOutros2);
        alterardataOutros = (EditText) view.findViewById(R.id.alterardataOutros);
        alterarsalvarOutros = (Button) view.findViewById(R.id.alterarsalvarOutros);
        //endregion

        students = new ArrayList<String>();
        ids = new ArrayList<String>();
        spinner = (Spinner) view.findViewById(R.id.spinner_alterar_outros1);
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

        students2 = new ArrayList<String>();
        ids2 = new ArrayList<String>();
        spinner2 = (Spinner) view.findViewById(R.id.spinner_alterar_outros2);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
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


        //region Máscaras
        alterarvalorOutros.addTextChangedListener(new MoneyTextWatcher(alterarvalorOutros));
        alterardataOutros.addTextChangedListener(MaskEditUtil.mask(alterardataOutros, MaskEditUtil.FORMAT_DATE));
        //endregion
        //region Clique do botão
        alterarsalvarOutros.setOnClickListener(new View.OnClickListener()
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

        ( (MainActivity)getActivity()).updateToolbarTitle("Alteração de Outros");
     //endregion
        getData1();
        getData();
        getData2();
        Log.d("ID",String.valueOf(Static.getId_outros()));
        return view;
    }

    //region Editar Dados
    public void UpdateEvento()
    {

        String descricao_outros = alterardescricaoOutros.getText().toString();
        String id_grupo = id_spinner.toString();
        String id_grupo2 = id_spinner2.toString();
        String valor_outros = alterarvalorOutros.getText().toString();
        String data_outros = alterardataOutros.getText().toString();
        String id_outros = String.valueOf(Static.getId_outros()).trim();

        UpdateToDatabase(descricao_outros,id_grupo,id_grupo2,valor_outros,data_outros,id_outros);

    }

    private void UpdateToDatabase(String descricao_outros, String id_grupo, String id_grupo2, String valor_outros,String data_outros, String id_outros) {
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

                String descricao_outros = alterardescricaoOutros.getText().toString();
                String id_grupo = id_spinner.toString();
                String id_grupo2 = id_spinner2.toString();
                String valor_outros = alterarvalorOutros.getText().toString();
                String data_outros = alterardataOutros.getText().toString();
                String id_outros = String.valueOf(Static.getId_outros()).trim();

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("descricao_outros", descricao_outros));
                nameValuePairs.add(new BasicNameValuePair("id_grupo", id_grupo));
                nameValuePairs.add(new BasicNameValuePair("id_grupo2", id_grupo2));
                nameValuePairs.add(new BasicNameValuePair("valor_outros", valor_outros));
                nameValuePairs.add(new BasicNameValuePair("data_outros", data_outros));
                nameValuePairs.add(new BasicNameValuePair("id_outros", id_outros));



                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost("http://premiumcontrol.com.br/NakasoneSoftapp/update/alterar_outros.php");
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
        sendPostReqAsyncTask.execute(descricao_outros,id_grupo,id_grupo2,valor_outros,data_outros,id_outros);
    }

    //endregion

    //region Pesquisa para mostrar nos campos
    private void getData1()
    {

        loading = ProgressDialog.show(getActivity(), "Carregando informações do evento!", "Por favor, aguarde", false, false);

        String url =  ConfigRetrieve.DATA_URL_OUTROS + String.valueOf(Static.getId_outros());

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
        String descricao_outros ="";

        String id_grupo ="";

        String id_grupo2 = "";

        String valor_outros = "";

        String data_outros = "";

        try
        {
             JSONObject jsonObject = new JSONObject(response);

            JSONArray result = jsonObject.getJSONArray(ConfigRetrieve.JSON_ARRAY);

            JSONObject collegeData = result.getJSONObject(0);

            descricao_outros = collegeData.getString(ConfigRetrieve.DESCRICAO_OUTROS);
            id_grupo = collegeData.getString(ConfigRetrieve.ID_GRUPO);
            id_grupo2 = collegeData.getString(ConfigRetrieve.ID_GRUPO2);
            valor_outros = collegeData.getString(ConfigRetrieve.VALOR_OUTROS);
            data_outros = collegeData.getString(ConfigRetrieve.DATA_OUTROS);

        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        //region COLOCANDO OS CAMPOS NA TELA
        alterardescricaoOutros.setText(descricao_outros);
        alterarcontadoOutros.setText(id_grupo);
        alterarvalorOutros.setText(valor_outros);
        alterarcontaOutros2.setText(id_grupo2);
        alterardataOutros.setText(data_outros);

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
}