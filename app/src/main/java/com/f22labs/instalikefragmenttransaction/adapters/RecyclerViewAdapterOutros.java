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
import com.f22labs.instalikefragmenttransaction.fragments.AlterarConsorcioNakasone;
import com.f22labs.instalikefragmenttransaction.fragments.AlterarOutrosNakasone;
import com.f22labs.instalikefragmenttransaction.utils.Static;

import java.util.List;

public class RecyclerViewAdapterOutros extends RecyclerView.Adapter<RecyclerViewAdapterOutros.ViewHolder>
{

    //Context context;
    private Activity context;

    List<GetDataAdapter> getDataAdapter;
    ImageLoader imageLoader1;

    Object mContext;
    public RecyclerViewAdapterOutros(List<GetDataAdapter> getDataAdapter, Activity context){

        super();
        this.getDataAdapter = getDataAdapter;
        this.context = context;


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_items_outros, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder Viewholder, final int position)
    {
        final GetDataAdapter getDataAdapter1 =  getDataAdapter.get(position);
        final String distancia ;
        Viewholder.id_outros.setText(getDataAdapter1.getId_outros());
        Viewholder.descricao_outros.setText(getDataAdapter1.getDescricao_outros());
        Viewholder.id_grupo.setText(getDataAdapter1.getId_grupo());
        Viewholder.id_grupo2.setText(getDataAdapter1.getId_grupo2());
        Viewholder.valor_outros.setText(getDataAdapter1.getValor_outros());
        Viewholder.data_outros.setText(getDataAdapter1.getData_outros());


        Viewholder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Static.setId_outros(Integer.parseInt(String.valueOf(Viewholder.id_outros.getText())));

                AlterarOutrosNakasone frag = new AlterarOutrosNakasone();

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

        public TextView id_outros, descricao_outros, id_grupo, id_grupo2, valor_outros, data_outros;

        public ProgressBar bar;
        CardView cad;
        RelativeLayout cdll;



        public ViewHolder(View itemView)
        {

            super(itemView);

            id_outros= (TextView) itemView.findViewById(R.id.id_outros);
            descricao_outros = (TextView) itemView.findViewById(R.id.descricao_outros);
            id_grupo= (TextView) itemView.findViewById(R.id.id_grupo);
            id_grupo2 = (TextView)itemView.findViewById(R.id.id_grupo2);
            valor_outros = (TextView) itemView.findViewById(R.id.valor_outros) ;
            data_outros= (TextView) itemView.findViewById(R.id.data_outros) ;

        }
    }
}