package com.f22labs.instalikefragmenttransaction.adapters;

import android.app.Activity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
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
import com.f22labs.instalikefragmenttransaction.utils.Static;

import java.util.List;

public class RecyclerViewAdapterContasAtivadas extends RecyclerView.Adapter<RecyclerViewAdapterContasAtivadas.ViewHolder>
{

    //Context context;
    private Activity context;

    List<GetDataAdapter> getDataAdapter;
    ImageLoader imageLoader1;

    Object mContext;
    public RecyclerViewAdapterContasAtivadas(List<GetDataAdapter> getDataAdapter, Activity context){

        super();
        this.getDataAdapter = getDataAdapter;
        this.context = context;


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_items_contas_ativas, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder Viewholder, final int position)
    {

        final GetDataAdapter getDataAdapter1 =  getDataAdapter.get(position);
        final String distancia ;


        Viewholder.id_conta_ativada.setText(getDataAdapter1.getId_conta_conta());
        Viewholder.saldo_ativada.setText(getDataAdapter1.getSaldoinicial_conta());
        Viewholder.nome_conta_ativada.setText(getDataAdapter1.getNome_conta());
        Viewholder.data_encerramento_ativada.setText(getDataAdapter1.getDatafechamento_conta());






        Viewholder.itemView.setOnClickListener(
                new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Static.setId_conta(Integer.parseInt(String.valueOf(Viewholder.id_conta_ativada.getText())));

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

        public TextView id_conta_ativada, saldo_ativada,data_encerramento_ativada,nome_conta_ativada;

        public ProgressBar bar;
        CardView cad;
        RelativeLayout cdll;



        public ViewHolder(View itemView)
        {

            super(itemView);

            id_conta_ativada= (TextView) itemView.findViewById(R.id.id_conta_ativada);
            saldo_ativada = (TextView) itemView.findViewById(R.id.saldo_ativada);
            data_encerramento_ativada= (TextView) itemView.findViewById(R.id.data_encerramento_ativada);
            nome_conta_ativada = (TextView)itemView.findViewById(R.id.nome_conta_ativada);




            bar=(ProgressBar)itemView.findViewById(R.id.progressBar);
            cad = (CardView)itemView.findViewById(R.id.cardview1);
            cdll= (RelativeLayout) itemView.findViewById(R.id.cdll2);


        }



    }


}