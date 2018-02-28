package com.f22labs.instalikefragmenttransaction.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Config;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleAdapter;
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
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.ButterKnife;


public class CadastroConsorcioNakasone extends BaseFragment implements Spinner.OnItemSelectedListener
{

    EditText descricaoconsorcio,contadoconsorcio,valorconsorcio,pagamentoconsorcio,dataconsorcio;

    Button salvarconsorcio;

    private String current = "";
    private Spinner spinner;
    private ArrayList<String> students;
    private ArrayList<String> ids;
    private JSONArray result;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void insert(){

        String  descricao_consorcio = descricaoconsorcio.getText().toString();
        String  valor_consorcio = valorconsorcio.getText().toString();
        String  conta_consorcio = contadoconsorcio.getText().toString();
        String  comofoipago_consorcio = pagamentoconsorcio.getText().toString();
        String  data_consorcio = dataconsorcio.getText().toString();



        insertToDatabase(descricao_consorcio, valor_consorcio, conta_consorcio, comofoipago_consorcio, data_consorcio);

    }

    private void insertToDatabase(String descricao_consorcio, String valor_consorcio,String conta_consorcio,String comofoipago_consorcio, String data_consorcio){
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {
                String paramdescricao_consorcio = params[0];
                String paramvalor_consorcio = params[1];
                String paramconta_consorcio = params[2];
                String paramcomofoipago_consorcio = params[3];
                String paramdata_consorcio = params[4];


                //InputStream is = null;

                String  descricao_consorcio = descricaoconsorcio.getText().toString();
                String  valor_consorcio = valorconsorcio.getText().toString();
                String  conta_consorcio = contadoconsorcio.getText().toString();
                String  comofoipago_consorcio = pagamentoconsorcio.getText().toString();
                String  data_consorcio = dataconsorcio.getText().toString();

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("descricao_consorcio", descricao_consorcio));
                nameValuePairs.add(new BasicNameValuePair("valor_consorcio", valor_consorcio));
                nameValuePairs.add(new BasicNameValuePair("conta_consorcio", conta_consorcio));
                nameValuePairs.add(new BasicNameValuePair("comofoipago_consorcio", comofoipago_consorcio));
                nameValuePairs.add(new BasicNameValuePair("data_consorcio", data_consorcio));


                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "http://premiumcontrol.com.br/NakasoneSoftapp/cadastro_consorcio_nakasone.php");
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
        sendPostReqAsyncTask.execute(descricao_consorcio, valor_consorcio, conta_consorcio, comofoipago_consorcio, data_consorcio);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_cadastro_consorcio, container, false);
        //region FindViews
        descricaoconsorcio = (EditText) view.findViewById(R.id.descricaoconsorcio);
        contadoconsorcio = (EditText) view.findViewById(R.id.contadoconsorcio);
        valorconsorcio = (EditText) view.findViewById(R.id.valorconsorcio);
        pagamentoconsorcio = (EditText) view.findViewById(R.id.pagamentoconsorcio);
        dataconsorcio = (EditText) view.findViewById(R.id.dataconsorcio);
        salvarconsorcio = (Button) view.findViewById(R.id.rsalvarconsorcio);
        students = new ArrayList<String>();
        ids = new ArrayList<String>();
        spinner = (Spinner) view.findViewById(R.id.spinner1);
        spinner.setOnItemSelectedListener(this);
        //endregion
        //region Máscaras
        dataconsorcio.addTextChangedListener(MaskEditUtil.mask(dataconsorcio, MaskEditUtil.FORMAT_DATE));

        valorconsorcio.addTextChangedListener(new MoneyTextWatcher(valorconsorcio));
        //endregion
        //region Clique do Botão
        salvarconsorcio.setOnClickListener(new View.OnClickListener()
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

        ( (MainActivity)getActivity()).updateToolbarTitle("Lançamento de consórcio");
        //endregion
        getData();


        return view;
    }









    //region Spinner
    private void getData(){
        StringRequest stringRequest = new StringRequest("http://premiumcontrol.com.br/NakasoneSoftapp/select/testebusca.php",
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
        for(int i=0;i<j.length();i++){
            try {
                JSONObject json = j.getJSONObject(i);
                students.add(json.getString("nome_grupo"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        spinner.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, students));
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    //endregion

}
