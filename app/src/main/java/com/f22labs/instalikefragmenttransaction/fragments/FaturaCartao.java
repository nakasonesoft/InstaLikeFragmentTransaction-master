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


public class FaturaCartao extends BaseFragment
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
        View view = inflater.inflate(R.layout.fragment_fatura, container, false);

        ListView listaDeCursos = (ListView) view.findViewById(R.id.fatura);

        perg = new ArrayList<String>();

        resp = new ArrayList<String>();

        Perguntas();

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
                        Static.setMes(position);
                        mFragmentNavigation.pushFragment(new PerguntaFrequenteEspecifica());
                        Log.d("Posição é :",String.valueOf(position));
                        break;
                    case 1:
                        Static.setMes(position);
                        mFragmentNavigation.pushFragment(new PerguntaFrequenteEspecifica());
                        break;
                    case 2:
                        Static.setMes(position);
                        mFragmentNavigation.pushFragment(new PerguntaFrequenteEspecifica());
                        break;
                    case 3:
                        Static.setMes(position);
                        mFragmentNavigation.pushFragment(new PerguntaFrequenteEspecifica());
                        break;

                    case 4:
                        Static.setMes(position);
                        mFragmentNavigation.pushFragment(new PerguntaFrequenteEspecifica());
                        break;

                    case 5:
                        Static.setMes(position);
                        mFragmentNavigation.pushFragment(new PerguntaFrequenteEspecifica());
                        break;

                    case 6:
                        Static.setMes(position);
                        mFragmentNavigation.pushFragment(new PerguntaFrequenteEspecifica());
                        break;

                    case 7:
                        Static.setMes(position);
                        mFragmentNavigation.pushFragment(new PerguntaFrequenteEspecifica());
                        break;

                    case 8:
                        Static.setMes(position);
                        mFragmentNavigation.pushFragment(new PerguntaFrequenteEspecifica());
                        break;

                    case 9:
                        Static.setMes(position);
                        mFragmentNavigation.pushFragment(new PerguntaFrequenteEspecifica());
                        break;

                    case 10:
                        Static.setMes(position);
                        mFragmentNavigation.pushFragment(new PerguntaFrequenteEspecifica());
                        break;

                    case 11:
                        Static.setMes(position);
                        mFragmentNavigation.pushFragment(new PerguntaFrequenteEspecifica());
                        break;

                    case 12:
                        Static.setMes(position);
                        mFragmentNavigation.pushFragment(new PerguntaFrequenteEspecifica());
                        break;
                }
            }
        });

        //region Outros
        ButterKnife.bind(this, view);

        ( (MainActivity)getActivity()).updateToolbarTitle("Fatura");
        //endregion

        return view;
    }
    public void Perguntas()
    {
        perg.add(getString(R.string.mes1));
        perg.add(getString(R.string.mes2));
        perg.add(getString(R.string.mes3));
        perg.add(getString(R.string.mes4));
        perg.add(getString(R.string.mes5));
        perg.add(getString(R.string.mes6));
        perg.add(getString(R.string.mes7));
        perg.add(getString(R.string.mes8));
        perg.add(getString(R.string.mes9));
        perg.add(getString(R.string.mes10));
        perg.add(getString(R.string.mes11));
        perg.add(getString(R.string.mes12));
    }
 }
