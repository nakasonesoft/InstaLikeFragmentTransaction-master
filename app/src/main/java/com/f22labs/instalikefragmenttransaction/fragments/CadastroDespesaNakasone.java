package com.f22labs.instalikefragmenttransaction.fragments;

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
import com.f22labs.instalikefragmenttransaction.utils.MaskEditUtil;
import com.f22labs.instalikefragmenttransaction.utils.MoneyTextWatcher;

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


public class CadastroDespesaNakasone extends BaseFragment implements Spinner.OnItemSelectedListener
{

    EditText descricaodespesa,valordespesa,contadespesa,datadespesa,comofoipagadespesa;

    Button salvardespesa;


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

    public void insert(){

        String  descricao_despesas = descricaodespesa.getText().toString();
        String  conta_despesas = id_spinner.toString();
        String  valor_despesas = valordespesa.getText().toString();
        String  comofoipago_despesas = comofoipagadespesa.getText().toString();
        String  data_despesas = datadespesa.getText().toString();



        insertToDatabase(descricao_despesas, conta_despesas, valor_despesas, comofoipago_despesas, data_despesas);

    }

    private void insertToDatabase(String descricao_despesas, String conta_despesas,String valor_despesas,String comofoipago_despesas, String data_despesas){
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {
                String paramdescricao_despesas = params[0];
                String paramconta_despesas = params[1];
                String paramvalor_despesas = params[2];
                String paramcomofoipago_despesas = params[3];
                String paramdata_despesas = params[4];


                //InputStream is = null;

                String  descricao_despesas = descricaodespesa.getText().toString();
                String  conta_despesas = id_spinner.toString();
                String  valor_despesas = valordespesa.getText().toString();
                String  comofoipago_despesas = comofoipagadespesa.getText().toString();
                String  data_despesas = datadespesa.getText().toString();

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("descricao_despesas", descricao_despesas));
                nameValuePairs.add(new BasicNameValuePair("conta_despesas", conta_despesas));
                nameValuePairs.add(new BasicNameValuePair("valor_despesas", valor_despesas));
                nameValuePairs.add(new BasicNameValuePair("comofoipago_despesas", comofoipago_despesas));
                nameValuePairs.add(new BasicNameValuePair("data_despesas", data_despesas));


                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "http://premiumcontrol.com.br/NakasoneSoftapp/cadastro_despesa_nakasone.php");
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                    HttpResponse response = httpClient.execute(httpPost);

                    HttpEntity entity = response.getEntity();

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
                //TextView textViewResult = (TextView) findViewById(R.id.textViewResult);
                // textViewResult.setText("Inserted");
            }



        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(descricao_despesas, conta_despesas, valor_despesas, comofoipago_despesas, data_despesas);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_cadastro_despesa, container, false);

        //region FindViews
        descricaodespesa = (EditText) view.findViewById(R.id.descricaodespesa);
        contadespesa = (EditText) view.findViewById(R.id.contadespesa);
        valordespesa = (EditText) view.findViewById(R.id.valordespesa);
        datadespesa = (EditText) view.findViewById(R.id.datadespesa);
        comofoipagadespesa = (EditText) view.findViewById(R.id.comofoipagadespesa);
        salvardespesa = (Button) view.findViewById(R.id.salvardespesa);
        students = new ArrayList<String>();
        ids = new ArrayList<String>();
        spinner = (Spinner) view.findViewById(R.id.spinner4);
        spinner.setOnItemSelectedListener(this);

        //endregion
        //region Máscaras
        datadespesa.addTextChangedListener(MaskEditUtil.mask(datadespesa, MaskEditUtil.FORMAT_DATE));
        valordespesa.addTextChangedListener(new MoneyTextWatcher(valordespesa));
        //endregion
        //region Clique do Botão
        salvardespesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                insert();
            }
        });
        //endregion
        //region Outros
        ButterKnife.bind(this, view);

        ( (MainActivity)getActivity()).updateToolbarTitle("Lançamento de Despesa");

        //endregion
        getData();
        return view;

    }

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
       // students.add("");
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
        try
        {
            id_spinner = ids.get(position);
        }
        catch (Exception e)
        {

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent)
    {
        ids.get(spinner.getSelectedItemPosition());
    }
    //endregion

}
