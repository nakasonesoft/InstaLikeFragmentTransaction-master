package com.f22labs.instalikefragmenttransaction.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.f22labs.instalikefragmenttransaction.R;
import com.f22labs.instalikefragmenttransaction.activities.MainActivity;
import com.f22labs.instalikefragmenttransaction.utils.Static;

import butterknife.ButterKnife;


public class TiposPatrimonio extends BaseFragment{

    LinearLayout dinheiro1,invest1,contas1,bens1, total;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {


        View view = inflater.inflate(R.layout.fragment_tipos_patrimonio, container, false);

        dinheiro1 = (LinearLayout)view.findViewById(R.id.dinheiro1);
        invest1 = (LinearLayout)view.findViewById(R.id.invest1);
        contas1 = (LinearLayout)view.findViewById(R.id.contas1);
        bens1 = (LinearLayout)view.findViewById(R.id.bens1);
        total = (LinearLayout)view.findViewById(R.id.TotalPatrimonio);

        total.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Static.setTipo_patrimonio(9);
                ( (MainActivity)getActivity()).updateToolbarTitle("Dinheiro");
                mFragmentNavigation.pushFragment(new GridTotalPatrimonioMensal());

            }
        });

        dinheiro1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Static.setTipo_patrimonio(9);
                ( (MainActivity)getActivity()).updateToolbarTitle("Dinheiro");
                mFragmentNavigation.pushFragment(new RecyclerContasPatrimonio());

            }
        });


        invest1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Static.setTipo_patrimonio(2);
                ( (MainActivity)getActivity()).updateToolbarTitle("Investimentos e outros");
                mFragmentNavigation.pushFragment(new RecyclerContasPatrimonio());
            }
        });


        contas1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Static.setTipo_patrimonio(10);
                ( (MainActivity)getActivity()).updateToolbarTitle("Contas a receber");
                mFragmentNavigation.pushFragment(new RecyclerContasPatrimonio());
            }
        });


        bens1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Static.setTipo_patrimonio(8);
                ( (MainActivity)getActivity()).updateToolbarTitle("Bens");
                mFragmentNavigation.pushFragment(new RecyclerContasPatrimonio());
            }
        });


        ( (MainActivity)getActivity()).updateToolbarTitle("Tipos de patrimônio");
        ButterKnife.bind(this, view);




        return view;
    }


}
