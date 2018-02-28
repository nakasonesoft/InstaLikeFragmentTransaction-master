package com.f22labs.instalikefragmenttransaction.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.f22labs.instalikefragmenttransaction.R;
import com.f22labs.instalikefragmenttransaction.activities.MainActivity;

import butterknife.ButterKnife;


public class AltExcLancamentoNakasone extends BaseFragment
{

    ImageView imgAltExcCartao, imgAltExcDespesa,imgAltExcReceita,imgAltExcTransferencia,imgAltExcSaque,imgAltExcDeposito,imgAltExcImovel,imgAltExcConsorcio,imgAltExcOutros;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {


        View view = inflater.inflate(R.layout.fragment_lancamento_alt_exc, container, false);

        // Linkagem dos IDs (Layout/Código)
        imgAltExcCartao = (ImageView) view.findViewById(R.id.imgAltExcCartao);
        imgAltExcDespesa = (ImageView) view.findViewById(R.id.imgAltExcDespesa);
        imgAltExcReceita = (ImageView) view.findViewById(R.id.imgAltExcReceita);
        imgAltExcTransferencia = (ImageView) view.findViewById(R.id.imgAltExcTransferencia);
        imgAltExcSaque = (ImageView) view.findViewById(R.id.imgAltExcSaque);
        imgAltExcDeposito = (ImageView) view.findViewById(R.id.imgAltExcDeposito);
        imgAltExcImovel = (ImageView) view.findViewById(R.id.imgAltExcImovel);
        imgAltExcConsorcio = (ImageView) view.findViewById(R.id.imgAltExcConsorcio);
        imgAltExcOutros = (ImageView) view.findViewById(R.id.imgAltExcOutros);

        imgAltExcCartao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragmentNavigation.pushFragment(new RecyclerAltExcFatura());
            }
        });

        imgAltExcDespesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragmentNavigation.pushFragment(new RecyclerAltExcDespesa());
            }
        });

        imgAltExcReceita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragmentNavigation.pushFragment(new RecyclerAltExcReceita());
            }
        });

        imgAltExcTransferencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragmentNavigation.pushFragment(new RecyclerAltExcTransferencia());
            }
        });

        imgAltExcSaque.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragmentNavigation.pushFragment(new RecyclerAltExcSaque());
            }
        });

        imgAltExcDeposito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragmentNavigation.pushFragment(new RecyclerAltExcDeposito());
            }
        });

        imgAltExcImovel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragmentNavigation.pushFragment(new RecyclerAltExcImovel());
            }
        });

        imgAltExcConsorcio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragmentNavigation.pushFragment(new RecyclerAltExcConsorcio());
            }
        });

        imgAltExcOutros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragmentNavigation.pushFragment(new RecyclerAltExcOutros());
            }
        });

        ButterKnife.bind(this, view);

        ( (MainActivity)getActivity()).updateToolbarTitle("Alterar/Excluir Lançamento");


        return view;
    }
}
