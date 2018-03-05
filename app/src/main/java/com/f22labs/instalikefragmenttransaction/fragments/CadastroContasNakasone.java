package com.f22labs.instalikefragmenttransaction.fragments;

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


public class CadastroContasNakasone extends BaseFragment implements Spinner.OnItemSelectedListener
{
    Button SalvarConta;

    EditText txtDescricaoConta,txtGrupoConta,txtSaldoInicialConta,datafechamentoConta;

    private String current = "";

    //region Spinner Variaveis
    private Spinner spinner;
    private ArrayList<String> students;
    private ArrayList<String> ids;
    private JSONArray result;
    static String id_spinner = "1";
    //endregion

    public void insert(){

        String  id_grupo = id_spinner.toString();
        String  nome_conta = txtDescricaoConta.getText().toString();
        String  saldoinicial_conta = txtSaldoInicialConta.getText().toString();
        String  datafechamento_conta = datafechamentoConta.getText().toString();



        insertToDatabase(id_grupo, nome_conta, saldoinicial_conta, datafechamento_conta);

    }

    private void insertToDatabase(String id_grupo, String nome_conta,String saldoinicial_conta, String datafechamento_conta){
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String>
        {
            @Override
            protected String doInBackground(String... params)
            {
                String paramid_grupo = params[0];
                String paramnome_conta = params[1];
                String paramsaldoinicial_conta = params[2];
                String paramdatafechamento_conta = params[3];

                String  id_grupo = id_spinner.toString();
                String  nome_conta = txtDescricaoConta.getText().toString();
                String  saldoinicial_conta = txtSaldoInicialConta.getText().toString();
                String  datafechamento_conta = datafechamentoConta.getText().toString();

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("id_grupo", id_grupo));
                nameValuePairs.add(new BasicNameValuePair("nome_conta", nome_conta));
                nameValuePairs.add(new BasicNameValuePair("saldoinicial_conta", saldoinicial_conta));
                nameValuePairs.add(new BasicNameValuePair("datafechamento_conta", datafechamento_conta));



                try
                {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "http://premiumcontrol.com.br/NakasoneSoftapp/cadastro_contas_nakasone.php");
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                    HttpResponse response = httpClient.execute(httpPost);

                    HttpEntity entity = response.getEntity();

                    //is = entity.getContent();

                }
                catch (ClientProtocolException e)
                {

                }
                catch (IOException e)
                {

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
        sendPostReqAsyncTask.execute(id_grupo, nome_conta, saldoinicial_conta, datafechamento_conta);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {

        View view = inflater.inflate(R.layout.fragment_cadastro_contas, container, false);
        //region FindViews
        txtDescricaoConta = (EditText) view.findViewById(R.id.txtDescricaoConta);
        txtGrupoConta = (EditText) view.findViewById(R.id.txtGrupoConta);
        txtSaldoInicialConta = (EditText) view.findViewById(R.id.txtSaldoInicialConta);
        datafechamentoConta = (EditText) view.findViewById(R.id.datafechamentoConta);
        SalvarConta = (Button) view.findViewById(R.id.SalvarConta);

//region declarar variaveis spinner
        students = new ArrayList<String>();
        ids = new ArrayList<String>();
        spinner = (Spinner) view.findViewById(R.id.spinner2);
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
        //endregion
        //endregion
        //region Outros
        ButterKnife.bind(this, view);

        ( (MainActivity)getActivity()).updateToolbarTitle("Lançamento de Contas");
        //endregion
        //region Clique do Botão
        SalvarConta.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                insert();
            }
        });
        //endregion
        getData();

        return view;
    }

    //region Spinner
    private void getData(){
        StringRequest stringRequest = new StringRequest("http://premiumcontrol.com.br/NakasoneSoftapp/teste1.php",
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
                students.add(json.getString("nome_conta"));
                ids.add(json.getString("id_conta"));
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
