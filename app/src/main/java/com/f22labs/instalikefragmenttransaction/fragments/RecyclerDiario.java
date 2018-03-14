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

public class RecyclerDiario extends BaseFragment implements RecyclerViewOnClickListenerHack
{

    ProgressDialog loading;
    String url, resposta;



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


    String GET_JSON_DATA_HTTP_URL = "http://premiumcontrol.com.br/NakasoneSoftapp/select/diario_despesa.php?id_cliente="+ Static.getId_cliente()+"";

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

        View view = inflater.inflate(R.layout.fragment_recycler_diario, container, false);

        //region Jsons

        switch (Static.getDiario())
        {
            case 1:  GET_JSON_DATA_HTTP_URL = "http://premiumcontrol.com.br/NakasoneSoftapp/select/diario_receita.php?id_cliente="+ Static.getId_cliente()+"";

                ((MainActivity)getActivity()).updateToolbarTitle("Diário de Receita");
                break;

            case 2:  GET_JSON_DATA_HTTP_URL = "http://premiumcontrol.com.br/NakasoneSoftapp/select/diario_transferencia.php?id_cliente="+ Static.getId_cliente()+"";

                ((MainActivity)getActivity()).updateToolbarTitle("Diário de Transferência");

              break;

            case 3:  GET_JSON_DATA_HTTP_URL = "http://premiumcontrol.com.br/NakasoneSoftapp/select/diario_saque.php?id_cliente="+ Static.getId_cliente()+"";

                ((MainActivity)getActivity()).updateToolbarTitle("Diário de Saque");

                break;
            case 4:  GET_JSON_DATA_HTTP_URL = "http://premiumcontrol.com.br/NakasoneSoftapp/select/diario_deposito.php?id_cliente="+ Static.getId_cliente()+"";

                ((MainActivity)getActivity()).updateToolbarTitle("Diário de Depósito");

                break;


            case 5:  GET_JSON_DATA_HTTP_URL = "http://premiumcontrol.com.br/NakasoneSoftapp/select/diario_fatura.php?id_cliente="+ Static.getId_cliente()+"";

                ((MainActivity)getActivity()).updateToolbarTitle("Diário de Fatura");

                break;

            case 7:  GET_JSON_DATA_HTTP_URL = " http://premiumcontrol.com.br/NakasoneSoftapp/select/diario_imoveis.php?id_cliente="+ Static.getId_cliente()+"";

                ((MainActivity)getActivity()).updateToolbarTitle("Diário de Prestação de Imóveis");

                break;

            case 8:  GET_JSON_DATA_HTTP_URL = " http://premiumcontrol.com.br/NakasoneSoftapp/select/diario_consorcio.php?id_cliente="+ Static.getId_cliente()+"";

                ((MainActivity)getActivity()).updateToolbarTitle("Diário de Consórcio");

                break;

            case 9:  GET_JSON_DATA_HTTP_URL = " http://premiumcontrol.com.br/NakasoneSoftapp/select/diario_outros.php?id_cliente="+ Static.getId_cliente()+"";

                ((MainActivity)getActivity()).updateToolbarTitle("Diário Outros");

                break;



        }
        //endregion


        mSwipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.swifeRefreshfatura_diario);

        //mListener = listener;

        GetDataAdapter1 = new ArrayList<>();

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_diario);

        recyclerView.setHasFixedSize(true);

        recyclerViewlayoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(recyclerViewlayoutManager);

        JSON_DATA_WEB_CALL();
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

}