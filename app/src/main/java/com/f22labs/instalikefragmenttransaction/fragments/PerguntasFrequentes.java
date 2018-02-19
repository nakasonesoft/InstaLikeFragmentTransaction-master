package com.f22labs.instalikefragmenttransaction.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.f22labs.instalikefragmenttransaction.R;
import com.f22labs.instalikefragmenttransaction.activities.MainActivity;
import com.f22labs.instalikefragmenttransaction.utils.Static;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;


public class PerguntasFrequentes extends BaseFragment
{

    List<String> perg   ;
    List<String> resp   ;
    ArrayAdapter<String> adaptador;

    int i = 1   ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_perguntasf, container, false);

        ListView listaDeCursos = (ListView) view.findViewById(R.id.lista);

        perg = new ArrayList<String>();

        resp = new ArrayList<String>();

        Perguntas();

        Resposta();

        adaptador = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, perg);

        listaDeCursos.setAdapter(adaptador);

        listaDeCursos.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                switch (position)
                {
                    case 0:
                        Static.setId_perguntasfrequentes(position);
                        mFragmentNavigation.pushFragment(new PerguntaFrequenteEspecifica());
                        Log.d("Posição é :",String.valueOf(position));
                        break;
                    case 1:
                        Static.setId_perguntasfrequentes(position);
                        mFragmentNavigation.pushFragment(new PerguntaFrequenteEspecifica());
                        break;
                    case 2:
                        Static.setId_perguntasfrequentes(position);
                        mFragmentNavigation.pushFragment(new PerguntaFrequenteEspecifica());
                        break;
                    case 3:
                        Static.setId_perguntasfrequentes(position);
                        mFragmentNavigation.pushFragment(new PerguntaFrequenteEspecifica());
                        break;

                    case 4:
                        Static.setId_perguntasfrequentes(position);
                        mFragmentNavigation.pushFragment(new PerguntaFrequenteEspecifica());
                        break;

                    case 5:
                        Static.setId_perguntasfrequentes(position);
                        mFragmentNavigation.pushFragment(new PerguntaFrequenteEspecifica());
                        break;

                    case 6:
                        Static.setId_perguntasfrequentes(position);
                        mFragmentNavigation.pushFragment(new PerguntaFrequenteEspecifica());
                        break;

                    case 7:
                        Static.setId_perguntasfrequentes(position);
                        mFragmentNavigation.pushFragment(new PerguntaFrequenteEspecifica());
                        break;

                    case 8:
                        Static.setId_perguntasfrequentes(position);
                        mFragmentNavigation.pushFragment(new PerguntaFrequenteEspecifica());
                        break;

                    case 9:
                        Static.setId_perguntasfrequentes(position);
                        mFragmentNavigation.pushFragment(new PerguntaFrequenteEspecifica());
                        break;

                    case 10:
                        Static.setId_perguntasfrequentes(position);
                        mFragmentNavigation.pushFragment(new PerguntaFrequenteEspecifica());
                        break;

                    case 11:
                        Static.setId_perguntasfrequentes(position);
                        mFragmentNavigation.pushFragment(new PerguntaFrequenteEspecifica());
                        break;

                    case 12:
                        Static.setId_perguntasfrequentes(position);
                        mFragmentNavigation.pushFragment(new PerguntaFrequenteEspecifica());
                        break;

                    case 13:
                        Static.setId_perguntasfrequentes(position);
                        mFragmentNavigation.pushFragment(new PerguntaFrequenteEspecifica());
                        break;
                    case 14:
                        Static.setId_perguntasfrequentes(position);
                        mFragmentNavigation.pushFragment(new PerguntaFrequenteEspecifica());
                        break;
                    case 15:
                        Static.setId_perguntasfrequentes(position);
                        mFragmentNavigation.pushFragment(new PerguntaFrequenteEspecifica());
                        break;
                    case 16:
                        Static.setId_perguntasfrequentes(position);
                        mFragmentNavigation.pushFragment(new PerguntaFrequenteEspecifica());
                        break;
                    case 17:
                        Static.setId_perguntasfrequentes(position);
                        mFragmentNavigation.pushFragment(new PerguntaFrequenteEspecifica());
                        break;

                    case 18:
                        Static.setId_perguntasfrequentes(position);
                        mFragmentNavigation.pushFragment(new PerguntaFrequenteEspecifica());
                        break;

                    case 19:
                        Static.setId_perguntasfrequentes(position);
                        mFragmentNavigation.pushFragment(new PerguntaFrequenteEspecifica());
                        break;

                    case 20:
                        Static.setId_perguntasfrequentes(position);
                        mFragmentNavigation.pushFragment(new PerguntaFrequenteEspecifica());
                        break;

                    case 21:
                        Static.setId_perguntasfrequentes(position);
                        mFragmentNavigation.pushFragment(new PerguntaFrequenteEspecifica());
                        break;

                    case 22:
                        Static.setId_perguntasfrequentes(position);
                        mFragmentNavigation.pushFragment(new PerguntaFrequenteEspecifica());
                        break;

                    case 23:
                        Static.setId_perguntasfrequentes(position);
                        mFragmentNavigation.pushFragment(new PerguntaFrequenteEspecifica());
                        break;

                    case 24:
                        Static.setId_perguntasfrequentes(position);
                        mFragmentNavigation.pushFragment(new PerguntaFrequenteEspecifica());
                        break;

                    case 25:
                        Static.setId_perguntasfrequentes(position);
                        mFragmentNavigation.pushFragment(new PerguntaFrequenteEspecifica());
                        break;

                    case 26:
                        Static.setId_perguntasfrequentes(position);
                        mFragmentNavigation.pushFragment(new PerguntaFrequenteEspecifica());
                        break;

                    case 27:
                        Static.setId_perguntasfrequentes(position);
                        mFragmentNavigation.pushFragment(new PerguntaFrequenteEspecifica());
                        break;
                    case 28:
                        Static.setId_perguntasfrequentes(position);
                        mFragmentNavigation.pushFragment(new PerguntaFrequenteEspecifica());
                        break;

                    case 29:
                        Static.setId_perguntasfrequentes(position);
                        mFragmentNavigation.pushFragment(new PerguntaFrequenteEspecifica());
                        break;

                    case 30:
                        Static.setId_perguntasfrequentes(position);
                        mFragmentNavigation.pushFragment(new PerguntaFrequenteEspecifica());
                        break;
                    case 31:
                        Static.setId_perguntasfrequentes(position);
                        mFragmentNavigation.pushFragment(new PerguntaFrequenteEspecifica());
                        break;

                    case 32:
                        Static.setId_perguntasfrequentes(position);
                        mFragmentNavigation.pushFragment(new PerguntaFrequenteEspecifica());
                        break;

                    case 33:
                        Static.setId_perguntasfrequentes(position);
                        mFragmentNavigation.pushFragment(new PerguntaFrequenteEspecifica());
                        break;

                    case 34:
                        Static.setId_perguntasfrequentes(position);
                        mFragmentNavigation.pushFragment(new PerguntaFrequenteEspecifica());
                        break;

                    case 35:
                        Static.setId_perguntasfrequentes(position);
                        mFragmentNavigation.pushFragment(new PerguntaFrequenteEspecifica());
                        break;

                    case 36:
                        Static.setId_perguntasfrequentes(position);
                        mFragmentNavigation.pushFragment(new PerguntaFrequenteEspecifica());
                        break;

                    case 37:
                        Static.setId_perguntasfrequentes(position);
                        mFragmentNavigation.pushFragment(new PerguntaFrequenteEspecifica());
                        break;

                    case 38:
                        Static.setId_perguntasfrequentes(position);
                        mFragmentNavigation.pushFragment(new PerguntaFrequenteEspecifica());
                        break;
                    case 39:
                        Static.setId_perguntasfrequentes(position);
                        mFragmentNavigation.pushFragment(new PerguntaFrequenteEspecifica());
                        break;

                    case 40:
                        Static.setId_perguntasfrequentes(position);
                        mFragmentNavigation.pushFragment(new PerguntaFrequenteEspecifica());
                        break;


                }
            }
        });

        //region Outros
        ButterKnife.bind(this, view);

        ( (MainActivity)getActivity()).updateToolbarTitle("Perguntas Frequentes");
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
