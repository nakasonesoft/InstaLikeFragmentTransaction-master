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
import com.f22labs.instalikefragmenttransaction.fragments.AlterarReceitaNakasone;
import com.f22labs.instalikefragmenttransaction.utils.Static;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class RecyclerViewAdapterReceita extends RecyclerView.Adapter<RecyclerViewAdapterReceita.ViewHolder>
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

    public RecyclerViewAdapterReceita(List<GetDataAdapter> getDataAdapter, Activity context){

        super();
        this.getDataAdapter = getDataAdapter;
        this.context = context;


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_items_receita, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder Viewholder, final int position)
    {

        final GetDataAdapter getDataAdapter1 =  getDataAdapter.get(position);
        final String distancia ;

        Viewholder.id_receita.setText(getDataAdapter1.getId_receita());
        Viewholder.descricao_receita.setText(getDataAdapter1.getDescricao_receita());
        Viewholder.valor_receita.setText(getDataAdapter1.getValor_receita());
        //Viewholder.praondefoi_receita.setText(getDataAdapter1.getPraondefoi_receita());
        Viewholder.data_receita.setText(getDataAdapter1.getData_receita());

        //region Método para buscar o nome dos produtos na tabela Produto.
        jsonArrayRequest = new JsonArrayRequest(GET_JSON_DATA_HTTP_URL+getDataAdapter1.getId_conta(),

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
                            Viewholder.id_conta.setText(nome);
                           // Viewholder.id_conta.setText(getDataAdapter1.getId_conta());


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
        jsonArrayRequest = new JsonArrayRequest("http://premiumcontrol.com.br/NakasoneSoftapp/select/selectgruporonaldo.php?id_grupo="+getDataAdapter1.getPraondefoi_receita(),

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
                            Viewholder.praondefoi_receita.setText(nome);
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
                Static.setId_receita(Integer.parseInt(String.valueOf(Viewholder.id_receita.getText())));

                AlterarReceitaNakasone frag = new AlterarReceitaNakasone();

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

        public TextView id_receita, descricao_receita, id_conta, valor_receita, praondefoi_receita, data_receita;

        public ProgressBar bar;
        CardView cad;
        RelativeLayout cdll;



        public ViewHolder(View itemView)
        {

            super(itemView);

            id_receita= (TextView) itemView.findViewById(R.id.id_receita);
            descricao_receita = (TextView) itemView.findViewById(R.id.textView_item_receita);
            id_conta= (TextView) itemView.findViewById(R.id.txtconta_receita);
            valor_receita = (TextView)itemView.findViewById(R.id.txtvalor_receita);
            praondefoi_receita = (TextView) itemView.findViewById(R.id.txtcomofoipago_receita) ;
            data_receita= (TextView) itemView.findViewById(R.id.txtData_receita) ;

        }
    }
}