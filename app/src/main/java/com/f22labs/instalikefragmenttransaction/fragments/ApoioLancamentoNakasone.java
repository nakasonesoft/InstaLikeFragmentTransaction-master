package com.f22labs.instalikefragmenttransaction.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.f22labs.instalikefragmenttransaction.R;
import com.f22labs.instalikefragmenttransaction.activities.MainActivity;

import butterknife.ButterKnife;


public class ApoioLancamentoNakasone extends BaseFragment{

    ImageView imglistaapoio, imgminidic;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_apoio, container, false);
//-----------------------------------------------------------
        imglistaapoio = (ImageView) view.findViewById(R.id.imglistaapoio); // Linkagem dos IDs (Layout/Código)
        imgminidic = (ImageView) view.findViewById(R.id.imgminidic); // Linkagem dos IDs (Layout/Código)

        imglistaapoio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragmentNavigation.pushFragment(new ListaContasApoioNakasone());
            }
        });
//-----------------------------------------------------------
        imgminidic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragmentNavigation.pushFragment(new MinidicNakasone());
            }
        });

        ButterKnife.bind(this, view);

        ( (MainActivity)getActivity()).updateToolbarTitle("Apoio");


        return view;
    }


}
