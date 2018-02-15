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
import com.f22labs.instalikefragmenttransaction.fragments.AlterarDespesaNakasone;
import com.f22labs.instalikefragmenttransaction.fragments.AlterarSaqueNakasone;
import com.f22labs.instalikefragmenttransaction.fragments.AlterarTransferenciaNakasone;
import com.f22labs.instalikefragmenttransaction.utils.Static;

import java.util.List;

public class RecyclerViewAdapterSaque extends RecyclerView.Adapter<RecyclerViewAdapterSaque.ViewHolder>
{

    //Context context;
    private Activity context;

    List<GetDataAdapter> getDataAdapter;
    ImageLoader imageLoader1;

    Object mContext;
    public RecyclerViewAdapterSaque(List<GetDataAdapter> getDataAdapter, Activity context){

        super();
        this.getDataAdapter = getDataAdapter;
        this.context = context;


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_items_saque, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder Viewholder, final int position)
    {

        final GetDataAdapter getDataAdapter1 =  getDataAdapter.get(position);
        final String distancia ;
        Viewholder.id_saque.setText(getDataAdapter1.getId_saque());
        Viewholder.descricao_saque.setText(getDataAdapter1.getDescricao_saque());
        Viewholder.valor_saque.setText(getDataAdapter1.getValor_saque());
        Viewholder.praondefoi_saque.setText(getDataAdapter1.getPraondefoi_saque());
        Viewholder.contaondeveio_saque.setText(getDataAdapter1.getContaondeveio_saque());
        Viewholder.data_saque.setText(getDataAdapter1.getData_saque());


        Viewholder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Static.setId_saque(Integer.parseInt(String.valueOf(Viewholder.id_saque.getText())));

                AlterarSaqueNakasone frag = new AlterarSaqueNakasone();

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
        public TextView id_saque, descricao_saque, valor_saque, praondefoi_saque, contaondeveio_saque, data_saque;

        public ProgressBar bar;
        CardView cad;
        RelativeLayout cdll;



        public ViewHolder(View itemView)
        {

            super(itemView);

            id_saque= (TextView) itemView.findViewById(R.id.id_saque);
            descricao_saque = (TextView) itemView.findViewById(R.id.descricao_saque);
            valor_saque= (TextView) itemView.findViewById(R.id.valor_saque);
            praondefoi_saque = (TextView)itemView.findViewById(R.id.praondefoi_saque);
            contaondeveio_saque = (TextView) itemView.findViewById(R.id.contaondeveio_saque) ;
            data_saque= (TextView) itemView.findViewById(R.id.data_saque) ;

        }
    }
}