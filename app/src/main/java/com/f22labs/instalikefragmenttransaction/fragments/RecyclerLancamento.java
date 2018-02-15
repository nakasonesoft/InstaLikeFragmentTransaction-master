package com.f22labs.instalikefragmenttransaction.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.f22labs.instalikefragmenttransaction.R;
import com.f22labs.instalikefragmenttransaction.activities.MainActivity;
import com.f22labs.instalikefragmenttransaction.adapters.GetDataAdapter;
import com.f22labs.instalikefragmenttransaction.adapters.GetDataAdapterReceita;
import com.f22labs.instalikefragmenttransaction.adapters.RecyclerViewAdapter;

import com.f22labs.instalikefragmenttransaction.adapters.RecyclerViewAdapterReceita;


import com.f22labs.instalikefragmenttransaction.interfaces.RecyclerViewOnClickListenerHack;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;


public class RecyclerLancamento extends BaseFragment implements RecyclerViewOnClickListenerHack
{
    private FragmentTabHost mTabHost;
    private OnItemClickListener mListener;
    //region
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
    RecyclerViewAdapter mAdapter ;

    GestureDetector mGestureDetector;

    SwipeRefreshLayout mSwipeRefreshLayout;
    List<GetDataAdapter> GetDataAdapter1;
    List<GetDataAdapterReceita> GetDataAdapter2;

    RecyclerView recyclerView;

    RecyclerView.LayoutManager recyclerViewlayoutManager;

    RecyclerView.Adapter recyclerViewadapter;

    String GET_JSON_DATA_HTTP_URL = "http://premiumcontrol.com.br/NakasoneSoftapp/select/select_all.php";
    String GET_JSON_DATA_HTTP_URL_Receita = "http://premiumcontrol.com.br/NakasoneSoftapp/select/select_receita.php";

    String JSON_id_despesas = "id_despesas";
    String JSON_descricao_despesas = "descricao_despesas";
    String JSON_conta_despesas = "conta_despesas";
    String JSON_valor_despesas = "valor_despesas";
    String JSON_comofoipago_despesas = "comofoipago_despesas";
    String JSON_data_despesas = "data_despesas";

    //receita
    String JSON_id_receita = "id_receita";
    String JSON_descricao_receita = "descricao_receita";
    String JSON_id_conta = "id_conta";
    String JSON_valor_receita = "valor_receita";
    String JSON_praondefoi_receita = "praondefoi_receita";
    String JSON_data_receita = "data_receita";



    private final String KEY_RECYCLER_STATE = "recycler_state";

    private static Bundle mBundleRecyclerViewState;
    private static int dyb;

    private Toolbar mToolbar;
    TextView txtTitle, txtSubtitle;
    JsonArrayRequest jsonArrayRequest,jsonArrayRequest2 ;

    RequestQueue requestQueue, requestQueue2;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    //----------------------------------------------------------------------------------------------
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {

        View view = inflater.inflate(R.layout.fragment_recycler_lancamento, container, false);

        mSwipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.swifeRefresh);
        //mListener = listener;

        GetDataAdapter1 = new ArrayList<>();


        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview12);

        recyclerView.setHasFixedSize(true);

        recyclerViewlayoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(recyclerViewlayoutManager);
        //JSON_DATA_WEB_CALL();
        JSON_DATA_WEB_CALL_RECEITA();


        //region swipe
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {

            @Override
            public void onRefresh() {


                GetDataAdapter1 = new ArrayList<>();

                recyclerView.setHasFixedSize(true);

                recyclerViewlayoutManager = new LinearLayoutManager(getActivity());

                recyclerView.setLayoutManager(recyclerViewlayoutManager);

                JSON_DATA_WEB_CALL();
                JSON_DATA_WEB_CALL_RECEITA();



            }
        });//endregion
        //region
        ButterKnife.bind(this, view);
        ((MainActivity)getActivity()).updateToolbarTitle("Lançamentos Cadastrados");

        //endregion

        return view;
    }

    //----------------------------------------------------------------------------------------------

    //region Chamada no Host Despesa

    public void JSON_DATA_WEB_CALL()
    {

        jsonArrayRequest = new JsonArrayRequest(GET_JSON_DATA_HTTP_URL,

                new Response.Listener<JSONArray>()
                {
                    @Override
                    public void onResponse(JSONArray response)
                    {

                        JSON_PARSE_DATA_AFTER_WEBCALL(response);



                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {

                    }
                });

        requestQueue = Volley.newRequestQueue(getActivity());

        requestQueue.add(jsonArrayRequest);
        mSwipeRefreshLayout.setRefreshing(true);

    }




    public void JSON_PARSE_DATA_AFTER_WEBCALL(JSONArray array)
    {

        for(int i = 0; i<array.length(); i++)
        {

            final GetDataAdapter GetDataAdapter2 = new GetDataAdapter();

            JSONObject json = null;
            try {
                json = array.getJSONObject(i);
                //receita
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

        recyclerViewadapter = new RecyclerViewAdapter(GetDataAdapter1, getActivity());

        recyclerView.setAdapter(recyclerViewadapter);



    }

    public void JSON_DATA_WEB_CALL_RECEITA()
    {

        jsonArrayRequest2 = new JsonArrayRequest(GET_JSON_DATA_HTTP_URL_Receita,

                new Response.Listener<JSONArray>()
                {
                    @Override
                    public void onResponse(JSONArray response)
                    {

                        JSON_PARSE_DATA_AFTER_WEBCALL_RECEITA(response);



                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {

                    }
                });

        requestQueue2 = Volley.newRequestQueue(getActivity());

        requestQueue2.add(jsonArrayRequest2);
        mSwipeRefreshLayout.setRefreshing(true);

    }


    public void JSON_PARSE_DATA_AFTER_WEBCALL_RECEITA(JSONArray array)
    {

        for(int i = 0; i<array.length(); i++)
        {

            final GetDataAdapter GetDataAdapter3 = new GetDataAdapter();

            JSONObject json = null;
            try {
                json = array.getJSONObject(i);

                //receita
                GetDataAdapter3.setId_receita(json.getString(JSON_id_receita));
                GetDataAdapter3.setDescricao_receita(json.getString(JSON_descricao_receita));
                GetDataAdapter3.setId_conta(json.getString(JSON_id_conta));
                GetDataAdapter3.setValor_receita(json.getString(JSON_valor_receita));
                GetDataAdapter3.setPraondefoi_receita(json.getString(JSON_praondefoi_receita));
                GetDataAdapter3.setData_despesas(json.getString(JSON_data_receita));

                mSwipeRefreshLayout.setRefreshing(false);

            }
            catch (JSONException e)
            {

                e.printStackTrace();
            }

            GetDataAdapter1.add(GetDataAdapter3);
        }

        recyclerViewadapter = new RecyclerViewAdapterReceita(GetDataAdapter1, getActivity());

        recyclerView.setAdapter(recyclerViewadapter);



    }





 }
