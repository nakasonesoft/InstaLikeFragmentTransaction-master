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
import com.f22labs.instalikefragmenttransaction.utils.Static;

import java.util.List;

public class RecyclerViewAdapterDespesa extends RecyclerView.Adapter<RecyclerViewAdapterDespesa.ViewHolder>
{

    //Context context;
    private Activity context;

    List<GetDataAdapter> getDataAdapter;
    ImageLoader imageLoader1;

    Object mContext;
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
        Viewholder.conta_despesas.setText(getDataAdapter1.getConta_despesas());
        Viewholder.valor_despesas.setText(getDataAdapter1.getValor_despesas());
        Viewholder.praondefoi_despesa.setText(getDataAdapter1.getComofoipago_despesas());
        Viewholder.data_despesas.setText(getDataAdapter1.getData_despesas());


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
}