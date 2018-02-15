package com.f22labs.instalikefragmenttransaction.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.f22labs.instalikefragmenttransaction.R;
import com.f22labs.instalikefragmenttransaction.activities.MainActivity;

import butterknife.ButterKnife;


public class MinidicNakasone extends BaseFragment{

    ImageView imgdicdespesa,imgdicreceita;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_minidicionario, container, false);
        //------------------------------------------
        imgdicreceita = (ImageView) view.findViewById(R.id.imgdicreceita);
        imgdicdespesa = (ImageView) view.findViewById(R.id.imgdicdespesa);
        //----------------------
        imgdicdespesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragmentNavigation.pushFragment(new RecyclerMinidicionario());
            }
        });

        imgdicreceita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragmentNavigation.pushFragment(new RecyclerMinidicionarioReceita());
            }
        });

        ButterKnife.bind(this, view);

        ( (MainActivity)getActivity()).updateToolbarTitle("Minidicionário");


        return view;
    }


}
