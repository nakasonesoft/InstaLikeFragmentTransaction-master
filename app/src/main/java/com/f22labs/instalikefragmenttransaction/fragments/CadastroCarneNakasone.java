package com.f22labs.instalikefragmenttransaction.fragments;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
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
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;


public class CadastroCarneNakasone extends BaseFragment
{

    EditText descricaocarne,valorcarne,datacarne,parcelas;
    Button salvarcarne;


    AutoCompleteTextView auto1;
    List<String> responseList;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void insert(){

        String  descricao_carne = descricaocarne.getText().toString();
        String  valor_carne = valorcarne.getText().toString();
        String  datafinal_carne = datacarne.getText().toString();
        String  qntd_carne = parcelas.getText().toString();



        insertToDatabase(descricao_carne,valor_carne,datafinal_carne,qntd_carne);

    }

    private void insertToDatabase(String descricao_carne, String valor_carne,String datafinal_carne,String qntd_carne){
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {
                String paramdescricao_carne = params[0];
                String paramvalor_carne = params[1];
                String paramdatafinal_carne = params[2];
                String paramqntd_carne = params[3];

                //InputStream is = null;
                String  descricao_carne = descricaocarne.getText().toString();
                String  valor_carne = valorcarne.getText().toString();
                String  datafinal_carne = datacarne.getText().toString();
                String  qntd_carne = parcelas.getText().toString();

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("descricao_carne", descricao_carne));
                nameValuePairs.add(new BasicNameValuePair("valor_carne", valor_carne));
                nameValuePairs.add(new BasicNameValuePair("datafinal_carne", datafinal_carne));
                nameValuePairs.add(new BasicNameValuePair("qntd_carne", qntd_carne));

                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "http://premiumcontrol.com.br/NakasoneSoftapp/cadastro_carne.php");
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
        sendPostReqAsyncTask.execute(descricao_carne,valor_carne,datafinal_carne,qntd_carne);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_cadastro_carne, container, false);


        //region FindViews
        descricaocarne = (EditText) view.findViewById(R.id.descricaocarne);
        valorcarne = (EditText) view.findViewById(R.id.valorcarne);
        datacarne = (EditText) view.findViewById(R.id.datacarne);
        salvarcarne = (Button) view.findViewById(R.id.salvarcarne);
        parcelas = (EditText) view.findViewById(R.id.parcelas2);
        //endregion

        //region Máscaras
        datacarne.addTextChangedListener(MaskEditUtil.mask(datacarne, MaskEditUtil.FORMAT_DATE));
        valorcarne.addTextChangedListener(new MoneyTextWatcher(valorcarne));
        //endregion]


        //region Clique do Botão
        salvarcarne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                insert();
            }
        });
        //endregion

        //region Toldbar
        ButterKnife.bind(this, view);

        ( (MainActivity)getActivity()).updateToolbarTitle("Lançamento de Carnê");

        //endregion

        return view;
    }

}
