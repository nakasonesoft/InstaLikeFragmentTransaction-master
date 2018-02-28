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

import com.android.volley.toolbox.ImageLoader;
import com.f22labs.instalikefragmenttransaction.R;
import com.f22labs.instalikefragmenttransaction.activities.MainActivity;
import com.f22labs.instalikefragmenttransaction.fragments.AlterarContaNakasone;
import com.f22labs.instalikefragmenttransaction.fragments.RecyclerDividasCartaoCredito1;
import com.f22labs.instalikefragmenttransaction.utils.Static;

import java.util.List;

public class RecyclerViewAdapterDividasCartaoCredito extends RecyclerView.Adapter<RecyclerViewAdapterDividasCartaoCredito.ViewHolder>
{

    //Context context;
    private Activity context;

    List<GetDataAdapter> getDataAdapter;
    ImageLoader imageLoader1;
    String status;

    Object mContext;
    public RecyclerViewAdapterDividasCartaoCredito(List<GetDataAdapter> getDataAdapter, Activity context){

        super();
        this.getDataAdapter = getDataAdapter;
        this.context = context;


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_items_dividas_cartao, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder Viewholder, final int position)
    {

        final GetDataAdapter getDataAdapter1 =  getDataAdapter.get(position);
        final String distancia ;



        Viewholder.descricao_cartao_credito.setText(getDataAdapter1.getNome_conta());
        Viewholder.data_cartao_credito.setText(getDataAdapter1.getDatafechamento_conta());
        Viewholder.saldo_cartao_credito.setText(getDataAdapter1.getSaldoinicial_conta());
        Viewholder.id_cartao_credito.setText(getDataAdapter1.getId_conta_conta());


        status = getDataAdapter1.getStatus();
      switch (Integer.parseInt(getDataAdapter1.getStatus())){
          case 0:
              Viewholder.status_cartao_credito.setText("Desativada");

              break;
          case 1:
              Viewholder.status_cartao_credito.setText("Ativada");

              break;
      }


        Viewholder.itemView.setOnClickListener(
                new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Static.setId_conta(Integer.parseInt(String.valueOf(Viewholder.id_cartao_credito.getText())));

                RecyclerDividasCartaoCredito1 frag = new RecyclerDividasCartaoCredito1();

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

        public TextView descricao_cartao_credito, data_cartao_credito,saldo_cartao_credito,status_cartao_credito,id_cartao_credito;

        public ProgressBar bar;
        CardView cad;
        RelativeLayout cdll;



        public ViewHolder(View itemView)
        {

            super(itemView);

            descricao_cartao_credito= (TextView) itemView.findViewById(R.id.descricao_cartao_credito);
            data_cartao_credito = (TextView) itemView.findViewById(R.id.data_cartao_credito);
            saldo_cartao_credito= (TextView) itemView.findViewById(R.id.saldo_cartao_credito);
            status_cartao_credito = (TextView)itemView.findViewById(R.id.status_cartao_credito);
            id_cartao_credito = (TextView) itemView.findViewById(R.id.id_cartao_credito) ;

            bar=(ProgressBar)itemView.findViewById(R.id.progressBar);
            cad = (CardView)itemView.findViewById(R.id.cardview1);
            cdll= (RelativeLayout) itemView.findViewById(R.id.cdll2);
        }
    }
}