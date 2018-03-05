package com.f22labs.instalikefragmenttransaction.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.f22labs.instalikefragmenttransaction.R;
import com.f22labs.instalikefragmenttransaction.activities.MainActivity;
import com.f22labs.instalikefragmenttransaction.utils.Downloader;

import java.util.ArrayList;

import butterknife.ButterKnife;


public class FragmentGrid2 extends BaseFragment
{

    Handler mHandler = new Handler();

    TextView mText;

    ImageView total_patrimonio,total_dividas,patrimonio_liq,desempenho,desempenho_ac;
    ImageView total_patrimonio1,total_dividas1,patrimonio_liq1,desempenho1,desempenho_ac1;

    GridView mGridView;

    final static String urlAddress  = "http://premiumcontrol.com.br/NakasoneSoftapp/select/patrimonio.php";
    final static String urlAddress2  = "http://premiumcontrol.com.br/NakasoneSoftapp/select/total_dividas.php";
    final static String urlAddress3  = "http://premiumcontrol.com.br/NakasoneSoftapp/select/patrimonio_liquido.php";
    final static String urlAddress4  = "http://premiumcontrol.com.br/NakasoneSoftapp/select/desempenho.php";
    final static String urlAddress5  = "http://premiumcontrol.com.br/NakasoneSoftapp/select/desempenho_acumulado.php";

    public static GridView gvPrimeiroTotal = null;
    final ArrayList<String> items = new ArrayList<String>();
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.bailao2, container, false);


        //region FindViews
        total_patrimonio = (ImageView)view.findViewById(R.id.total_patrimonio);
        total_dividas = (ImageView)view.findViewById(R.id.total_dividas);
        patrimonio_liq = (ImageView)view.findViewById(R.id.patrimonio_liq);
        desempenho = (ImageView)view.findViewById(R.id.desempenho);
        desempenho_ac = (ImageView)view.findViewById(R.id.desempenho_ac);

        total_patrimonio1 = (ImageView)view.findViewById(R.id.total_patrimonio1);
        total_dividas1 = (ImageView)view.findViewById(R.id.total_dividas1);
        patrimonio_liq1 = (ImageView)view.findViewById(R.id.patrimonio_liq1);
        desempenho1 = (ImageView)view.findViewById(R.id.desempenho1);
        desempenho_ac1 = (ImageView)view.findViewById(R.id.desempenho_ac1);

        final GridView gv= (GridView) view.findViewById(R.id.gv22);
        final GridView gv2= (GridView)view. findViewById(R.id.gv23);
        final GridView gv3= (GridView) view.findViewById(R.id.gv24);
        final GridView gv4= (GridView) view.findViewById(R.id.gv25);
        final GridView gv5= (GridView) view.findViewById(R.id.gv26);


//endregion

        //region Cliques do Botão


        total_patrimonio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                total_patrimonio1.setVisibility(View.VISIBLE);
                new Downloader(getActivity(),urlAddress,gv).execute();
                gv.setVisibility(View.VISIBLE);
                total_patrimonio.setVisibility(View.GONE);
            }
        });

        total_patrimonio1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                total_patrimonio.setVisibility(View.VISIBLE);
                gv.setVisibility(View.GONE);
                total_patrimonio1.setVisibility(View.GONE);
            }
        });


        total_dividas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                total_dividas1.setVisibility(View.VISIBLE);
                new Downloader(getActivity(),urlAddress2,gv2).execute();
                gv2.setVisibility(View.VISIBLE);
                total_dividas.setVisibility(View.GONE);
            }
        });

        total_dividas1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                total_dividas.setVisibility(View.VISIBLE);
                gv2.setVisibility(View.GONE);
                total_dividas1.setVisibility(View.GONE);
            }
        });


        patrimonio_liq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                patrimonio_liq1.setVisibility(View.VISIBLE);
                new Downloader(getActivity(),urlAddress3,gv3).execute();
                gv3.setVisibility(View.VISIBLE);
                patrimonio_liq.setVisibility(View.GONE);
            }
        });

        patrimonio_liq1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                patrimonio_liq.setVisibility(View.VISIBLE);
                gv3.setVisibility(View.GONE);
                patrimonio_liq1.setVisibility(View.GONE);
            }
        });



        desempenho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                desempenho1.setVisibility(View.VISIBLE);
                new Downloader(getActivity(),urlAddress4,gv4).execute();
                gv4.setVisibility(View.VISIBLE);
                desempenho.setVisibility(View.GONE);
            }
        });

        desempenho1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                desempenho.setVisibility(View.VISIBLE);
                gv4.setVisibility(View.GONE);
                desempenho1.setVisibility(View.GONE);
            }
        });


        desempenho_ac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                desempenho_ac1.setVisibility(View.VISIBLE);
                new Downloader(getActivity(),urlAddress5,gv5).execute();
                gv5.setVisibility(View.VISIBLE);
                desempenho_ac.setVisibility(View.GONE);
            }
        });

        desempenho_ac1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                desempenho_ac.setVisibility(View.VISIBLE);
                gv5.setVisibility(View.GONE);
                desempenho_ac1.setVisibility(View.GONE);
            }
        });
        //endregion

        ButterKnife.bind(this, view);

        ( (MainActivity)getActivity()).updateToolbarTitle("Desempenho");


        return view;
    }

}
