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
import com.f22labs.instalikefragmenttransaction.adapters.GetDataAdapterDesativarContas;
import com.f22labs.instalikefragmenttransaction.adapters.RecyclerViewAdapterDesativarContas;
import com.f22labs.instalikefragmenttransaction.interfaces.RecyclerViewOnClickListenerHack;
import com.f22labs.instalikefragmenttransaction.utils.Static;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class RecyclerDesativarContas extends BaseFragment implements RecyclerViewOnClickListenerHack
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
    RecyclerDesativarContas mAdapter ;

    GestureDetector mGestureDetector;

    SwipeRefreshLayout mSwipeRefreshLayout;
    List<GetDataAdapterDesativarContas> GetDataAdapter1;

    RecyclerView recyclerView;

    RecyclerView.LayoutManager recyclerViewlayoutManager = new LinearLayoutManager(getActivity());
    RecyclerView.Adapter recyclerViewadapter;

    String GET_JSON_DATA_HTTP_URL = "http://premiumcontrol.com.br/NakasoneSoftapp/select/select_conta.php?id_cliente="+ Static.getId_cliente()+"";

    String JSON_id_despesas_relatorio3 = "id_despesas";
    String JSON_descricao_despesas_relatorio3 = "descricao_despesas";
    String JSON_conta_despesas_relatorio3 = "conta_despesas";
    String JSON_valor_despesas_relatorio3 = "valor_despesas";
    String JSON_comofoipago_despesas_relatorio3 = "comofoipago_despesas";
    String JSON_data_despesas_relatorio3 = "data_despesas";
    String JSON_id_conta_relatorio3 = "id_conta";
    String JSON_id_grupo_relatorio3 = "id_grupo";
    String JSON_nome_conta_relatorio3 = "nome_conta";
    String JSON_saldoinicial_conta_relatorio3 = "saldoinicial_conta";
    String JSON_datafechamento_conta_relatorio3 = "datafechamento_conta";
    String JSON_status_relatorio3 = "status";

    private final String KEY_RECYCLER_STATE = "recycler_state";

    private static Bundle mBundleRecyclerViewState;
    private static int dyb;

    private Toolbar mToolbar;
    TextView txtTitle, txtSubtitle;
    JsonArrayRequest jsonArrayRequest ;

    RequestQueue requestQueue ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_recycler_contas_desativar, container, false);
        mSwipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.swifeRefreshcontasdesativar);

        GetDataAdapter1 = new ArrayList<>();

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerviewcontasdesativar);

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
    public void JSON_PARSE_DATA_AFTER_WEBCALL(JSONArray array){

        for(int i = 0; i<array.length(); i++) {

            final GetDataAdapterDesativarContas GetDataAdapter2 = new GetDataAdapterDesativarContas();

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

        recyclerViewadapter = new RecyclerViewAdapterDesativarContas(GetDataAdapter1, getActivity());

        recyclerView.setAdapter(recyclerViewadapter);



    }

//endregion


}
