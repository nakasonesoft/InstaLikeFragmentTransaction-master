package com.f22labs.instalikefragmenttransaction.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.f22labs.instalikefragmenttransaction.R;
import com.f22labs.instalikefragmenttransaction.activities.MainActivity;

import butterknife.ButterKnife;


public class ListaContasApoioNakasone extends BaseFragment{

    ImageView imgativas;
    ImageView imgdesativadas;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_lista_contas, container, false);

        imgativas = (ImageView)view.findViewById(R.id.imgativas);
        imgdesativadas = (ImageView)view.findViewById(R.id.imgdesativadas);


        imgdesativadas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragmentNavigation.pushFragment(new RecyclerContasDesativadas());
            }
        });


        imgativas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragmentNavigation.pushFragment(new RecyclerContasAtivadas());
            }
        });



        ButterKnife.bind(this, view);

        ( (MainActivity)getActivity()).updateToolbarTitle("Lista de Contas");


        return view;
    }


}
