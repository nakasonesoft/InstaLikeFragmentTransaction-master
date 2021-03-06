package com.f22labs.instalikefragmenttransaction.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.f22labs.instalikefragmenttransaction.R;
import com.f22labs.instalikefragmenttransaction.activities.ListViewTeste;
import com.f22labs.instalikefragmenttransaction.activities.MainActivity;
import com.f22labs.instalikefragmenttransaction.activities.implantacaoErotina;
import com.f22labs.instalikefragmenttransaction.adapters.RecyclerViewAdapterDesativarContas;

import butterknife.ButterKnife;


public class MaisApoioNakasone extends BaseFragment{

    ImageView imgperguntas,imgpasso,imgimplantacao,imgdicaseconomia,imgfaleconosco;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_mais_apoio, container, false);

        imgperguntas = (ImageView) view.findViewById(R.id.imgperguntas);
        imgpasso = (ImageView) view.findViewById(R.id.imgpasso);
        imgimplantacao = (ImageView) view.findViewById(R.id.imgimplantacao);
        imgdicaseconomia = (ImageView) view.findViewById(R.id.imgdicaseconomia);
        imgfaleconosco = (ImageView) view.findViewById(R.id.imgfaleconosco);


        imgpasso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragmentNavigation.pushFragment(new RecyclerDesativarContas());
            }
        });

        imgdicaseconomia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragmentNavigation.pushFragment(new DicasEconomiaNakasone());
            }
        });

        imgfaleconosco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragmentNavigation.pushFragment(new FaleConosco());
            }
        });

        imgperguntas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                mFragmentNavigation.pushFragment(new PerguntasFrequentes());
            }
        });

        imgimplantacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), implantacaoErotina.class);
                startActivity(intent);
            }
        });
        ButterKnife.bind(this, view);

        ( (MainActivity)getActivity()).updateToolbarTitle("Mais Apoio");


        return view;
    }
}
