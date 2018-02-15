package com.f22labs.instalikefragmenttransaction.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.f22labs.instalikefragmenttransaction.R;
import com.f22labs.instalikefragmenttransaction.activities.MainActivity;

import butterknife.ButterKnife;


public class LancamentoDeNakasone extends BaseFragment{

    ImageView imgdespesalancamento,imgreceitalancamento,imgtranslancamento,imgsaquelancamento,imgdepositolancamento,imgcartaolancamento;
    ImageView imgcarneslancamento,imgimoveislancamento,imgconsorciolancamento,imgoutroslancamento;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {


        View view = inflater.inflate(R.layout.fragment_lancamento_de, container, false);


        imgdespesalancamento = (ImageView) view.findViewById(R.id.imgdespesalancamento);
        imgreceitalancamento = (ImageView) view.findViewById(R.id.imgreceitalancamento);
        imgtranslancamento = (ImageView) view.findViewById(R.id.imgtranslancamento);
        imgsaquelancamento = (ImageView) view.findViewById(R.id.imgsaquelancamento);
        imgdepositolancamento = (ImageView) view.findViewById(R.id.imgdepositolancamento);
        imgcartaolancamento = (ImageView) view.findViewById(R.id.imgcartaolancamento);
        imgcarneslancamento = (ImageView) view.findViewById(R.id.imgcarneslancamento);
        imgimoveislancamento = (ImageView) view.findViewById(R.id.imgimoveislancamento);
        imgconsorciolancamento = (ImageView) view.findViewById(R.id.imgconsorciolancamento);
        imgoutroslancamento = (ImageView) view.findViewById(R.id.imgoutroslancamento);



        //--------------------------------
        imgdespesalancamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragmentNavigation.pushFragment(new CadastroDespesaNakasone());
            }
        });
        //--------------------------------

        //region Clique do botão imgreceitalancamento
        imgreceitalancamento.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mFragmentNavigation.pushFragment(new CadastroReceitaNakasone());
            }
        });
        //endregion

        //region Clique do botão imgtranslancamento
        imgtranslancamento.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mFragmentNavigation.pushFragment(new CadastroTransferenciaNakasone());
            }
        });
        //endregion

        //region Clique do botão imgsaquelancamento
        imgsaquelancamento.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mFragmentNavigation.pushFragment(new CadastroSaqueNakasone());
            }
        });
        //endregion

        //region Clique do botão imgdepositolancamento
        imgdepositolancamento.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mFragmentNavigation.pushFragment(new CadastroDepositoNakasone());
            }
        });
        //endregion

        //region Clique do botão imgcartaolancamento ------------ FALTA
        imgcartaolancamento.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {


            }
        });
        //endregion

        //region Clique do botão imgcarneslancamento ------------ FALTA
        imgcarneslancamento.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

            }
        });
        //endregion

        //region Clique do botão imgimoveislancamento
        imgimoveislancamento.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mFragmentNavigation.pushFragment(new CadastroImoveisNakasone());

            }
        });
        //endregion

        //region Clique do botão imgconsorciolancamento
        imgconsorciolancamento.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mFragmentNavigation.pushFragment(new CadastroConsorcioNakasone());
            }
        });
        //endregion

        //region Clique do botão imgoutroslancamento
        imgoutroslancamento.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mFragmentNavigation.pushFragment(new CadastroOutrosNakasone());
            }
        });
        //endregion














        ButterKnife.bind(this, view);

        ( (MainActivity)getActivity()).updateToolbarTitle("Lançamento de:");


        return view;
    }


}
