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


public class FichaIndividualMeses extends BaseFragment
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
        View view = inflater.inflate(R.layout.fragment_ficha_individual_meses, container, false);

        ListView listaDeCursos = (ListView) view.findViewById(R.id.ficha_meses);

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
                        Static.setMes_ficha(position);
                        mFragmentNavigation.pushFragment(new RecyclerFaturaDespesaJan());
                        ( (MainActivity)getActivity()).updateToolbarTitle("Janeiro");
                        break;
                    case 1:
                        Static.setMes_ficha(position);
                        mFragmentNavigation.pushFragment(new RecyclerFaturaDespesaJan());
                        ( (MainActivity)getActivity()).updateToolbarTitle("Fevereiro");
                        break;
                    case 2:
                        Static.setMes_ficha(position);
                        mFragmentNavigation.pushFragment(new RecyclerFaturaDespesaJan());
                        ( (MainActivity)getActivity()).updateToolbarTitle("Março");
                        break;
                    case 3:
                        Static.setMes_ficha(position);
                        mFragmentNavigation.pushFragment(new RecyclerFaturaDespesaJan());
                        ( (MainActivity)getActivity()).updateToolbarTitle("Abril");
                        break;

                    case 4:
                        Static.setMes_ficha(position);
                        mFragmentNavigation.pushFragment(new RecyclerFaturaDespesaJan());
                        ( (MainActivity)getActivity()).updateToolbarTitle("Maio");
                        break;

                    case 5:
                        Static.setMes_ficha(position);
                        mFragmentNavigation.pushFragment(new RecyclerFaturaDespesaJan());
                        ( (MainActivity)getActivity()).updateToolbarTitle("Junho");
                        break;

                    case 6:
                        Static.setMes_ficha(position);
                        mFragmentNavigation.pushFragment(new RecyclerFaturaDespesaJan());
                        ( (MainActivity)getActivity()).updateToolbarTitle("Julho");
                        break;

                    case 7:
                        Static.setMes_ficha(position);
                        mFragmentNavigation.pushFragment(new RecyclerFaturaDespesaJan());
                        ( (MainActivity)getActivity()).updateToolbarTitle("Agosto");
                        break;

                    case 8:
                        Static.setMes_ficha(position);
                        mFragmentNavigation.pushFragment(new RecyclerFaturaDespesaJan());
                        ( (MainActivity)getActivity()).updateToolbarTitle("Setembro");
                        break;

                    case 9:
                        Static.setMes_ficha(position);
                        mFragmentNavigation.pushFragment(new RecyclerFaturaDespesaJan());
                        ( (MainActivity)getActivity()).updateToolbarTitle("Outubro");
                        break;

                    case 10:
                        Static.setMes_ficha(position);
                        mFragmentNavigation.pushFragment(new RecyclerFaturaDespesaJan());
                        ( (MainActivity)getActivity()).updateToolbarTitle("Novembro");
                        break;

                    case 11:
                        Static.setMes_ficha(position);
                        mFragmentNavigation.pushFragment(new RecyclerFaturaDespesaJan());
                        ( (MainActivity)getActivity()).updateToolbarTitle("Dezembro");
                        break;


                }
            }
        });

        //region Outros
        ButterKnife.bind(this, view);

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
