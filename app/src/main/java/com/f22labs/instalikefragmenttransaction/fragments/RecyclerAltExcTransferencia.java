package com.f22labs.instalikefragmenttransaction.fragments;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.f22labs.instalikefragmenttransaction.R;
import com.f22labs.instalikefragmenttransaction.activities.MainActivity;
import com.f22labs.instalikefragmenttransaction.adapters.GetDataAdapter;
import com.f22labs.instalikefragmenttransaction.adapters.RecyclerViewAdapter;
import com.f22labs.instalikefragmenttransaction.adapters.RecyclerViewAdapterReceita;
import com.f22labs.instalikefragmenttransaction.adapters.RecyclerViewAdapterTransferencia;
import com.f22labs.instalikefragmenttransaction.interfaces.RecyclerViewOnClickListenerHack;
import com.f22labs.instalikefragmenttransaction.utils.Static;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;


public class RecyclerAltExcTransferencia extends BaseFragment implements RecyclerViewOnClickListenerHack
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

    RecyclerView recyclerView;

    RecyclerView.LayoutManager recyclerViewlayoutManager;

    RecyclerView.Adapter recyclerViewadapter;
    String GET_JSON_DATA_HTTP_URL = "http://premiumcontrol.com.br/NakasoneSoftapp/select/select_transferencia.php?id_cliente="+ Static.getId_cliente()+"";
    String JSON_id_transferencia = "id_transferencia";
    String JSON_descricao_transferencia = "descricao_transferencia";
    String JSON_valor_transferencia = "valor_transferencia";
    String JSON_praondefoi_transferencia = "praondefoi_transferencia";
    String JSON_contaondeveio_transferencia = "contaondeveio_transferencia";
    String JSON_data_transferencia = "data_transferencia";

    private final String KEY_RECYCLER_STATE = "recycler_state";

    private static Bundle mBundleRecyclerViewState;
    private static int dyb;

    JsonArrayRequest jsonArrayRequest ;

    RequestQueue requestQueue ;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {

        View view = inflater.inflate(R.layout.fragment_recycler_alt_exc_transferencia, container, false);

        mSwipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.swifeRefreshaltexctransferencia);
        //mListener = listener;

        GetDataAdapter1 = new ArrayList<>();

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerviewaltexctransferencia);

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
        ((MainActivity)getActivity()).updateToolbarTitle("Alterar/Excluir Transferência");

        //endregion

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

            final GetDataAdapter GetDataAdapter2 = new GetDataAdapter();

            JSONObject json = null;
            try {

                json = array.getJSONObject(i);

                GetDataAdapter2.setId_transferencia(json.getString(JSON_id_transferencia));
                GetDataAdapter2.setDescricao_transferencia(json.getString(JSON_descricao_transferencia));
                GetDataAdapter2.setValor_transferencia(json.getString(JSON_valor_transferencia));
                GetDataAdapter2.setPraondefoi_transferencia(json.getString(JSON_praondefoi_transferencia));
                GetDataAdapter2.setContaondeveio_transferencia(json.getString(JSON_contaondeveio_transferencia));
                GetDataAdapter2.setData_transferencia(json.getString(JSON_data_transferencia));
                mSwipeRefreshLayout.setRefreshing(false);

            }
            catch (JSONException e)
            {

                e.printStackTrace();
            }
            GetDataAdapter1.add(GetDataAdapter2);
        }

        recyclerViewadapter = new RecyclerViewAdapterTransferencia(GetDataAdapter1, getActivity());

        recyclerView.setAdapter(recyclerViewadapter);



    }

//endregion


}
