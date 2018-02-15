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


public class CadastroDepositoNakasone extends BaseFragment implements Spinner.OnItemSelectedListener
{

    EditText descricaodeposito,ondefoideposito,valordeposito,deondeveiodeposito,datadeposito;

    Button salvardeposito;

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

        String  descricao_deposito = descricaodeposito.getText().toString();
        String  valor_deposito = valordeposito.getText().toString();
        String  praondefoi_deposito = id_spinner.toString();
        String  contaondeveio_deposito = deondeveiodeposito.getText().toString();
        String  data_deposito = datadeposito.getText().toString();



        insertToDatabase(descricao_deposito, valor_deposito, praondefoi_deposito, contaondeveio_deposito, data_deposito);

    }

    private void insertToDatabase(String descricao_deposito, String valor_deposito,String praondefoi_deposito,String contaondeveio_deposito, String data_deposito){
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {
                String paramdescricao_deposito = params[0];
                String paramvalor_deposito = params[1];
                String parampraondefoi_deposito = params[2];
                String paramcontaondeveio_deposito = params[3];
                String paramdata_deposito = params[4];


                //InputStream is = null;

                String  descricao_deposito = descricaodeposito.getText().toString();
                String  valor_deposito = valordeposito.getText().toString();
                String  praondefoi_deposito = id_spinner.toString();
                String  contaondeveio_deposito = deondeveiodeposito.getText().toString();
                String  data_deposito = datadeposito.getText().toString();

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("descricao_deposito", descricao_deposito));
                nameValuePairs.add(new BasicNameValuePair("valor_deposito", valor_deposito));
                nameValuePairs.add(new BasicNameValuePair("praondefoi_deposito", praondefoi_deposito));
                nameValuePairs.add(new BasicNameValuePair("contaondeveio_deposito", contaondeveio_deposito));
                nameValuePairs.add(new BasicNameValuePair("data_deposito", data_deposito));


                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "http://premiumcontrol.com.br/NakasoneSoftapp/cadastro_deposito_nakasone.php");
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
        sendPostReqAsyncTask.execute(descricao_deposito, valor_deposito, praondefoi_deposito, contaondeveio_deposito, data_deposito);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_cadastro_deposito, container, false);

        //region FindViews
        descricaodeposito = (EditText) view.findViewById(R.id.descricaodeposito);
        ondefoideposito = (EditText) view.findViewById(R.id.ondefoideposito);
        valordeposito = (EditText) view.findViewById(R.id.valordeposito);
        deondeveiodeposito = (EditText) view.findViewById(R.id.deondeveiodeposito);
        datadeposito = (EditText) view.findViewById(R.id.datadeposito);
        salvardeposito = (Button) view.findViewById(R.id.salvardeposito);
        students = new ArrayList<String>();
        ids = new ArrayList<String>();
        spinner = (Spinner) view.findViewById(R.id.spinner_deposito);
        spinner.setOnItemSelectedListener(this);
        //endregion
        //region Máscara
        datadeposito.addTextChangedListener(MaskEditUtil.mask(datadeposito, MaskEditUtil.FORMAT_DATE));
        valordeposito.addTextChangedListener(new MoneyTextWatcher(valordeposito));
        //endregion
        //region Clique do Botão
        salvardeposito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                insert();
            }
        });
        //endregion
        //region Outros
        ButterKnife.bind(this, view);
        ( (MainActivity)getActivity()).updateToolbarTitle("Lançamento de Depósito");
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