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
import com.f22labs.instalikefragmenttransaction.fragments.AlterarDepositoNakasone;
import com.f22labs.instalikefragmenttransaction.fragments.AlterarImoveisNakasone;
import com.f22labs.instalikefragmenttransaction.utils.Static;

import java.util.List;

public class RecyclerViewAdapterImovel extends RecyclerView.Adapter<RecyclerViewAdapterImovel.ViewHolder>
{

    //Context context;
    private Activity context;

    List<GetDataAdapter> getDataAdapter;
    ImageLoader imageLoader1;

    Object mContext;
    public RecyclerViewAdapterImovel(List<GetDataAdapter> getDataAdapter, Activity context){

        super();
        this.getDataAdapter = getDataAdapter;
        this.context = context;


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_items_imovel, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder Viewholder, final int position)
    {

        final GetDataAdapter getDataAdapter1 =  getDataAdapter.get(position);
        final String distancia ;
        Viewholder.id_prestImovel.setText(getDataAdapter1.getId_prestImovel());
        Viewholder.descricao_prestImovel.setText(getDataAdapter1.getDescricao_prestImovel());
        Viewholder.valor_prestImovel.setText(getDataAdapter1.getValor_prestImovel());
        Viewholder.conta_prestImovel.setText(getDataAdapter1.getConta_prestImovel());
        Viewholder.comofoipago_prestImovel.setText(getDataAdapter1.getComofoipago_prestImovel());
        Viewholder.data_prestImovel.setText(getDataAdapter1.getData_prestImovel());


        Viewholder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Static.setId_prestImovel(Integer.parseInt(String.valueOf(Viewholder.id_prestImovel.getText())));

                AlterarImoveisNakasone frag = new AlterarImoveisNakasone();

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
        public TextView id_prestImovel, descricao_prestImovel, valor_prestImovel, conta_prestImovel, comofoipago_prestImovel, data_prestImovel;

        public ProgressBar bar;
        CardView cad;
        RelativeLayout cdll;



        public ViewHolder(View itemView)
        {

            super(itemView);

            id_prestImovel= (TextView) itemView.findViewById(R.id.id_prestImovel);
            descricao_prestImovel = (TextView) itemView.findViewById(R.id.descricao_prestImovel);
            valor_prestImovel= (TextView) itemView.findViewById(R.id.valor_prestImovel);
            conta_prestImovel = (TextView)itemView.findViewById(R.id.conta_prestImovel);
            comofoipago_prestImovel = (TextView) itemView.findViewById(R.id.comofoipago_prestImovel) ;
            data_prestImovel= (TextView) itemView.findViewById(R.id.data_prestImovel) ;

        }
    }
}