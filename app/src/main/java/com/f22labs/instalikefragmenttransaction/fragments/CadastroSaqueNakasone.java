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


public class CadastroSaqueNakasone extends BaseFragment implements Spinner.OnItemSelectedListener
{

    EditText descricaosaque,ondefoisaque,valorsaque,ondeveiosaque,datasaque;

    Button salvarsaque;

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

    public void insert()
    {

        String  descricao_saque = descricaosaque.getText().toString();
        String  valor_saque = ondefoisaque.getText().toString();
        String  praondefoi_saque = valorsaque.getText().toString();
        String  contaondeveio_saque = id_spinner.toString();
        String  data_saque = datasaque.getText().toString();



        insertToDatabase(descricao_saque, valor_saque, praondefoi_saque, contaondeveio_saque, data_saque);

    }


    private void insertToDatabase(String descricao_saque, String valor_saque,String praondefoi_saque,String contaondeveio_saque, String data_saque){
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {
                String paramdescricao_saque = params[0];
                String paramvalor_saque = params[1];
                String parampraondefoi_saque = params[2];
                String paramcontaondeveio_saque = params[3];
                String paramdata_saque = params[4];


                //InputStream is = null;

                String  descricao_saque = descricaosaque.getText().toString();
                String  valor_saque = ondefoisaque.getText().toString();
                String  praondefoi_saque = valorsaque.getText().toString();
                String  contaondeveio_saque = id_spinner.toString();
                String  data_saque = datasaque.getText().toString();

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("descricao_saque", descricao_saque));
                nameValuePairs.add(new BasicNameValuePair("valor_saque", valor_saque));
                nameValuePairs.add(new BasicNameValuePair("praondefoi_saque", praondefoi_saque));
                nameValuePairs.add(new BasicNameValuePair("contaondeveio_saque", contaondeveio_saque));
                nameValuePairs.add(new BasicNameValuePair("data_saque", data_saque));


                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "http://premiumcontrol.com.br/NakasoneSoftapp/cadastro_saque_nakasone.php");
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
        sendPostReqAsyncTask.execute(descricao_saque, valor_saque, praondefoi_saque, contaondeveio_saque, data_saque);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_cadastro_saque, container, false);
        //region FindViews
        descricaosaque = (EditText) view.findViewById(R.id.descricaosaque);
        ondefoisaque = (EditText) view.findViewById(R.id.ondefoisaque);
        valorsaque = (EditText) view.findViewById(R.id.valorsaque);
        ondeveiosaque = (EditText) view.findViewById(R.id.ondeveiosaque);
        datasaque = (EditText) view.findViewById(R.id.datasaque);
        salvarsaque = (Button) view.findViewById(R.id.salvarsaque);
        valorsaque.addTextChangedListener(new MoneyTextWatcher(valorsaque));
        students = new ArrayList<String>();
        ids = new ArrayList<String>();
        spinner = (Spinner) view.findViewById(R.id.spinner_saque);
        spinner.setOnItemSelectedListener(this);
        //endregion
        //region Máscara da data
        datasaque.addTextChangedListener(MaskEditUtil.mask(datasaque, MaskEditUtil.FORMAT_DATE));
        //endregion
        //region Clique do Botão
        salvarsaque.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                insert();
            }
        });
        //endregion
        //region Outros
        ButterKnife.bind(this, view);

        ( (MainActivity)getActivity()).updateToolbarTitle("Lançamento de Saque");

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
