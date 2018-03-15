package com.f22labs.instalikefragmenttransaction.fragments;

import android.app.ProgressDialog;
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
import com.f22labs.instalikefragmenttransaction.adapters.RecyclerViewAdapterFicha;
import com.f22labs.instalikefragmenttransaction.interfaces.RecyclerViewOnClickListenerHack;
import com.f22labs.instalikefragmenttransaction.utils.Static;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class RecyclerFicha extends BaseFragment implements RecyclerViewOnClickListenerHack
{

    ProgressDialog loading;
    String url, resposta, gasto_url, resposta1;
    TextView gasto, saldo;

    private FragmentTabHost mTabHost;
    private OnItemClickListener mListener;
    private static int opcao = Static.getFicha();






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


    String GET_JSON_DATA_HTTP_URL = "http://premiumcontrol.com.br/NakasoneSoftapp/select/diario_despesa.php?id_cliente="+Static.getId_cliente()+"";

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

        View view = inflater.inflate(R.layout.fragment_recycler_ficha, container, false);

        saldo = (TextView)view.findViewById(R.id.saldo);
        gasto = (TextView)view.findViewById(R.id.gasto);

        //region Jsons

        switch (Static.getFicha())
        {
            case 1:  GET_JSON_DATA_HTTP_URL = "http://premiumcontrol.com.br/NakasoneSoftapp/select/diario_receita.php?id_cliente="+Static.getId_cliente()+"";

                ((MainActivity)getActivity()).updateToolbarTitle("Ficha individual de Receita");
                break;

            case 2:  GET_JSON_DATA_HTTP_URL = "http://premiumcontrol.com.br/NakasoneSoftapp/select/diario_transferencia.php?id_cliente="+Static.getId_cliente()+"";

                ((MainActivity)getActivity()).updateToolbarTitle("Ficha individual de Transferência");

                break;

            case 3:  GET_JSON_DATA_HTTP_URL = "http://premiumcontrol.com.br/NakasoneSoftapp/select/diario_saque.php?id_cliente="+Static.getId_cliente()+"";

                ((MainActivity)getActivity()).updateToolbarTitle("Ficha individual de Saque");

                break;
            case 4:  GET_JSON_DATA_HTTP_URL = "http://premiumcontrol.com.br/NakasoneSoftapp/select/diario_deposito.php?id_cliente="+Static.getId_cliente()+"";

                ((MainActivity)getActivity()).updateToolbarTitle("Ficha individual de Depósito");

                break;

            case 5:  GET_JSON_DATA_HTTP_URL = "http://premiumcontrol.com.br/NakasoneSoftapp/select/diario_fatura.php?id_cliente="+Static.getId_cliente()+"";

                ((MainActivity)getActivity()).updateToolbarTitle("Ficha individual de Depósito");

                break;

            case 7:  GET_JSON_DATA_HTTP_URL = " http://premiumcontrol.com.br/NakasoneSoftapp/select/diario_imoveis.php?id_cliente="+Static.getId_cliente()+"";

                ((MainActivity)getActivity()).updateToolbarTitle("Ficha individual de Prestação de Imóveis");

                break;

            case 8:  GET_JSON_DATA_HTTP_URL = " http://premiumcontrol.com.br/NakasoneSoftapp/select/diario_consorcio.php?id_cliente="+Static.getId_cliente()+"";

                ((MainActivity)getActivity()).updateToolbarTitle("Ficha individual de Consórcio");

                break;

            case 9:  GET_JSON_DATA_HTTP_URL = " http://premiumcontrol.com.br/NakasoneSoftapp/select/diario_outros.php?id_cliente="+Static.getId_cliente()+"";

                ((MainActivity)getActivity()).updateToolbarTitle("Ficha individual Outros");

                break;



        }
        //endregion

        mSwipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.swifeRefreshficha);

        //mListener = listener;

        GetDataAdapter1 = new ArrayList<>();

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_ficha);

        recyclerView.setHasFixedSize(true);

        recyclerViewlayoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(recyclerViewlayoutManager);

        switch (Static.getFicha()){
            case 0:
                gasto_url = "http://premiumcontrol.com.br/NakasoneSoftapp/select/sum_despesa.php?id_cliente="+Static.getId_cliente()+"";
                break;
            case 1:
                gasto_url = "http://premiumcontrol.com.br/NakasoneSoftapp/select/sum_receita.php?id_cliente="+Static.getId_cliente()+"";
                break;
            case 2:
                gasto_url = "http://premiumcontrol.com.br/NakasoneSoftapp/select/sum_transferencia.php?id_cliente="+Static.getId_cliente()+"";
                break;
            case 3:
                gasto_url = "http://premiumcontrol.com.br/NakasoneSoftapp/select/sum_saque.php?id_cliente="+Static.getId_cliente()+"";
                break;
            case 4:
                gasto_url = "http://premiumcontrol.com.br/NakasoneSoftapp/select/sum_deposito.php?id_cliente="+Static.getId_cliente()+"";
                break;
            case 5:
                gasto_url = "http://premiumcontrol.com.br/NakasoneSoftapp/select/sum_fatura.php?id_cliente="+Static.getId_cliente()+"";
                break;
            case 6:
                gasto_url = "http://premiumcontrol.com.br/NakasoneSoftapp/select/sum_carne.php?id_cliente="+Static.getId_cliente()+"";
                break;
            case 7:
                gasto_url = "http://premiumcontrol.com.br/NakasoneSoftapp/select/sum_imoveis.php?id_cliente="+Static.getId_cliente()+"";
                break;
            case 8:
                gasto_url = "http://premiumcontrol.com.br/NakasoneSoftapp/select/sum_consorcio.php?id_cliente="+Static.getId_cliente()+"";
                break;
            case 9:
                gasto_url = "http://premiumcontrol.com.br/NakasoneSoftapp/select/sum_outros.php?id_cliente="+Static.getId_cliente()+"";
                break;
        }

        JSON_DATA_WEB_CALL();
        LogpesToDatabase();
        LogpesToDatabase1();
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {


                GetDataAdapter1 = new ArrayList<>();

                recyclerView.setHasFixedSize(true);

                recyclerViewlayoutManager = new LinearLayoutManager(getActivity());

                recyclerView.setLayoutManager(recyclerViewlayoutManager);

                JSON_DATA_WEB_CALL();
                LogpesToDatabase();
                LogpesToDatabase1();


            }
        });//region
        ButterKnife.bind(this, view);


        //endregion

        return view;
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

        recyclerViewadapter = new RecyclerViewAdapterFicha(GetDataAdapter1, getActivity());

        recyclerView.setAdapter(recyclerViewadapter);



    }

