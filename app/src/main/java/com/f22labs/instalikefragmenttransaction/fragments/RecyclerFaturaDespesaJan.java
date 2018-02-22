package com.f22labs.instalikefragmenttransaction.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.f22labs.instalikefragmenttransaction.R;
import com.f22labs.instalikefragmenttransaction.activities.MainActivity;
import com.f22labs.instalikefragmenttransaction.adapters.GetDataAdapter;
import com.f22labs.instalikefragmenttransaction.adapters.RecyclerViewAdapter;
import com.f22labs.instalikefragmenttransaction.adapters.RecyclerViewAdapterDespesa;
import com.f22labs.instalikefragmenttransaction.interfaces.RecyclerViewOnClickListenerHack;
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
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import butterknife.ButterKnife;

public class RecyclerFaturaDespesaJan extends BaseFragment implements RecyclerViewOnClickListenerHack
{
    TextView credito_jan;
    Button pagar_jan;
    ProgressDialog loading;
    String url, resposta;
    public static final String PREFS_JAN = "0";
    public static final String PREFS_FEV = "0";
    public static final String PREFS_MAR = "0";
    public static final String PREFS_ABR = "0";
    public static final String PREFS_MAI = "0";
    public static final String PREFS_JUN = "0";
    public static final String PREFS_JUL = "0";
    public static final String PREFS_AGO = "0";
    public static final String PREFS_SET= "0";
    public static final String PREFS_OUT = "0";
    public static final String PREFS_NOV = "0";
    public static final String PREFS_DEZ = "0";


    private FragmentTabHost mTabHost;
    private OnItemClickListener mListener;

    //region ClickListener
    public interface OnItemClickListener
    {
        public void onItemClick(View view, int position);
    }
    @Override
    public void onClickListener(View view, int position)
    {

    }

    @Override
    public void onLongPressClickListener(View view, int position)
    {

    }
    //endregion

    //region Recycler
    RecyclerViewAdapter mAdapter ;

    GestureDetector mGestureDetector;

    SwipeRefreshLayout mSwipeRefreshLayout;
    List<GetDataAdapter> GetDataAdapter1;

    RecyclerView recyclerView;

    RecyclerView.LayoutManager recyclerViewlayoutManager;

    RecyclerView.Adapter recyclerViewadapter;


    String GET_JSON_DATA_HTTP_URL = "http://premiumcontrol.com.br/NakasoneSoftapp/select/credito_jan.php";

    String JSON_id_despesas = "id_despesas";
    String JSON_descricao_despesas = "descricao_despesas";
    String JSON_conta_despesas = "conta_despesas";
    String JSON_valor_despesas = "valor_despesas";
    String JSON_comofoipago_despesas = "comofoipago_despesas";
    String JSON_data_despesas = "data_despesas";

    private final String KEY_RECYCLER_STATE = "recycler_state";

    private static Bundle mBundleRecyclerViewState;
    private static int dyb;

    JsonArrayRequest jsonArrayRequest ;

