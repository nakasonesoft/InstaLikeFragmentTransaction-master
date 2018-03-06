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
import com.f22labs.instalikefragmenttransaction.fragments.ContaPatrimonioMensal;
import com.f22labs.instalikefragmenttransaction.utils.Static;

import java.util.List;

public class RecyclerViewAdapterContasPatrimonio extends RecyclerView.Adapter<RecyclerViewAdapterContasPatrimonio.ViewHolder>
{

    //Context context;
    private Activity context;

    List<GetDataAdapterContasPatrimonio> getDataAdapter;
    ImageLoader imageLoader1;

    Object mContext;
    public RecyclerViewAdapterContasPatrimonio(List<GetDataAdapterContasPatrimonio> getDataAdapter, Activity context){

        super();
        this.getDataAdapter = getDataAdapter;
        this.context = context;


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_items_contas_patrimonio, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder Viewholder, final int position)
    {
        final GetDataAdapterContasPatrimonio getDataAdapter1 =  getDataAdapter.get(position);
        final String distancia ;

        Viewholder.id_conta_patrimonio.setText(getDataAdapter1.getId_conta());
        Viewholder.nome_conta.setText(getDataAdapter1.getNome_conta());

        Viewholder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Static.setId_conta(Integer.parseInt(String.valueOf(Viewholder.id_conta_patrimonio.getText())));

                ContaPatrimonioMensal frag = new ContaPatrimonioMensal();

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

        public TextView id_conta_patrimonio, nome_conta;

        public ProgressBar bar;
        CardView cad;
        RelativeLayout cdll;



        public ViewHolder(View itemView)
        {
            super(itemView);

            id_conta_patrimonio= (TextView) itemView.findViewById(R.id.id_conta_patrimonio);
            nome_conta = (TextView) itemView.findViewById(R.id.descricao_contas_patrimonio);

        }



    }


}