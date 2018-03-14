package com.f22labs.instalikefragmenttransaction.fragments;

import android.os.Bundle;
import android.os.Handler;
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
import com.f22labs.instalikefragmenttransaction.utils.Static;

import java.util.ArrayList;

import butterknife.ButterKnife;


public class Mensal_anual extends BaseFragment{

    Handler mHandler = new Handler();
    TextView mText;
    GridView mGridView;

    ImageView breceita, bdespesa, bparcial, bsuper, btotal;
    ImageView breceita1, bdespesa1, bparcial1, bsuper1, btotal1;


    final static String urlAddress  = "http://premiumcontrol.com.br/NakasoneSoftapp/select/receita/total_despesa_necessaria.php?id_cliente="+ Static.getId_cliente()+"";
    final static String urlAddress_receita  =   "http://premiumcontrol.com.br/NakasoneSoftapp/select/receita/total_receita.php?id_cliente="+ Static.getId_cliente()+"";
    final static String ResultadoParcial  =   "http://premiumcontrol.com.br/NakasoneSoftapp/select/receita/resultado_parcial.php?id_cliente="+ Static.getId_cliente()+"";
    final static String TotalDespesasSuperfluas  ="http://premiumcontrol.com.br/NakasoneSoftapp/select/receita/total_despesa_superfluas.php?id_cliente="+ Static.getId_cliente()+"";
    final static String TotalGeral = "http://premiumcontrol.com.br/NakasoneSoftapp/select/receita/resultado_total.php?id_cliente="+ Static.getId_cliente()+"";

    public static GridView gvPrimeiroTotal = null;
    final ArrayList<String> items = new ArrayList<String>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {


        View view = inflater.inflate(R.layout.fragment_mensal_anual, container, false);

        breceita = (ImageView)view.findViewById(R.id.breceita);
        bdespesa = (ImageView)view.findViewById(R.id.bdespesa);
        bparcial = (ImageView)view.findViewById(R.id.bparcial);
        bsuper = (ImageView)view.findViewById(R.id.bsuper);
        btotal = (ImageView)view.findViewById(R.id.btotal);

        breceita1 = (ImageView)view.findViewById(R.id.breceita1);
        bdespesa1 = (ImageView)view.findViewById(R.id.bdespesa1);
        bparcial1 = (ImageView)view.findViewById(R.id.bparcial1);
        bsuper1 = (ImageView)view.findViewById(R.id.bsuper1);
        btotal1 = (ImageView)view.findViewById(R.id.btotal1);

        final GridView gv= (GridView) view.findViewById(R.id.gv);
        final GridView gv2= (GridView) view.findViewById(R.id.gvreceita);
        final GridView gvSuperfluas = (GridView) view.findViewById(R.id.gvSuperfluas);
        final GridView gvTotalGeral = (GridView) view.findViewById(R.id.gvTotalGeral);

        gvPrimeiroTotal  = (GridView) view.findViewById(R.id.gv33);

        //region receita
        breceita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gv2.setVisibility(View.VISIBLE);
                new Downloader(getActivity(),urlAddress_receita,gv2).execute();
                breceita.setVisibility(View.GONE);
                breceita1.setVisibility(View.VISIBLE);

            }
        });

        breceita1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gv2.setVisibility(View.GONE);
                breceita1.setVisibility(View.GONE);
                breceita.setVisibility(View.VISIBLE);

            }
        });
        //endregion

        //region despesa nec
        bdespesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gv.setVisibility(View.VISIBLE);
                new Downloader(getActivity(),urlAddress,gv).execute();
                bdespesa.setVisibility(View.GONE);
                bdespesa1.setVisibility(View.VISIBLE);
            }
        });

        bdespesa1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gv.setVisibility(View.GONE);
                bdespesa1.setVisibility(View.GONE);
                bdespesa.setVisibility(View.VISIBLE);
            }
        });
        //endregion

        //region parcial
        bparcial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gvPrimeiroTotal.setVisibility(View.VISIBLE);
                new Downloader(getActivity(),ResultadoParcial,gvPrimeiroTotal).execute();
                bparcial.setVisibility(View.GONE);
                bparcial1.setVisibility(View.VISIBLE);
            }
        });

        bparcial1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gvPrimeiroTotal.setVisibility(View.GONE);
                bparcial1.setVisibility(View.GONE);
                bparcial.setVisibility(View.VISIBLE);
            }
        });
        //endregion

        //region bsuper
        bsuper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gvSuperfluas.setVisibility(View.VISIBLE);
                new Downloader(getActivity(),TotalDespesasSuperfluas,gvSuperfluas).execute();
                bsuper.setVisibility(View.GONE);
                bsuper1.setVisibility(View.VISIBLE);
            }
        });

        bsuper1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gvSuperfluas.setVisibility(View.GONE);
                bsuper1.setVisibility(View.GONE);
                bsuper.setVisibility(View.VISIBLE);
            }
        });
        //endregion

        //region bsuper
        btotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gvTotalGeral.setVisibility(View.VISIBLE);
                new Downloader(getActivity(),TotalGeral,gvTotalGeral).execute();
                btotal.setVisibility(View.GONE);
                btotal1.setVisibility(View.VISIBLE);
            }
        });

        btotal1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gvTotalGeral.setVisibility(View.GONE);
                btotal1.setVisibility(View.GONE);
                btotal.setVisibility(View.VISIBLE);
            }
        });
        //endregion



     //region inutil
        ButterKnife.bind(this, view);

        ( (MainActivity)getActivity()).updateToolbarTitle("Mensal e Anual");
       //endregion

        return view;
    }

    //region Adaptador do Grid de Total Parcial
    // Assume it's known
    private static final int ROW_ITEMS = 3;

    private static final class GridAdapter extends BaseAdapter {

        final ArrayList<String> mItems;
        final int mCount;

        /**
         * Default constructor
         * @param items to fill data to
         */
        private GridAdapter(final ArrayList<String> items) {

            mCount = items.size() * ROW_ITEMS;
            mItems = new ArrayList<String>(mCount);

            // for small size of items it's ok to do it here, sync way
            for (String item : items) {
                // get separate string parts, divided by ,
                final String[] parts = item.split(",");

                // remove spaces from parts
                for (String part : parts) {
                    part.replace(" ", "");
                    mItems.add(part);
                }
            }
        }

        @Override
        public int getCount() {
            return mCount;
        }

        @Override
        public Object getItem(final int position) {
            return mItems.get(position);
        }

        @Override
        public long getItemId(final int position) {
            return position;
        }

        @Override
        public View getView(final int position, final View convertView, final ViewGroup parent) {

            View view = convertView;

            if (view == null)
            {
                view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
            }

            final TextView text = (TextView) view.findViewById(android.R.id.text1);

            text.setText(mItems.get(position));

            return view;
        }
    }
    //endregion

    public GridView getGridView() {return mGridView;}

    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount)
    {
        int lastItem = firstVisibleItem + visibleItemCount - 1;
        //mText.setText("Showing " + firstVisibleItem + "-" + lastItem + "/" + totalItemCount);
    }

    public void onScrollStateChanged(AbsListView view, int scrollState)
    {

        //gvPrimeiroTotal.setAdapter(new GridAdapter(items));
    }


}
