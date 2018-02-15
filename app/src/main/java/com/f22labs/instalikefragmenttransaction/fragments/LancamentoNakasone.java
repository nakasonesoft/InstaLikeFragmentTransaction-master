package com.f22labs.instalikefragmenttransaction.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.f22labs.instalikefragmenttransaction.R;
import com.f22labs.instalikefragmenttransaction.activities.MainActivity;

import butterknife.ButterKnife;


public class LancamentoNakasone extends BaseFragment
{

    ImageView imgfazerlancamento,imgAltExcLancamento,imgcadastrarcontas,imgcadastrarsaldo,imgapoio; // Criação da variável linkada ao layout

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {


        View view = inflater.inflate(R.layout.fragment_lancamento, container, false);

        // Linkagem dos IDs (Layout/Código)
        imgfazerlancamento = (ImageView) view.findViewById(R.id.imgfazerlancamento);
        imgapoio = (ImageView) view.findViewById(R.id.imgapoio);
        imgAltExcLancamento = (ImageView) view.findViewById(R.id.imgAltExcLancamento);
        imgcadastrarcontas = (ImageView) view.findViewById(R.id.imgcadastrarcontas);
        imgcadastrarsaldo = (ImageView) view.findViewById(R.id.imgcadastrarsaldo);

        //region Clique do Botão -> Fazer Lançamento

        imgfazerlancamento.setOnClickListener(new View.OnClickListener() // Clique do botão
        {
            @Override
            public void onClick(View v)
            {
                mFragmentNavigation.pushFragment(new LancamentoDeNakasone()); // "a href"
            }
        });
        //endregion

        //region Clique do Botão -> Apoio
        imgapoio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragmentNavigation.pushFragment(new ApoioLancamentoNakasone());
            }
        });
        //endregion

        //region Clique do Botão -> Alterar/Excluir Lançamento
        imgAltExcLancamento.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mFragmentNavigation.pushFragment(new AltExcLancamentoNakasone());
            }
        });
        //endregion

        //region Clique do Botão -> Cadastrar Contas
        imgcadastrarcontas.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mFragmentNavigation.pushFragment(new CadastroContasNakasone());
            }
        });
        //endregion

        //region Clique do Botão -> Cadastrar/Alterar Saldo Inicial
        imgcadastrarsaldo.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mFragmentNavigation.pushFragment(new RecyclerConta());
            }
        });

        //endregion



        ButterKnife.bind(this, view);

        ( (MainActivity)getActivity()).updateToolbarTitle("Lançamento");


        return view;
    }
}