    RequestQueue requestQueue ;
    //endregion

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {

        View view = inflater.inflate(R.layout.fragment_recycler_fatura_jan, container, false);
        pagar_jan = (Button)view.findViewById(R.id.lançar) ;
        //region Jsons
        switch (Static.getMes())
        {
            case 0:  GET_JSON_DATA_HTTP_URL = "http://premiumcontrol.com.br/NakasoneSoftapp/select/credito_jan.php";
                url = "http://premiumcontrol.com.br/NakasoneSoftapp/select/credito_jan_total.php";
                ((MainActivity)getActivity()).updateToolbarTitle("Fatura de Janeiro");
                //region Jan
                SharedPreferences settings = getActivity().getSharedPreferences(PREFS_JAN, getActivity().MODE_PRIVATE);

                String jan = settings.getString("1", "");
                Log.d("Ronaldo", jan);

                if(jan.equals("false"))
                {
                    pagar_jan.setEnabled(false);
                }

                //endregion
            break;

            case 1:  GET_JSON_DATA_HTTP_URL = "http://premiumcontrol.com.br/NakasoneSoftapp/select/credito_fev.php";
                url = "http://premiumcontrol.com.br/NakasoneSoftapp/select/credito_fev_total.php";
                ((MainActivity)getActivity()).updateToolbarTitle("Fatura de Fevereiro");

                //region Fev
                SharedPreferences settingsfev = getActivity().getSharedPreferences(PREFS_FEV, getActivity().MODE_PRIVATE);

                String fev = settingsfev.getString("2", "");


                if(fev.equals("false"))
                {
                    pagar_jan.setEnabled(false);
                }


                //endregion
                break;

            case 2:  GET_JSON_DATA_HTTP_URL = "http://premiumcontrol.com.br/NakasoneSoftapp/select/credito_mar.php";
                url = "http://premiumcontrol.com.br/NakasoneSoftapp/select/credito_mar_total.php";
                ((MainActivity)getActivity()).updateToolbarTitle("Fatura de Março");

                //region MAR
                SharedPreferences settingsmar = getActivity().getSharedPreferences(PREFS_MAR, getActivity().MODE_PRIVATE);

                String mar = settingsmar.getString("3", "");


                if(mar.equals("false"))
                {
                    pagar_jan.setEnabled(false);
                }


                //endregion
            break;

            case 3:  GET_JSON_DATA_HTTP_URL = "http://premiumcontrol.com.br/NakasoneSoftapp/select/credito_abr.php";
                url = "http://premiumcontrol.com.br/NakasoneSoftapp/select/credito_abr_total.php";
                ((MainActivity)getActivity()).updateToolbarTitle("Fatura de Abril");
                //region ABR
                SharedPreferences settingsabr = getActivity().getSharedPreferences(PREFS_ABR, getActivity().MODE_PRIVATE);

                String abr = settingsabr.getString("4", "");


                if(abr.equals("false"))
                {
                    pagar_jan.setEnabled(false);
                }


                //endregion
            break;

            case 4:  GET_JSON_DATA_HTTP_URL = "http://premiumcontrol.com.br/NakasoneSoftapp/select/credito_mai.php";
                url = "http://premiumcontrol.com.br/NakasoneSoftapp/select/credito_mai_total.php";
                ((MainActivity)getActivity()).updateToolbarTitle("Fatura de Maio");
                //region MAio
                SharedPreferences settingsmai = getActivity().getSharedPreferences(PREFS_MAI, getActivity().MODE_PRIVATE);

                String mai = settingsmai.getString("5", "");


                if(mai.equals("false"))
                {
                    pagar_jan.setEnabled(false);
                }


                //endregion
                break;

            case 5:  GET_JSON_DATA_HTTP_URL = "http://premiumcontrol.com.br/NakasoneSoftapp/select/credito_jun.php";
                url = "http://premiumcontrol.com.br/NakasoneSoftapp/select/credito_jun_total.php";
                ((MainActivity)getActivity()).updateToolbarTitle("Fatura de Junho");
                //region Jun
                SharedPreferences settingsjun = getActivity().getSharedPreferences(PREFS_JUN, getActivity().MODE_PRIVATE);

                String jun = settingsjun.getString("6", "");


                if(jun.equals("false"))
                {
                    pagar_jan.setEnabled(false);
                }


                //endregion
                break;

            case 6:  GET_JSON_DATA_HTTP_URL = "http://premiumcontrol.com.br/NakasoneSoftapp/select/credito_jul.php";
                url = "http://premiumcontrol.com.br/NakasoneSoftapp/select/credito_jul_total.php";
                ((MainActivity)getActivity()).updateToolbarTitle("Fatura de Julho");
                //region Jul
                SharedPreferences settingsjul = getActivity().getSharedPreferences(PREFS_JUL, getActivity().MODE_PRIVATE);

                String jul = settingsjul.getString("7", "");


                if(jul.equals("false"))
                {
                    pagar_jan.setEnabled(false);
                }


                //endregion
                break;

            case 7:  GET_JSON_DATA_HTTP_URL = "http://premiumcontrol.com.br/NakasoneSoftapp/select/credito_ago.php";
                url = "http://premiumcontrol.com.br/NakasoneSoftapp/select/credito_ago_total.php";
                ((MainActivity)getActivity()).updateToolbarTitle("Fatura de Agosto");
                //region Ago
                SharedPreferences settingsago = getActivity().getSharedPreferences(PREFS_AGO, getActivity().MODE_PRIVATE);

                String ago = settingsago.getString("8", "");


                if(ago.equals("false"))
                {
                    pagar_jan.setEnabled(false);
                }


                //endregion
            break;

            case 8:  GET_JSON_DATA_HTTP_URL = "http://premiumcontrol.com.br/NakasoneSoftapp/select/credito_set.php";
                url = "http://premiumcontrol.com.br/NakasoneSoftapp/select/credito_set_total.php";
                ((MainActivity)getActivity()).updateToolbarTitle("Fatura de Setembro");
                //region SET
                SharedPreferences settingsset = getActivity().getSharedPreferences(PREFS_SET, getActivity().MODE_PRIVATE);

                String set = settingsset.getString("9", "");


                if(set.equals("false"))
                {
                    pagar_jan.setEnabled(false);
                }


                //endregion
            break;

            case 9:  GET_JSON_DATA_HTTP_URL = "http://premiumcontrol.com.br/NakasoneSoftapp/select/credito_out.php";
                url = "http://premiumcontrol.com.br/NakasoneSoftapp/select/credito_out_total.php";
                ((MainActivity)getActivity()).updateToolbarTitle("Fatura de Outubro");
                //region Out
                SharedPreferences settingsout = getActivity().getSharedPreferences(PREFS_OUT, getActivity().MODE_PRIVATE);

                String out = settingsout.getString("10", "");


                if(out.equals("false"))
                {
                    pagar_jan.setEnabled(false);
                }


                //endregion
            break;

            case 10:  GET_JSON_DATA_HTTP_URL = "http://premiumcontrol.com.br/NakasoneSoftapp/select/credito_nov.php";
                url = "http://premiumcontrol.com.br/NakasoneSoftapp/select/credito_nov_total.php";
                ((MainActivity)getActivity()).updateToolbarTitle("Fatura de Novembro");
                //region Nov
                SharedPreferences settingsnov = getActivity().getSharedPreferences(PREFS_NOV, getActivity().MODE_PRIVATE);

                String nov = settingsnov.getString("11", "");


                if(nov.equals("false"))
                {
                    pagar_jan.setEnabled(false);
                }


                //endregion
                break;

            case 11:  GET_JSON_DATA_HTTP_URL = "http://premiumcontrol.com.br/NakasoneSoftapp/select/credito_dez.php";
                url = "http://premiumcontrol.com.br/NakasoneSoftapp/select/credito_dez_total.php";
                ((MainActivity)getActivity()).updateToolbarTitle("Fatura de Dezembro");
                //region DEZ
                SharedPreferences settingsdez = getActivity().getSharedPreferences(PREFS_DEZ, getActivity().MODE_PRIVATE);

                String dez = settingsdez.getString("12", "");


                if(dez.equals("false"))
                {
                    pagar_jan.setEnabled(false);
                }


                //endregion
            break;
        }
        //endregion

        mSwipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.swifeRefreshfatura_jan);

