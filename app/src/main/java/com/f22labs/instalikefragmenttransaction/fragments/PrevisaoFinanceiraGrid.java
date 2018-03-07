package com.f22labs.instalikefragmenttransaction.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.easing.linear.Linear;
import com.f22labs.instalikefragmenttransaction.R;
import com.f22labs.instalikefragmenttransaction.activities.MainActivity;
import com.f22labs.instalikefragmenttransaction.utils.Downloader;
import com.f22labs.instalikefragmenttransaction.utils.Downloader2;

import java.util.ArrayList;

import butterknife.ButterKnife;


public class PrevisaoFinanceiraGrid extends BaseFragment
{

    Handler mHandler = new Handler();

    TextView mText;

    ImageView prev_desp,prev_desp1,cartao,cartao1,carne,carne1,total_desp,total_desp1,parcial1,parcial11,prev_rec,prev_rec1;
    ImageView des_pagar, des_pagar1;

    LinearLayout DES1,DES2,DES3, DES4, total_parcial1, salario, res_credito, res_des_desembolsar;


    GridView mGridView;

    final static String urlAddress  = "http://premiumcontrol.com.br/NakasoneSoftapp/select/select_previsao_financeira.php";

    public static GridView gvPrimeiroTotal = null;
    final ArrayList<String> items = new ArrayList<String>();
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.previsaofinanceiragrid, container, false);


        //region FindViews
        DES1 = (LinearLayout)view.findViewById(R.id.DES1);
        DES2 = (LinearLayout)view.findViewById(R.id.DES2);
        DES3 = (LinearLayout)view.findViewById(R.id.DES3);
        DES4 = (LinearLayout)view.findViewById(R.id.DES4);
        total_parcial1 = (LinearLayout)view.findViewById(R.id.total_parcial1);
        res_des_desembolsar = (LinearLayout)view.findViewById(R.id.res_des_desembolsar);
        salario = (LinearLayout)view.findViewById(R.id.salario);
        res_credito = (LinearLayout)view.findViewById(R.id.res_credito);

        des_pagar = (ImageView)view.findViewById(R.id.des_pagar);
        des_pagar1 = (ImageView)view.findViewById(R.id.des_pagar1);
        prev_desp = (ImageView)view.findViewById(R.id.prev_desp);
        prev_desp1 = (ImageView)view.findViewById(R.id.prev_desp1);
        cartao = (ImageView)view.findViewById(R.id.cartao);
        cartao1 = (ImageView)view.findViewById(R.id.cartao1);
        carne = (ImageView)view.findViewById(R.id.carne);
        carne1 = (ImageView)view.findViewById(R.id.carne1);
        total_desp = (ImageView)view.findViewById(R.id.total_desp);
        total_desp1 = (ImageView)view.findViewById(R.id.total_desp1);
        parcial1 = (ImageView)view.findViewById(R.id.parcial1);
        parcial11 = (ImageView)view.findViewById(R.id.parcial11);
        prev_rec = (ImageView)view.findViewById(R.id.prev_rec);
        prev_rec1 = (ImageView)view.findViewById(R.id.prev_rec1);


        final GridView prev_desp_carne_grid= (GridView) view.findViewById(R.id.prev_desp_carne_grid);
        final GridView prev_desp_grid= (GridView) view.findViewById(R.id.prev_desp_grid);


        des_pagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                des_pagar.setVisibility(View.GONE);
                des_pagar1.setVisibility(View.VISIBLE);
                res_des_desembolsar.setVisibility(View.VISIBLE);
            }
        });

        des_pagar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                des_pagar1.setVisibility(View.GONE);
                des_pagar.setVisibility(View.VISIBLE);
                res_des_desembolsar.setVisibility(View.GONE);
            }
        });


        cartao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartao.setVisibility(View.GONE);
                cartao1.setVisibility(View.VISIBLE);
                res_credito.setVisibility(View.VISIBLE);
            }
        });

        cartao1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartao1.setVisibility(View.GONE);
                cartao.setVisibility(View.VISIBLE);
                res_credito.setVisibility(View.GONE);
            }
        });




        parcial1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parcial1.setVisibility(View.GONE);
                parcial11.setVisibility(View.VISIBLE);
                total_parcial1.setVisibility(View.VISIBLE);
            }
        });

        parcial11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parcial11.setVisibility(View.GONE);
                parcial1.setVisibility(View.VISIBLE);
                total_parcial1.setVisibility(View.GONE);
            }
        });

        prev_desp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DES1.setVisibility(View.VISIBLE);
                DES2.setVisibility(View.VISIBLE);
                DES3.setVisibility(View.VISIBLE);
                DES4.setVisibility(View.VISIBLE);
                prev_desp.setVisibility(View.GONE);
                prev_desp1.setVisibility(View.VISIBLE);
            }
        });

        prev_desp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DES1.setVisibility(View.GONE);
                DES2.setVisibility(View.GONE);
                DES3.setVisibility(View.GONE);
                DES4.setVisibility(View.GONE);
                prev_desp.setVisibility(View.VISIBLE);
                prev_desp1.setVisibility(View.GONE);

                //se algum grid estiver aberto, vamos fechar
                total_desp1.setVisibility(View.GONE);
                total_desp.setVisibility(View.VISIBLE);
                prev_desp_grid.setVisibility(View.GONE);


                cartao1.setVisibility(View.GONE);
                cartao.setVisibility(View.VISIBLE);
                res_credito.setVisibility(View.GONE);

                des_pagar1.setVisibility(View.GONE);
                des_pagar.setVisibility(View.VISIBLE);
                res_des_desembolsar.setVisibility(View.GONE);



            }
        });


        total_desp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                total_desp.setVisibility(View.GONE);
                total_desp1.setVisibility(View.VISIBLE);
                new Downloader2(getActivity(),urlAddress,prev_desp_grid).execute();
                prev_desp_grid.setVisibility(View.VISIBLE);

            }
        });

        total_desp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                total_desp1.setVisibility(View.GONE);
                total_desp.setVisibility(View.VISIBLE);
                prev_desp_grid.setVisibility(View.GONE);
            }
        });

        prev_rec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prev_rec1.setVisibility(View.VISIBLE);
                prev_rec.setVisibility(View.GONE);
                salario.setVisibility(View.VISIBLE);
            }
        });

        prev_rec1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prev_rec.setVisibility(View.VISIBLE);
                prev_rec1.setVisibility(View.GONE);
                salario.setVisibility(View.GONE);
            }
        });





//endregion


        ButterKnife.bind(this, view);

        ( (MainActivity)getActivity()).updateToolbarTitle("Previsão Financeira");


        return view;
    }

}
