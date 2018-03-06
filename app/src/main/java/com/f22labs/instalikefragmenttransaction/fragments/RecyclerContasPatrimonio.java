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
import com.f22labs.instalikefragmenttransaction.adapters.GetDataAdapterContasPatrimonio;
import com.f22labs.instalikefragmenttransaction.adapters.RecyclerViewAdapter;
import com.f22labs.instalikefragmenttransaction.adapters.RecyclerViewAdapterContasPatrimonio;
import com.f22labs.instalikefragmenttransaction.adapters.RecyclerViewAdapterDespesa;
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

public class RecyclerContasPatrimonio extends BaseFragment implements RecyclerViewOnClickListenerHack
{

    ProgressDialog loading;
    String url, resposta, gasto_url, resposta1;
    TextView total_total;


    private FragmentTabHost mTabHost;
    private OnItemClickListener mListener;
    private static int opcao = Static.getId_conta();


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
    List<GetDataAdapterContasPatrimonio> GetDataAdapter1;

    RecyclerView recyclerView;

    RecyclerView.LayoutManager recyclerViewlayoutManager;

    RecyclerView.Adapter recyclerViewadapter;


    String GET_JSON_DATA_HTTP_URL = "http://premiumcontrol.com.br/NakasoneSoftapp/select/select_conta_patrimonio.php";
    String GET_JSON_DATA_HTTP_URL1 = "http://premiumcontrol.com.br/NakasoneSoftapp/select/set_total_patrimonio.php?id_grupo=8";

    String JSON_id_conta = "id_conta";
    String JSON_nome_conta = "nome_conta";


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

        View view = inflater.inflate(R.layout.fragment_recycler_contas_patrimonio, container, false);

        total_total = (TextView)view.findViewById(R.id.total_total);

        switch (Static.getTipo_patrimonio()){
            case 2:
                GET_JSON_DATA_HTTP_URL = "http://premiumcontrol.com.br/NakasoneSoftapp/select/select_conta_patrimonio.php?id_grupo=2";
                GET_JSON_DATA_HTTP_URL1 = "http://premiumcontrol.com.br/NakasoneSoftapp/select/set_total_patrimonio.php?id_grupo=2";


                break;
            case 8:
                GET_JSON_DATA_HTTP_URL = "http://premiumcontrol.com.br/NakasoneSoftapp/select/select_conta_patrimonio.php?id_grupo=8";
                GET_JSON_DATA_HTTP_URL1 = "http://premiumcontrol.com.br/NakasoneSoftapp/select/set_total_patrimonio.php?id_grupo=8";
                break;
            case 9:
                GET_JSON_DATA_HTTP_URL = "http://premiumcontrol.com.br/NakasoneSoftapp/select/select_conta_patrimonio.php?id_grupo=9";
                GET_JSON_DATA_HTTP_URL1 = "http://premiumcontrol.com.br/NakasoneSoftapp/select/set_total_patrimonio.php?id_grupo=9";
                break;
            case 10:
                GET_JSON_DATA_HTTP_URL = "http://premiumcontrol.com.br/NakasoneSoftapp/select/select_conta_patrimonio.php?id_grupo=10";
                GET_JSON_DATA_HTTP_URL1 = "http://premiumcontrol.com.br/NakasoneSoftapp/select/set_total_patrimonio.php?id_grupo=10";
                break;
        }





        mSwipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.swifeRefreshcontas_patrimonio);

        //mListener = listener;

        GetDataAdapter1 = new ArrayList<>();

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_contas_patrimonio);

        recyclerView.setHasFixedSize(true);

        recyclerViewlayoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(recyclerViewlayoutManager);


        JSON_DATA_WEB_CALL();
        JSON_DATA_WEB_CALL_TOTAL();

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {


                GetDataAdapter1 = new ArrayList<>();

                recyclerView.setHasFixedSize(true);

                recyclerViewlayoutManager = new LinearLayoutManager(getActivity());

                recyclerView.setLayoutManager(recyclerViewlayoutManager);

                JSON_DATA_WEB_CALL();
                JSON_DATA_WEB_CALL_TOTAL();


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

            final GetDataAdapterContasPatrimonio GetDataAdapter2 = new GetDataAdapterContasPatrimonio();

            JSONObject json = null;
            try {

                json = array.getJSONObject(i);
                GetDataAdapter2.setId_conta(json.getString(JSON_id_conta));
                GetDataAdapter2.setNome_conta(json.getString(JSON_nome_conta));

                mSwipeRefreshLayout.setRefreshing(false);

            }
            catch (JSONException e)
            {

                e.printStackTrace();
            }
            GetDataAdapter1.add(GetDataAdapter2);
        }

        recyclerViewadapter = new RecyclerViewAdapterContasPatrimonio(GetDataAdapter1, getActivity());

        recyclerView.setAdapter(recyclerViewadapter);



    }

//endregion


    //region HostCall
    public void JSON_DATA_WEB_CALL_TOTAL()
    {

        jsonArrayRequest = new JsonArrayRequest(GET_JSON_DATA_HTTP_URL1,

                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        JSON_PARSE_DATA_AFTER_WEBCALL_TOTAL(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        requestQueue = Volley.newRequestQueue(getActivity());

        requestQueue.add(jsonArrayRequest);

        //mSwipeRefreshLayout.setRefreshing(true);
    }
    public void JSON_PARSE_DATA_AFTER_WEBCALL_TOTAL(JSONArray array)
    {
        JSONObject json = null;
        try
        {
            json = array.getJSONObject(0);
            total_total.setText("R$  " + json.getString("valor"));
            Log.d("AQUI", json.getString(""));
            //mSwipeRefreshLayout.setRefreshing(false);
        }
        catch (JSONException e){e.printStackTrace();}
    }
    //endregion




}