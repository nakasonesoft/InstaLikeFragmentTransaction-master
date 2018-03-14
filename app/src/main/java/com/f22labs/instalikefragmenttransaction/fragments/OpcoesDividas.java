package com.f22labs.instalikefragmenttransaction.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.f22labs.instalikefragmenttransaction.R;
import com.f22labs.instalikefragmenttransaction.activities.MainActivity;
import com.f22labs.instalikefragmenttransaction.utils.Static;

import butterknife.ButterKnife;


public class OpcoesDividas extends BaseFragment{

    LinearLayout contas_dividas,total_dividas;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {


        View view = inflater.inflate(R.layout.fragment_opcoes_dividas, container, false);

        contas_dividas = (LinearLayout)view.findViewById(R.id.contas_dividas);
        total_dividas = (LinearLayout)view.findViewById(R.id.total_dividas);

        contas_dividas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ( (MainActivity)getActivity()).updateToolbarTitle("Contas de dívidas");
                mFragmentNavigation.pushFragment(new RecyclerContaDividas());
            }
        });

        total_dividas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Static.setTipo_patrimonio(8);
                ( (MainActivity)getActivity()).updateToolbarTitle("Bens");
                mFragmentNavigation.pushFragment(new TotalMensalDividas());
            }
        });

        ( (MainActivity)getActivity()).updateToolbarTitle("Total de dívidas");
        ButterKnife.bind(this, view);

        return view;
    }
}