package com.f22labs.instalikefragmenttransaction.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.f22labs.instalikefragmenttransaction.R;
import com.f22labs.instalikefragmenttransaction.activities.MainActivity;
import com.f22labs.instalikefragmenttransaction.adapters.GetDataAdapterPasso2;
import com.f22labs.instalikefragmenttransaction.adapters.RecyclerViewAdapterPasso2;
import com.f22labs.instalikefragmenttransaction.interfaces.RecyclerViewOnClickListenerHack;
import com.f22labs.instalikefragmenttransaction.utils.Static;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;


public class RecyclerPasso2 extends BaseFragment implements RecyclerViewOnClickListenerHack
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
    RecyclerViewAdapterPasso2 mAdapter ;

    GestureDetector mGestureDetector;

    SwipeRefreshLayout mSwipeRefreshLayout;

    List<GetDataAdapterPasso2> GetDataAdapter1;
    List<GetDataAdapterPasso2> GetDataAdapter22;

    RecyclerView recyclerView,recyclerView2;

    RecyclerView.LayoutManager recyclerViewlayoutManager = new LinearLayoutManager(getActivity());
    RecyclerView.LayoutManager recyclerViewlayoutManager2 = new LinearLayoutManager(getActivity());

    RecyclerView.Adapter recyclerViewadapter;
    RecyclerView.Adapter recyclerViewadapter2;

    String GET_JSON_DATA_HTTP_URL = "http://premiumcontrol.com.br/NakasoneSoftapp/select/ImplementacaoPasso2.php?id_cliente="+ Static.getId_cliente()+"";
    String GET_JSON_DATA_HTTP_URL2 = "http://premiumcontrol.com.br/NakasoneSoftapp/select/ImplementacaoPasso2_1.php?id_cliente="+ Static.getId_cliente()+"";


    private final String KEY_RECYCLER_STATE = "recycler_state";

    private static Bundle mBundleRecyclerViewState;

    private static int dyb;

    private Toolbar mToolbar;

    TextView txtTitle, txtSubtitle;

    JsonArrayRequest jsonArrayRequest,jsonArrayRequest2 ;

    RequestQueue requestQueue,requestQueue2 ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_recycler_passo2, container, false);


        mSwipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.swifeRefreshPasso2);

        GetDataAdapter1 = new ArrayList<>();
        GetDataAdapter22 = new ArrayList<>();

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerviewPasso2);
        recyclerView2 = (RecyclerView) view.findViewById(R.id.recyclerviewPasso2Second);

        recyclerView.setHasFixedSize(true);
        recyclerView2.setHasFixedSize(true);

        recyclerViewlayoutManager = new LinearLayoutManager(getActivity());
        recyclerViewlayoutManager2 = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(recyclerViewlayoutManager);
        recyclerView2.setLayoutManager(recyclerViewlayoutManager2);

        JSON_DATA_WEB_CALL();JSON_DATA_WEB_CALL2();
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {


                GetDataAdapter1 = new ArrayList<>();

                recyclerView.setHasFixedSize(true);

                recyclerViewlayoutManager = new LinearLayoutManager(getActivity());
                recyclerViewlayoutManager2 = new LinearLayoutManager(getActivity());

                recyclerView.setLayoutManager(recyclerViewlayoutManager);

                recyclerView2.setHasFixedSize(true);

                recyclerView2.setLayoutManager(recyclerViewlayoutManager2);

                JSON_DATA_WEB_CALL();


            }
        });//region



        //endregion
        //region inutil

        ButterKnife.bind(this, view);

        ( (MainActivity)getActivity()).updateToolbarTitle("Search");
//endregion

        return view;
    }
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);


    }

    //region Chamada no Host
    public void JSON_DATA_WEB_CALL(){

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
    public void JSON_PARSE_DATA_AFTER_WEBCALL(JSONArray array)
    {

        for(int i = 0; i<array.length(); i++) {

            final GetDataAdapterPasso2 GetDataAdapter2 = new GetDataAdapterPasso2();

            JSONObject json = null;
            try {

                json = array.getJSONObject(i);

                GetDataAdapter2.setNome_conta(json.getString("nome_conta"));
                GetDataAdapter2.setStatus(json.getString("status"));
                GetDataAdapter2.setId_conta(json.getString("id_conta"));
                mSwipeRefreshLayout.setRefreshing(false);

            }
            catch (JSONException e)
            {

                e.printStackTrace();
            }
            GetDataAdapter1.add(GetDataAdapter2);
        }

        recyclerViewadapter = new RecyclerViewAdapterPasso2(GetDataAdapter1, getActivity());

        recyclerView.setAdapter(recyclerViewadapter);



    }

//endregion


    //region Chamada no Host
    public void JSON_DATA_WEB_CALL2(){

        jsonArrayRequest2 = new JsonArrayRequest(GET_JSON_DATA_HTTP_URL2,

                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        JSON_PARSE_DATA_AFTER_WEBCALL2(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        requestQueue2 = Volley.newRequestQueue(getActivity());

        requestQueue2.add(jsonArrayRequest2);
        mSwipeRefreshLayout.setRefreshing(true);




    }
    public void JSON_PARSE_DATA_AFTER_WEBCALL2(JSONArray array)
    {

        for(int i = 0; i<array.length(); i++) {

            final GetDataAdapterPasso2 GetDataAdapter2 = new GetDataAdapterPasso2();

            JSONObject json = null;
            try {

                json = array.getJSONObject(i);

                GetDataAdapter2.setNome_conta(json.getString("nome_conta"));
                GetDataAdapter2.setStatus(json.getString("status"));
                GetDataAdapter2.setId_conta(json.getString("id_conta"));
                mSwipeRefreshLayout.setRefreshing(false);

            }
            catch (JSONException e)
            {

                e.printStackTrace();
            }
            GetDataAdapter22.add(GetDataAdapter2);
        }

        recyclerViewadapter2 = new RecyclerViewAdapterPasso2(GetDataAdapter22, getActivity());

        recyclerView2.setAdapter(recyclerViewadapter2);



    }

//endregion

}
