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
import com.f22labs.instalikefragmenttransaction.adapters.GetDataAdapterDiario;
import com.f22labs.instalikefragmenttransaction.adapters.RecyclerViewAdapterDiario2;
import com.f22labs.instalikefragmenttransaction.interfaces.RecyclerViewOnClickListenerHack;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;


public class RecyclerDiario2 extends BaseFragment implements RecyclerViewOnClickListenerHack
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
    RecyclerViewAdapterDiario2 mAdapter ;

    GestureDetector mGestureDetector;

    SwipeRefreshLayout mSwipeRefreshLayout;
    List<GetDataAdapterDiario> GetDataAdapter1;

    RecyclerView recyclerView;

    RecyclerView.LayoutManager recyclerViewlayoutManager = new LinearLayoutManager(getActivity());
    RecyclerView.Adapter recyclerViewadapter;

    String GET_JSON_DATA_HTTP_URL = "http://premiumcontrol.com.br/NakasoneSoftapp/Select_Diario_Final.php";

    private final String KEY_RECYCLER_STATE = "recycler_state";

    private static Bundle mBundleRecyclerViewState;
    private static int dyb;

    private Toolbar mToolbar;
    TextView txtTitle, txtSubtitle;
    JsonArrayRequest jsonArrayRequest ;

    RequestQueue requestQueue ;



    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {


        View view = inflater.inflate(R.layout.fragment_recycler_diario2, container, false);

        mSwipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.swifeRefreshdiario);

        GetDataAdapter1 = new ArrayList<>();

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerviewdiario);

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
        });

        ButterKnife.bind(this, view);

        ( (MainActivity)getActivity()).updateToolbarTitle("Share");


        return view;
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
    public void JSON_PARSE_DATA_AFTER_WEBCALL(JSONArray array){

        for(int i = 0; i<array.length(); i++) {

            final GetDataAdapterDiario GetDataAdapter2 = new GetDataAdapterDiario();

            JSONObject json = null;
            try {

                json = array.getJSONObject(i);
                GetDataAdapter2.setId_diario(json.getString("id_diario"));
                GetDataAdapter2.setOrigem_diario(json.getString("origem_diario"));
                GetDataAdapter2.setDestino_diario(json.getString("destino_diario"));
                GetDataAdapter2.setDescricao_diario(json.getString("descricao_diario"));
                GetDataAdapter2.setValor_diario(json.getString("valor_diario"));
                GetDataAdapter2.setData_diario(json.getString("data_diario"));
                GetDataAdapter2.setTipo_diario(json.getString("tipo_diario"));
                GetDataAdapter2.setIdtipo_diario(json.getString("idtipo_diario"));
                GetDataAdapter2.setId_cliente(json.getString("id_cliente"));
                mSwipeRefreshLayout.setRefreshing(false);

            }
            catch (JSONException e)
            {

                e.printStackTrace();
            }
            GetDataAdapter1.add(GetDataAdapter2);
        }

        recyclerViewadapter = new RecyclerViewAdapterDiario2(GetDataAdapter1, getActivity());

        recyclerView.setAdapter(recyclerViewadapter);



    }

//endregion


}
