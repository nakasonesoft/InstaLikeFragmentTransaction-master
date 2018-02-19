package com.f22labs.instalikefragmenttransaction.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.f22labs.instalikefragmenttransaction.R;
import com.f22labs.instalikefragmenttransaction.activities.MainActivity;
import com.f22labs.instalikefragmenttransaction.utils.Static;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;


public class PerguntaFrequenteEspecifica extends BaseFragment{


    TextView textViewresp,textViewperg;

    ViewFlipper simpleViewFlipper;

    Button btnNext;

    List<String> perg;
    List<String> resp;

    int i = 1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_perguntas_especifica, container, false);

        simpleViewFlipper = (ViewFlipper) view.findViewById(R.id.viewFlipper);

        textViewresp = (TextView) view.findViewById(R.id.textViewresp);

        textViewperg = (TextView) view.findViewById(R.id.textViewperg);

        perg = new ArrayList<String>();

        resp = new ArrayList<String>();

        Perguntas();

        Resposta();

        textViewperg.setText(perg.get(Static.getId_perguntasfrequentes()));

        textViewresp.setText(resp.get(Static.getId_perguntasfrequentes()));





        //region Outros
        ButterKnife.bind(this, view);

        ( (MainActivity)getActivity()).updateToolbarTitle("Pergunta Específica");
        //endregion

        return view;
    }

    public void Perguntas()
    {
        perg.add(getString(R.string.perg1));
        perg.add(getString(R.string.perg2));
        perg.add(getString(R.string.perg3));
        perg.add(getString(R.string.perg4));
        perg.add(getString(R.string.perg5));
        perg.add(getString(R.string.perg6));
        perg.add(getString(R.string.perg7));
        perg.add(getString(R.string.perg8));
        perg.add(getString(R.string.perg9));
        perg.add(getString(R.string.perg10));
        perg.add(getString(R.string.perg11));
        perg.add(getString(R.string.perg12));
        perg.add(getString(R.string.perg13));
        perg.add(getString(R.string.perg14));
        perg.add(getString(R.string.perg15));
        perg.add(getString(R.string.perg16));
        perg.add(getString(R.string.perg17));
        perg.add(getString(R.string.perg18));
        perg.add(getString(R.string.perg19));
        perg.add(getString(R.string.perg20));
        perg.add(getString(R.string.perg21));
        perg.add(getString(R.string.perg22));
        perg.add(getString(R.string.perg23));
        perg.add(getString(R.string.perg24));
        perg.add(getString(R.string.perg25));
        perg.add(getString(R.string.perg26));
        perg.add(getString(R.string.perg27));
        perg.add(getString(R.string.perg28));
        perg.add(getString(R.string.perg29));
        perg.add(getString(R.string.perg30));
        perg.add(getString(R.string.perg31));
        perg.add(getString(R.string.perg32));
        perg.add(getString(R.string.perg33));
        perg.add(getString(R.string.perg34));
        perg.add(getString(R.string.perg35));
        perg.add(getString(R.string.perg36));
        perg.add(getString(R.string.perg37));
        perg.add(getString(R.string.perg38));
        perg.add(getString(R.string.perg39));
        perg.add(getString(R.string.perg40));

    }

    public void Resposta()
    {
        resp.add(getString(R.string.resp1));
        resp.add(getString(R.string.resp2));
        resp.add(getString(R.string.resp3));
        resp.add(getString(R.string.resp4));
        resp.add(getString(R.string.resp5));
        resp.add(getString(R.string.resp6));
        resp.add(getString(R.string.resp7));
        resp.add(getString(R.string.resp8));
        resp.add(getString(R.string.resp9));
        resp.add(getString(R.string.resp10));
        resp.add(getString(R.string.resp11));
        resp.add(getString(R.string.resp12));
        resp.add(getString(R.string.resp13));
        resp.add(getString(R.string.resp14));
        resp.add(getString(R.string.resp15));
        resp.add(getString(R.string.resp16));
        resp.add(getString(R.string.resp17));
        resp.add(getString(R.string.resp18));
        resp.add(getString(R.string.resp19));
        resp.add(getString(R.string.resp20));
        resp.add(getString(R.string.resp21));
        resp.add(getString(R.string.resp22));
        resp.add(getString(R.string.resp23));
        resp.add(getString(R.string.resp24));
        resp.add(getString(R.string.resp25));
        resp.add(getString(R.string.resp26));
        resp.add(getString(R.string.resp27));
        resp.add(getString(R.string.resp28));
        resp.add(getString(R.string.resp29));
        resp.add(getString(R.string.resp30));
        resp.add(getString(R.string.resp31));
        resp.add(getString(R.string.resp32));
        resp.add(getString(R.string.resp33));
        resp.add(getString(R.string.resp34));
        resp.add(getString(R.string.resp35));
        resp.add(getString(R.string.resp36));
        resp.add(getString(R.string.resp37));
        resp.add(getString(R.string.resp38));
        resp.add(getString(R.string.resp39));
        resp.add(getString(R.string.resp40));
    }


}
