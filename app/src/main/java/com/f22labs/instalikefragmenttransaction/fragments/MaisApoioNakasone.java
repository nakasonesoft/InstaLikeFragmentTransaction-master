package com.f22labs.instalikefragmenttransaction.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.f22labs.instalikefragmenttransaction.R;
import com.f22labs.instalikefragmenttransaction.activities.MainActivity;

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

        ButterKnife.bind(this, view);

        ( (MainActivity)getActivity()).updateToolbarTitle("Mais Apoio");


        return view;
    }
}
