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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class RecyclerViewAdapterMinidicionario extends RecyclerView.Adapter<RecyclerViewAdapterMinidicionario.ViewHolder>
{


    String GET_JSON_DATA_HTTP_URL = "http://premiumcontrol.com.br/NakasoneSoftapp/select/selectronaldo.php?id_conta=";

    JsonArrayRequest jsonArrayRequest ;

    RequestQueue requestQueue ;
    String nome;





    //Context context;
    private Activity context;

    List<GetDataAdapterMinidicionario> getDataAdapter;
    ImageLoader imageLoader1;

    Object mContext;
    public RecyclerViewAdapterMinidicionario(List<GetDataAdapterMinidicionario> getDataAdapter, Activity context){

        super();
        this.getDataAdapter = getDataAdapter;
        this.context = context;


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_items_minidicionario, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder Viewholder, final int position)
    {
        final GetDataAdapterMinidicionario getDataAdapter1 =  getDataAdapter.get(position);
        final String distancia ;

        Viewholder.id_item.setText(getDataAdapter1.getId_item());
        Viewholder.descricao_item.setText(getDataAdapter1.getDescricao_item());
        Viewholder.id_grupo.setText(getDataAdapter1.getId_grupo());

        //region Método para buscar o nome dos produtos na tabela Produto.
        jsonArrayRequest = new JsonArrayRequest("http://premiumcontrol.com.br/NakasoneSoftapp/select/selectgruporonaldo.php?id_grupo="+getDataAdapter1.getId_grupo(),

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
                            Viewholder.id_grupo.setText(nome);
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
                //Static.setIdevento(Integer.parseInt(String.valueOf(Viewholder.idEvento.getText())));

                //evento frag = new evento();

                //MainActivity mainActivity = (MainActivity) context;

                //mainActivity.switchContent(frag);
            }
        });


    }

    @Override
    public int getItemCount() {

        return getDataAdapter.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder
    {

        public TextView id_item, descricao_item, id_grupo;

        public ProgressBar bar;
        CardView cad;
        RelativeLayout cdll;



        public ViewHolder(View itemView)
        {

            super(itemView);

            id_item= (TextView) itemView.findViewById(R.id.id_item);
            descricao_item = (TextView) itemView.findViewById(R.id.descricao_item);
            id_grupo= (TextView) itemView.findViewById(R.id.id_grupo);

            bar=(ProgressBar)itemView.findViewById(R.id.progressBar);
            cad = (CardView)itemView.findViewById(R.id.cardview1);
            cdll= (RelativeLayout) itemView.findViewById(R.id.cdll2);


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