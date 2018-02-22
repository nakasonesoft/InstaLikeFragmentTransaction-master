package com.f22labs.instalikefragmenttransaction.fragments;

import android.app.ProgressDialog;
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
import com.f22labs.instalikefragmenttransaction.adapters.RecyclerViewAdapterDespesa;
import com.f22labs.instalikefragmenttransaction.interfaces.RecyclerViewOnClickListenerHack;
import com.f22labs.instalikefragmenttransaction.utils.Static;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class RecyclerFicha extends BaseFragment implements RecyclerViewOnClickListenerHack
{

    ProgressDialog loading;
    String url, resposta;

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


    String GET_JSON_DATA_HTTP_URL = "http://premiumcontrol.com.br/NakasoneSoftapp/select/diario_despesa.php";

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

        //region Jsons

        switch (Static.getFicha())
        {
            case 1:  GET_JSON_DATA_HTTP_URL = "http://premiumcontrol.com.br/NakasoneSoftapp/select/diario_receita.php";

                ((MainActivity)getActivity()).updateToolbarTitle("Ficha individual de Receita");
                break;

            case 2:  GET_JSON_DATA_HTTP_URL = "http://premiumcontrol.com.br/NakasoneSoftapp/select/diario_transferencia.php";

                ((MainActivity)getActivity()).updateToolbarTitle("Ficha individual de Transferência");

                break;

            case 3:  GET_JSON_DATA_HTTP_URL = "http://premiumcontrol.com.br/NakasoneSoftapp/select/diario_saque.php";

                ((MainActivity)getActivity()).updateToolbarTitle("Ficha individual de Saque");

                break;
            case 4:  GET_JSON_DATA_HTTP_URL = "http://premiumcontrol.com.br/NakasoneSoftapp/select/diario_deposito.php";

                ((MainActivity)getActivity()).updateToolbarTitle("Ficha individual de Depósito");

                break;

            case 7:  GET_JSON_DATA_HTTP_URL = " http://premiumcontrol.com.br/NakasoneSoftapp/select/diario_imoveis.php";

                ((MainActivity)getActivity()).updateToolbarTitle("Ficha individual de Prestação de Imóveis");

                break;

            case 8:  GET_JSON_DATA_HTTP_URL = " http://premiumcontrol.com.br/NakasoneSoftapp/select/diario_consorcio.php";

                ((MainActivity)getActivity()).updateToolbarTitle("Ficha individual de Consórcio");

                break;

            case 9:  GET_JSON_DATA_HTTP_URL = " http://premiumcontrol.com.br/NakasoneSoftapp/select/diario_outros.php";

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