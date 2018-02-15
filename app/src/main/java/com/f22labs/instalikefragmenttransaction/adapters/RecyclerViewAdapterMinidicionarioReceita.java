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

import java.util.List;

public class RecyclerViewAdapterMinidicionarioReceita extends RecyclerView.Adapter<RecyclerViewAdapterMinidicionarioReceita.ViewHolder>
{

    //Context context;
    private Activity context;

    List<GetDataAdapterMinidicionarioReceita> getDataAdapter;
    ImageLoader imageLoader1;

    Object mContext;
    public RecyclerViewAdapterMinidicionarioReceita(List<GetDataAdapterMinidicionarioReceita> getDataAdapter, Activity context){

        super();
        this.getDataAdapter = getDataAdapter;
        this.context = context;


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_items_minidicionario_receita, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder Viewholder, final int position)
    {
        final GetDataAdapterMinidicionarioReceita getDataAdapter1 =  getDataAdapter.get(position);
        final String distancia ;

        Viewholder.id_item_receita.setText(getDataAdapter1.getId_item());
        Viewholder.descricao_item_receita.setText(getDataAdapter1.getDescricao_item());
        Viewholder.id_grupo_receita.setText(getDataAdapter1.getId_grupo());

        Viewholder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //Static.setIdevento(Integer.parseInt(String.valueOf(Viewholder.idEvento.getText())));

                //evento frag = new evento();

                //MainActivity mainActivity = (MainActivity) context;

                //mainActivity.switchContent(frag);
            }
        });


    }

    @Override
    public int getItemCount() {

        return getDataAdapter.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder
    {

        public TextView id_item_receita, descricao_item_receita, id_grupo_receita;

        public ProgressBar bar;
        CardView cad;
        RelativeLayout cdll;



        public ViewHolder(View itemView)
        {

            super(itemView);

            id_item_receita= (TextView) itemView.findViewById(R.id.id_item_receita);
            descricao_item_receita = (TextView) itemView.findViewById(R.id.descricao_item_receita);
            id_grupo_receita= (TextView) itemView.findViewById(R.id.id_grupo_receita);

            bar=(ProgressBar)itemView.findViewById(R.id.progressBar);
            cad = (CardView)itemView.findViewById(R.id.cardview1);
            cdll= (RelativeLayout) itemView.findViewById(R.id.cdll2);


        }



    }


}