//endregion

    private void LogpesToDatabase(){
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {


            @Override
            protected String doInBackground(String... params) {

                try {

                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "http://premiumcontrol.com.br/NakasoneSoftapp/visao_geral.php?id_cliente="+Static.getId_cliente()+"");


                    HttpResponse response = httpClient.execute(httpPost);

                    HttpEntity entity = response.getEntity();
                    BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

                    StringBuffer sb = new StringBuffer("");
                    String line="";
                    while ((line = in.readLine()) != null) {
                        sb.append(line + "\n");
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


            protected String doInBackground1(String... params) {

                try {

                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(gasto_url);


                    HttpResponse response = httpClient.execute(httpPost);

                    HttpEntity entity = response.getEntity();
                    BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

                    StringBuffer sb = new StringBuffer("");
                    String line="";
                    while ((line = in.readLine()) != null) {
                        sb.append(line + "\n");
                        break;
                    }
                    in.close();
                    resposta1 = sb.toString();
                    Log.d("TAG", resposta1);
                    return sb.toString();

                    //is = entity.getContent();


                } catch (ClientProtocolException e) {

                } catch (IOException e) {

                }
                return resposta1;


            }

            @Override
            protected void onPostExecute(String result)
            {
                super.onPostExecute(result);
                try {

                    String ronaldo = result.substring(0, result.indexOf("<br>"));
                    String felipe = result.replace(ronaldo, "");
                    String alexandre = felipe.replace("<br>", "");
                    String Pablo = alexandre.substring(0, alexandre.indexOf("/"));
                    String Vittar = result.replace(ronaldo, "");
                    String Gabriel = Vittar.replace(Pablo, "");
                    String Guilherme = Gabriel.replace("<br>/", "");
                    String Reginaldo = resposta.substring(resposta.lastIndexOf("/") + 1);
                    Log.d("FOI LEK", ronaldo);
                    Log.d("FOI LEK", Pablo);
                    Log.d("Resultado", Guilherme);
                    Log.d("A", resposta.substring(resposta.lastIndexOf("/") + 1));

                    saldo.setText("R$  " + Reginaldo);



                    mSwipeRefreshLayout.setRefreshing(false);
                }
                catch (Exception e){
                    Toast.makeText(getActivity(),"Por favor, recarregue a página para calcular seus dados", Toast.LENGTH_LONG);}

            }


        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute();

    }


    private void LogpesToDatabase1(){
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {


            @Override
            protected String doInBackground(String... params) {

                try {

                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(gasto_url);


                    HttpResponse response = httpClient.execute(httpPost);

                    HttpEntity entity = response.getEntity();
                    BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

                    StringBuffer sb = new StringBuffer("");
                    String line="";
                    while ((line = in.readLine()) != null) {
                        sb.append(line + "\n");
                        break;
                    }
                    in.close();
                    resposta1 = sb.toString();
                    Log.d("TAG", resposta1);
                    return sb.toString();

                    //is = entity.getContent();


                } catch (ClientProtocolException e) {

                } catch (IOException e) {

                }
                return resposta1;


            }

            @Override
            protected void onPostExecute(String result)
            {
                super.onPostExecute(result);
                try {

                        gasto.setText(" R$  " + result);

                    mSwipeRefreshLayout.setRefreshing(false);
                }
                catch (Exception e){
                    Toast.makeText(getActivity(),"Por favor, recarregue a página para calcular seus dados", Toast.LENGTH_LONG);}

            }


        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute();

    }

}