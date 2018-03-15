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
import com.f22labs.instalikefragmenttransaction.fragments.AlterarConsorcioNakasone;
import com.f22labs.instalikefragmenttransaction.fragments.AlterarImoveisNakasone;
import com.f22labs.instalikefragmenttransaction.utils.Static;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class RecyclerViewAdapterConsorcio extends RecyclerView.Adapter<RecyclerViewAdapterConsorcio.ViewHolder>
{

    //Context context;
    private Activity context;

    List<GetDataAdapter> getDataAdapter;
    ImageLoader imageLoader1;

    Object mContext;

    String GET_JSON_DATA_HTTP_URL = "http://premiumcontrol.com.br/NakasoneSoftapp/select/selectronaldo.php?id_conta=";
    JsonArrayRequest jsonArrayRequest;
    RequestQueue requestQueue;

    String nome;
    public RecyclerViewAdapterConsorcio(List<GetDataAdapter> getDataAdapter, Activity context){

        super();
        this.getDataAdapter = getDataAdapter;
        this.context = context;


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_items_consorcio, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder Viewholder, final int position)
    {

        final GetDataAdapter getDataAdapter1 =  getDataAdapter.get(position);
        final String distancia ;
        Viewholder.id_consorcio.setText(getDataAdapter1.getId_consorcio());
        Viewholder.descricao_consorcio.setText(getDataAdapter1.getDescricao_consorcio());
        Viewholder.valor_consorcio.setText(getDataAdapter1.getValor_consorcio());
        //Viewholder.conta_consorcio.setText(getDataAdapter1.getConta_consorcio());
        Viewholder.comofoipago_consorcio.setText(getDataAdapter1.getComofoipago_consorcio());
        Viewholder.data_consorcio.setText(getDataAdapter1.getData_consorcio());

        //region Método para buscar o nome dos produtos na tabela Produto.
        jsonArrayRequest = new JsonArrayRequest(GET_JSON_DATA_HTTP_URL+getDataAdapter1.getConta_consorcio(),

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
                            Viewholder.conta_consorcio.setText(nome);



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
                Static.setId_consorcio(Integer.parseInt(String.valueOf(Viewholder.id_consorcio.getText())));

                AlterarConsorcioNakasone frag = new AlterarConsorcioNakasone();

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
        public TextView id_consorcio, descricao_consorcio, valor_consorcio, conta_consorcio, comofoipago_consorcio, data_consorcio;

        public ProgressBar bar;
        CardView cad;
        RelativeLayout cdll;



        public ViewHolder(View itemView)
        {

            super(itemView);

            id_consorcio= (TextView) itemView.findViewById(R.id.id_consorcio);
            descricao_consorcio = (TextView) itemView.findViewById(R.id.descricao_consorcio);
            valor_consorcio= (TextView) itemView.findViewById(R.id.valor_consorcio);
            conta_consorcio = (TextView)itemView.findViewById(R.id.conta_consorcio);
            comofoipago_consorcio = (TextView) itemView.findViewById(R.id.comofoipago_consorcio) ;
            data_consorcio= (TextView) itemView.findViewById(R.id.data_consorcio) ;

        }
    }
}