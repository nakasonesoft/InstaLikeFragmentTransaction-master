package com.f22labs.instalikefragmenttransaction.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.f22labs.instalikefragmenttransaction.R;
import com.f22labs.instalikefragmenttransaction.activities.MainActivity;

import butterknife.ButterKnife;


public class RelatoriosNakasone extends BaseFragment{

    ImageView imgfichaindividual,imgdiario,imgdividasrelatorio,imgmensal,imgdesempenho,imgprevisao,imgacompanhamento;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_relatorios, container, false);

        imgfichaindividual = (ImageView) view.findViewById(R.id.imgfichaindividual);
        imgdiario = (ImageView) view.findViewById(R.id.imgdiario);
        imgdividasrelatorio = (ImageView) view.findViewById(R.id.imgdividasrelatorio);
        imgmensal = (ImageView) view.findViewById(R.id.imgmensal);
        imgdesempenho = (ImageView) view.findViewById(R.id.imgdesempenho);
        imgprevisao = (ImageView) view.findViewById(R.id.imgprevisao);
        imgacompanhamento = (ImageView) view.findViewById(R.id.imgacompanhamento);

        imgdesempenho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragmentNavigation.pushFragment(new FragmentGrid2());
            }
        });


        imgmensal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragmentNavigation.pushFragment(new Mensal_anual());
            }
        });

        imgdiario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragmentNavigation.pushFragment(new RecyclerDiario2());
            }
        });

        imgfichaindividual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragmentNavigation.pushFragment(new FichaIndividual());
            }
        });

        imgdividasrelatorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragmentNavigation.pushFragment(new RecyclerDividasCartaoCredito());
            }
        });

        ButterKnife.bind(this, view);

        ( (MainActivity)getActivity()).updateToolbarTitle("Relatórios");


        return view;
    }
}
