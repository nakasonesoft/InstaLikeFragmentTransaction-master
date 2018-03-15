package com.f22labs.instalikefragmenttransaction.adapters;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
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
import com.f22labs.instalikefragmenttransaction.fragments.AlterarContaNakasone;
import com.f22labs.instalikefragmenttransaction.utils.Static;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class RecyclerViewAdapterConta extends RecyclerView.Adapter<RecyclerViewAdapterConta.ViewHolder>
{

    //Context context;
    private Activity context;

    List<GetDataAdapter> getDataAdapter;
    ImageLoader imageLoader1;
    String grupo;

    Object mContext;
    String GET_JSON_DATA_HTTP_URL = "http://premiumcontrol.com.br/NakasoneSoftapp/select/selectronaldo.php?id_conta=";

    JsonArrayRequest jsonArrayRequest ;

    RequestQueue requestQueue ;
    String nome;



    public RecyclerViewAdapterConta(List<GetDataAdapter> getDataAdapter, Activity context){

        super();
        this.getDataAdapter = getDataAdapter;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_items_conta, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder Viewholder, final int position)
    {

        final GetDataAdapter getDataAdapter1 =  getDataAdapter.get(position);
        final String distancia ;





        Viewholder.id_contas_conta.setText(getDataAdapter1.getId_conta_conta());
        Viewholder.txtsaldoinicial_conta.setText(getDataAdapter1.getSaldoinicial_conta());
        //Viewholder.txtid_grupo.setText(getDataAdapter1.getId_grupo_conta());
        Viewholder.txtDatafechamento_conta.setText(getDataAdapter1.getDatafechamento_conta());
        Viewholder.txtNome_conta.setText(getDataAdapter1.getNome_conta());

        //region Método para buscar o nome dos produtos na tabela Produto.
        jsonArrayRequest = new JsonArrayRequest("http://premiumcontrol.com.br/NakasoneSoftapp/select/selectgruporonaldo.php?id_grupo="+getDataAdapter1.getId_grupo_conta(),

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
                            Viewholder.txtid_grupo.setText(nome);
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






        Viewholder.itemView.setOnClickListener(
                new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Static.setId_conta(Integer.parseInt(String.valueOf(Viewholder.id_contas_conta.getText())));

                AlterarContaNakasone frag = new AlterarContaNakasone();

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

        public TextView txtNome_conta, txtDatafechamento_conta,txtid_grupo,txtsaldoinicial_conta,id_contas_conta;

        public ProgressBar bar;
        CardView cad;
        RelativeLayout cdll;



        public ViewHolder(View itemView)
        {

            super(itemView);

            id_contas_conta= (TextView) itemView.findViewById(R.id.id_contas_conta);
            txtsaldoinicial_conta = (TextView) itemView.findViewById(R.id.txtsaldoinicial_conta);
            txtid_grupo= (TextView) itemView.findViewById(R.id.txtid_grupo);
            txtDatafechamento_conta = (TextView)itemView.findViewById(R.id.txtDatafechamento_conta);
            txtNome_conta = (TextView) itemView.findViewById(R.id.txtNome_conta) ;




            bar=(ProgressBar)itemView.findViewById(R.id.progressBar);
            cad = (CardView)itemView.findViewById(R.id.cardview1);
            cdll= (RelativeLayout) itemView.findViewById(R.id.cdll2);


        }



    }

}