package com.f22labs.instalikefragmenttransaction.adapters;

import android.app.Activity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.f22labs.instalikefragmenttransaction.R;
import com.f22labs.instalikefragmenttransaction.activities.MainActivity;
import com.f22labs.instalikefragmenttransaction.fragments.AlterarDespesaNakasone;
import com.f22labs.instalikefragmenttransaction.utils.Static;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class RecyclerViewAdapterDespesa extends RecyclerView.Adapter<RecyclerViewAdapterDespesa.ViewHolder>
{

    //Context context;
    private Activity context;

    List<GetDataAdapter> getDataAdapter;
    ImageLoader imageLoader1;

    Object mContext;


    String GET_JSON_DATA_HTTP_URL = "http://premiumcontrol.com.br/NakasoneSoftapp/select/selectronaldo.php?id_conta=";

    JsonArrayRequest jsonArrayRequest ;

    RequestQueue requestQueue ;
    String nome;



    public RecyclerViewAdapterDespesa(List<GetDataAdapter> getDataAdapter, Activity context){

        super();
        this.getDataAdapter = getDataAdapter;
        this.context = context;


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_items_despesa, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder Viewholder, final int position)
    {

        final GetDataAdapter getDataAdapter1 =  getDataAdapter.get(position);
        final String distancia ;

        Viewholder.id_despesas.setText(getDataAdapter1.getId_despesas());
        Viewholder.descricao_despesas.setText(getDataAdapter1.getDescricao_despesas());
       // Viewholder.conta_despesas.setText(getDataAdapter1.getConta_despesas());
        Viewholder.valor_despesas.setText(getDataAdapter1.getValor_despesas());
       // Viewholder.praondefoi_despesa.setText(getDataAdapter1.getComofoipago_despesas());
        Viewholder.data_despesas.setText(getDataAdapter1.getData_despesas());

        //region Método para buscar o nome dos produtos na tabela Produto.
        jsonArrayRequest = new JsonArrayRequest(GET_JSON_DATA_HTTP_URL+getDataAdapter1.getConta_despesas(),

                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response)
                    {

                        JSONArray array = response;
                        JSONObject json = null;
                        try
                        {

                            json = array.getJSONObject(0);
                            nome= json.getString("nome_conta");
                            Log.d("NOME",nome);
                            Viewholder.conta_despesas.setText(nome);



                        }
                        catch (JSONException e){e.printStackTrace();}
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        requestQueue = Volley.newRequestQueue(this.context);

        requestQueue.add(jsonArrayRequest);
        //endregion

        //region Método para buscar o nome dos produtos na tabela Produto.
        jsonArrayRequest = new JsonArrayRequest("http://premiumcontrol.com.br/NakasoneSoftapp/select/selectgruporonaldo.php?id_grupo="+getDataAdapter1.getComofoipago_despesas(),

                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response)
                    {

                        JSONArray array = response;
                        JSONObject json = null;
                        try
                        {

                            json = array.getJSONObject(0);
                            nome= json.getString("nome_grupo");
                            Viewholder.praondefoi_despesa.setText(nome);
                            // Viewholder.comofoipago_despesas.setText(getDataAdapter1.getComofoipago_despesas());


                        }
                        catch (JSONException e){e.printStackTrace();}
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        requestQueue = Volley.newRequestQueue(this.context);

        requestQueue.add(jsonArrayRequest);
        //endregion



        Viewholder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Static.setId_depesas(Integer.parseInt(String.valueOf(Viewholder.id_despesas.getText())));

                AlterarDespesaNakasone frag = new AlterarDespesaNakasone();

                MainActivity mainActivity = (MainActivity) context;

                mainActivity.pushFragment(frag);
            }
        });


    }

    @Override
    public int getItemCount() {

        return getDataAdapter.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder
    {

        public TextView id_despesas, descricao_despesas, conta_despesas, valor_despesas, praondefoi_despesa, data_despesas;

        public ProgressBar bar;
        CardView cad;
        RelativeLayout cdll;



        public ViewHolder(View itemView)
        {

            super(itemView);

            id_despesas= (TextView) itemView.findViewById(R.id.id_despesa);
            descricao_despesas = (TextView) itemView.findViewById(R.id.descricao_despesa);
            conta_despesas= (TextView) itemView.findViewById(R.id.conta_despesa);
            valor_despesas = (TextView)itemView.findViewById(R.id.valor_despesa);
            praondefoi_despesa = (TextView) itemView.findViewById(R.id.praondefoi_despesa) ;
            data_despesas= (TextView) itemView.findViewById(R.id.data_despesa) ;

        }
    }


    //region HostCall
    public void JSON_DATA_WEB_CALL()
    {

        jsonArrayRequest = new JsonArrayRequest(GET_JSON_DATA_HTTP_URL  ,

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

        requestQueue = Volley.newRequestQueue(this.context);

        requestQueue.add(jsonArrayRequest);


    }
    public void JSON_PARSE_DATA_AFTER_WEBCALL(JSONArray array)
    {
        JSONObject json = null;
        try
        {
            json = array.getJSONObject(0);
            nome= json.getString("nome_conta");
        }
        catch (JSONException e){e.printStackTrace();}
    }
    //endregion
}