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

public class RecyclerViewAdapterContasDesativadas extends RecyclerView.Adapter<RecyclerViewAdapterContasDesativadas.ViewHolder>
{

    //Context context;
    private Activity context;

    List<GetDataAdapter> getDataAdapter;
    ImageLoader imageLoader1;

    Object mContext;
    public RecyclerViewAdapterContasDesativadas(List<GetDataAdapter> getDataAdapter, Activity context){

        super();
        this.getDataAdapter = getDataAdapter;
        this.context = context;


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_items_contas_desativadas, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder Viewholder, final int position)
    {

        final GetDataAdapter getDataAdapter1 =  getDataAdapter.get(position);
        final String distancia ;

        Viewholder.id_conta_desativada.setText(getDataAdapter1.getId_conta_conta());
        Viewholder.saldo_desativada.setText(getDataAdapter1.getSaldoinicial_conta());
        Viewholder.nome_conta_desativada.setText(getDataAdapter1.getNome_conta());
        Viewholder.data_encerramento_desativada.setText(getDataAdapter1.getDatafechamento_conta());






        Viewholder.itemView.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        Static.setId_conta(Integer.parseInt(String.valueOf(Viewholder.id_conta_desativada.getText())));

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

        public TextView id_conta_desativada, saldo_desativada,data_encerramento_desativada,nome_conta_desativada;

        public ProgressBar bar;
        CardView cad;
        RelativeLayout cdll;



        public ViewHolder(View itemView)
        {

            super(itemView);

            id_conta_desativada= (TextView) itemView.findViewById(R.id.id_conta_desativada);
            saldo_desativada = (TextView) itemView.findViewById(R.id.saldo_desativada);

            data_encerramento_desativada= (TextView) itemView.findViewById(R.id.data_encerramento_desativada);
            nome_conta_desativada = (TextView)itemView.findViewById(R.id.nome_conta_desativada);




            bar=(ProgressBar)itemView.findViewById(R.id.progressBar);
            cad = (CardView)itemView.findViewById(R.id.cardview1);
            cdll= (RelativeLayout) itemView.findViewById(R.id.cdll2);


        }



    }


}