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


public class Diario extends BaseFragment
{

    List<String> perg   ;
    ArrayAdapter<String> adaptador;

    int i = 1   ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_diario, container, false);

        ListView listaDeCursos = (ListView) view.findViewById(R.id.diario);

        perg = new ArrayList<String>();

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
                        Static.setDiario(position);
                        mFragmentNavigation.pushFragment(new RecyclerDiario());
                        break;
                    case 1:
                        Static.setDiario(position);
                        mFragmentNavigation.pushFragment(new RecyclerDiario());
                        break;
                    case 2:
                        Static.setDiario(position);
                        mFragmentNavigation.pushFragment(new RecyclerDiario());
                        break;
                    case 3:
                        Static.setDiario(position);
                        mFragmentNavigation.pushFragment(new RecyclerDiario());
                        break;

                    case 4:
                        Static.setDiario(position);
                        mFragmentNavigation.pushFragment(new RecyclerDiario());
                        break;

                    case 5:
                        Static.setDiario(position);
                        mFragmentNavigation.pushFragment(new RecyclerDiario());
                        break;

                    case 6:
                        Static.setDiario(position);
                        mFragmentNavigation.pushFragment(new RecyclerDiario());
                        break;

                    case 7:
                        Static.setDiario(position);
                        mFragmentNavigation.pushFragment(new RecyclerDiario());
                        break;

                    case 8:
                        Static.setDiario(position);
                        mFragmentNavigation.pushFragment(new RecyclerDiario());
                        break;

                    case 9:
                        Static.setDiario(position);
                        mFragmentNavigation.pushFragment(new RecyclerDiario());
                        break;


                }
            }
        });

        //region Outros
        ButterKnife.bind(this, view);

        ( (MainActivity)getActivity()).updateToolbarTitle("Diário");
        //endregion

        return view;
    }

    public void Perguntas()
    {
        perg.add("Despesa");//0
        perg.add("Receita");//1
        perg.add("Transferência");//2
        perg.add("Saque");//3
        perg.add("Depósito");//4
        perg.add("Fatura");//5
        perg.add("Carnê");//6
        perg.add("Imóveis");//7
        perg.add("Consórcio");//8
        perg.add("Outros");//9

    }
 }
