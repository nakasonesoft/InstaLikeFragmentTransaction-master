package com.f22labs.instalikefragmenttransaction.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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


public class ContaDividasMensal extends BaseFragment
{

    Handler mHandler = new Handler();

    TextView mText;

    ImageView total_patrimonio,total_dividas,patrimonio_liq,desempenho,desempenho_ac;
    Button total;

    GridView mGridView;

    String urlAddress  = "http://premiumcontrol.com.br/NakasoneSoftapp/select/mov_contas_patrimonio_id_conta.php?id_cliente="+Static.getId_cliente()+"&id_conta=";

    public static GridView gvPrimeiroTotal = null;
    final ArrayList<String> items = new ArrayList<String>();
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {


        View view = inflater.inflate(R.layout.contas_dividas_mensal, container, false);

        final GridView gv= (GridView) view.findViewById(R.id.conta_dividas_mensalgrid);



        ButterKnife.bind(this, view);

        ( (MainActivity)getActivity()).updateToolbarTitle("Dividas");
        urlAddress = urlAddress + Static.getId_conta();

        new Downloader(getActivity(),urlAddress,gv).execute();
        return view;
    }

}