        credito_jan = (TextView)view.findViewById(R.id.credito_jan);

        pagar_jan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert();
            }
        });


        //mListener = listener;

        GetDataAdapter1 = new ArrayList<>();

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_jan);

        recyclerView.setHasFixedSize(true);

        recyclerViewlayoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(recyclerViewlayoutManager);

        JSON_DATA_WEB_CALL();
        LogpesToDatabase();
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {


                GetDataAdapter1 = new ArrayList<>();

                recyclerView.setHasFixedSize(true);

                recyclerViewlayoutManager = new LinearLayoutManager(getActivity());

                recyclerView.setLayoutManager(recyclerViewlayoutManager);

                JSON_DATA_WEB_CALL();

            }
        });//region
        ButterKnife.bind(this, view);


        //endregion

        return view;
    }

    public void insert(){
        String  descricao_despesas = "Fatura";
        String  conta_despesas = "Fatura";
        String  valor_despesas = credito_jan.getText().toString();
        String  comofoipago_despesas = "Fatura";



        insertToDatabase(descricao_despesas, conta_despesas, valor_despesas, comofoipago_despesas);

    }

    private void insertToDatabase(String descricao_despesas, String conta_despesas,String valor_despesas,String comofoipago_despesas){
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {
                String paramdescricao_despesas = params[0];
                String paramconta_despesas = params[1];
                String paramvalor_despesas = params[2];
                String paramcomofoipago_despesas = params[3];


                //InputStream is = null;
                String  descricao_despesas = "Fatura";
                String  conta_despesas = "Fatura";
                String  valor_despesas = credito_jan.getText().toString();
                String  comofoipago_despesas = "Fatura";

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("descricao_despesas", descricao_despesas));
                nameValuePairs.add(new BasicNameValuePair("conta_despesas", conta_despesas));
                nameValuePairs.add(new BasicNameValuePair("valor_despesas", valor_despesas));
                nameValuePairs.add(new BasicNameValuePair("comofoipago_despesas", comofoipago_despesas));

                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "http://premiumcontrol.com.br/NakasoneSoftapp/cadastro_despesa_nakasone_new.php");
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
            protected void onPostExecute(String result)
            {
                super.onPostExecute(result);

                pagar_jan.setEnabled(false);
                //region Janeiro
                if(Static.getMes() == 0)
                {

                SharedPreferences settings = getActivity().getSharedPreferences(PREFS_JAN, Context.MODE_PRIVATE);

                SharedPreferences.Editor editor = settings.edit();

                editor.putString("1", "false");

                editor.commit();
                Log.d("Janeiro", PREFS_JAN);
                }
                //endregion

                //region Fevereiro
                if(Static.getMes() == 1)
                {

                    SharedPreferences settings = getActivity().getSharedPreferences(PREFS_FEV, Context.MODE_PRIVATE);

                    SharedPreferences.Editor editor = settings.edit();

                    editor.putString("2", "false");

                    editor.commit();
                    Log.d("Fevereiro", PREFS_FEV);
                }
                //endregion

                //region MArço
                if(Static.getMes() == 2)
                {

                    SharedPreferences settings = getActivity().getSharedPreferences(PREFS_MAR, Context.MODE_PRIVATE);

                    SharedPreferences.Editor editor = settings.edit();

                    editor.putString("3", "false");

                    editor.commit();
                    Log.d("Março", PREFS_MAR);
                }
                //endregion

                //region Abril
                if(Static.getMes() == 3)
                {

                    SharedPreferences settings = getActivity().getSharedPreferences(PREFS_ABR, Context.MODE_PRIVATE);

                    SharedPreferences.Editor editor = settings.edit();

                    editor.putString("4", "false");

                    editor.commit();
                    Log.d("Abril", PREFS_ABR);
                }
                //endregion

                //region Maio
                if(Static.getMes() == 4)
                {

                    SharedPreferences settings = getActivity().getSharedPreferences(PREFS_MAI, Context.MODE_PRIVATE);

                    SharedPreferences.Editor editor = settings.edit();

                    editor.putString("5", "false");

                    editor.commit();
                    Log.d("Maio", PREFS_MAI);
                }
                //endregion

                //region Junho
                if(Static.getMes() == 5)
                {

                    SharedPreferences settings = getActivity().getSharedPreferences(PREFS_JUN, Context.MODE_PRIVATE);

                    SharedPreferences.Editor editor = settings.edit();

                    editor.putString("6", "false");

                    editor.commit();
                    Log.d("Junho", PREFS_JUN);
                }
                //endregion

                //region Julho
                if(Static.getMes() == 6)
                {

                    SharedPreferences settings = getActivity().getSharedPreferences(PREFS_JUL, Context.MODE_PRIVATE);

                    SharedPreferences.Editor editor = settings.edit();

                    editor.putString("7", "false");

                    editor.commit();
                    Log.d("Julho", PREFS_JUL);
                }
                //endregion

                //region Agosto
                if(Static.getMes() == 7)
                {

                    SharedPreferences settings = getActivity().getSharedPreferences(PREFS_AGO, Context.MODE_PRIVATE);

                    SharedPreferences.Editor editor = settings.edit();

                    editor.putString("8", "false");

                    editor.commit();
                }
                //endregion

                //region Setembro
                if(Static.getMes() == 8)
                {

                    SharedPreferences settings = getActivity().getSharedPreferences(PREFS_SET, Context.MODE_PRIVATE);

                    SharedPreferences.Editor editor = settings.edit();

                    editor.putString("9", "false");

                    editor.commit();
                }
                //endregion

                //region Outubro
                if(Static.getMes() == 9)
                {

                    SharedPreferences settings = getActivity().getSharedPreferences(PREFS_OUT, Context.MODE_PRIVATE);

                    SharedPreferences.Editor editor = settings.edit();

                    editor.putString("10", "false");

                    editor.commit();
                }
                //endregion

                //region Novembro
                if(Static.getMes() == 10)
                {

                    SharedPreferences settings = getActivity().getSharedPreferences(PREFS_NOV, Context.MODE_PRIVATE);

                    SharedPreferences.Editor editor = settings.edit();

                    editor.putString("11", "false");

                    editor.commit();
                }
                //endregion

                //region Dezembro
                if(Static.getMes() == 11)
                {

                    SharedPreferences settings = getActivity().getSharedPreferences(PREFS_DEZ, Context.MODE_PRIVATE);

                    SharedPreferences.Editor editor = settings.edit();

                    editor.putString("12", "false");

                    editor.commit();
                }
                //endregion


                Toast.makeText(getActivity(), result, Toast.LENGTH_LONG).show();
                //TextView textViewResult = (TextView) findViewById(R.id.textViewResult);
                // textViewResult.setText("Inserted");
            }



        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(descricao_despesas, conta_despesas, valor_despesas, comofoipago_despesas);
    }

    //region Chamada no Host
    public void JSON_DATA_WEB_CALL()
    {

        jsonArrayRequest = new JsonArrayRequest(GET_JSON_DATA_HTTP_URL,

                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        JSON_PARSE_DATA_AFTER_WEBCALL(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        requestQueue = Volley.newRequestQueue(getActivity());

        requestQueue.add(jsonArrayRequest);
        mSwipeRefreshLayout.setRefreshing(true);




    }
    public void JSON_PARSE_DATA_AFTER_WEBCALL(JSONArray array){

        for(int i = 0; i<array.length(); i++) {

            final GetDataAdapter GetDataAdapter2 = new GetDataAdapter();

            JSONObject json = null;
            try {

                json = array.getJSONObject(i);
                GetDataAdapter2.setId_despesas(json.getString(JSON_id_despesas));
                GetDataAdapter2.setDescricao_despesas(json.getString(JSON_descricao_despesas));
                GetDataAdapter2.setConta_despesas(json.getString(JSON_conta_despesas));
                GetDataAdapter2.setValor_despesas(json.getString(JSON_valor_despesas));
                GetDataAdapter2.setComofoipago_despesas(json.getString(JSON_comofoipago_despesas));
                GetDataAdapter2.setData_despesas(json.getString(JSON_data_despesas));
                mSwipeRefreshLayout.setRefreshing(false);

            }
            catch (JSONException e)
            {

                e.printStackTrace();
            }
            GetDataAdapter1.add(GetDataAdapter2);
        }

        recyclerViewadapter = new RecyclerViewAdapterDespesa(GetDataAdapter1, getActivity());

        recyclerView.setAdapter(recyclerViewadapter);



    }

//endregion

    //region Login Normal
    private void LogpesToDatabase(){
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {

                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(url);


                    HttpResponse response = httpClient.execute(httpPost);

                    HttpEntity entity = response.getEntity();
                    BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

                    StringBuffer sb = new StringBuffer("");
                    String line="";

                    while ((line = in.readLine()) != null) {
                        sb.append(line + "");
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
                return resposta;


            }

            @Override
            protected void onPostExecute(String result)
            {
                super.onPostExecute(result);
                try {
                    credito_jan.setText(resposta);
                    mSwipeRefreshLayout.setRefreshing(false);
                }
                catch (Exception e){
                    Toast.makeText(getActivity(),"Por favor, recarregue a página para calcular seus dados", Toast.LENGTH_LONG);}

            }


        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute();

    }
    //endregion
}