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
import com.f22labs.instalikefragmenttransaction.fragments.AlterarCarneNakasone;
import com.f22labs.instalikefragmenttransaction.fragments.AlterarDespesaNakasone;
import com.f22labs.instalikefragmenttransaction.utils.Static;

import java.util.List;

public class RecyclerViewAdapterCarne extends RecyclerView.Adapter<RecyclerViewAdapterCarne.ViewHolder>
{

    //Context context;
    private Activity context;

    List<GetDataAdapter> getDataAdapter;
    ImageLoader imageLoader1;
    Object mContext;




    public RecyclerViewAdapterCarne(List<GetDataAdapter> getDataAdapter, Activity context){
        super();
        this.getDataAdapter = getDataAdapter;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_items_carne, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder Viewholder, final int position)
    {

        final GetDataAdapter getDataAdapter1 =  getDataAdapter.get(position);
        final String distancia ;

        Viewholder.id_carne.setText(getDataAdapter1.getId_carne());
        Viewholder.descricao_carne.setText(getDataAdapter1.getDescricao_carne());
        Viewholder.valor_carne.setText(getDataAdapter1.getValor_carne());
        Viewholder.datafinal_carne.setText(getDataAdapter1.getDatafinal_carne());
        Viewholder.qntd_carne.setText(getDataAdapter1.getQntd_carne());

        Viewholder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Static.setId_carne(Integer.parseInt(String.valueOf(Viewholder.id_carne.getText())));

                AlterarCarneNakasone frag = new AlterarCarneNakasone();

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

        public TextView id_carne, descricao_carne, valor_carne, datafinal_carne, qntd_carne, valor_total_carne;

        public ProgressBar bar;
        CardView cad;
        RelativeLayout cdll;



        public ViewHolder(View itemView)
        {

            super(itemView);

            id_carne= (TextView) itemView.findViewById(R.id.id_carne);
            descricao_carne = (TextView) itemView.findViewById(R.id.descricao_carne);
            valor_carne= (TextView) itemView.findViewById(R.id.valor_carne);
            datafinal_carne = (TextView)itemView.findViewById(R.id.datafinal_carne);
            qntd_carne = (TextView) itemView.findViewById(R.id.parcelas);
            valor_total_carne= (TextView) itemView.findViewById(R.id.valor_total_carne);

        }
    }
}