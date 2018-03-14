package com.f22labs.instalikefragmenttransaction.fragments;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.f22labs.instalikefragmenttransaction.R;
import com.f22labs.instalikefragmenttransaction.activities.MainActivity;
import com.f22labs.instalikefragmenttransaction.activities.activity_senha;
import com.f22labs.instalikefragmenttransaction.adapters.GetDataAdapter;
import com.f22labs.instalikefragmenttransaction.adapters.RecyclerViewAdapterImovel;
import com.f22labs.instalikefragmenttransaction.interfaces.MainActivityP;
import com.f22labs.instalikefragmenttransaction.utils.Static;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import butterknife.ButterKnife;


public class RelatoriosNakasone extends BaseFragment{

    ImageView imgfichaindividual,imgdiario,imgdividasrelatorio,imgmensal,imgdesempenho,imgprevisao,imgacompanhamento;



    JsonArrayRequest jsonArrayRequest ;

    RequestQueue requestQueue ;

    String GET_JSON_DATA_HTTP_URL = "http://premiumcontrol.com.br/NakasoneSoftapp/select_status_pagamento.php?id_cliente="+ Static.getId_cliente()+"";
    String JSON_status = "status",resposta1;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_relatorios, container, false);
        LogpesToDatabase1();
        //region findviews
        imgfichaindividual = (ImageView) view.findViewById(R.id.imgfichaindividual);
        imgdiario = (ImageView) view.findViewById(R.id.imgdiario);
        imgdividasrelatorio = (ImageView) view.findViewById(R.id.imgdividasrelatorio);
        imgmensal = (ImageView) view.findViewById(R.id.imgmensal);
        imgdesempenho = (ImageView) view.findViewById(R.id.imgdesempenho);
        imgprevisao = (ImageView) view.findViewById(R.id.imgprevisao);
        imgacompanhamento = (ImageView) view.findViewById(R.id.imgacompanhamento);
//endregion

        //region botões

        imgacompanhamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // mFragmentNavigation.pushFragment(new PrevisaoFinanceiraGrid());

                if(resposta1.equals("1")){
                    mFragmentNavigation.pushFragment(new PrevisaoFinanceiraGrid());
                }
                else{
                    Intent intent = new Intent(getActivity(), MainActivityP.class);
                    startActivity(intent);
                }
            }
        });


        imgprevisao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // mFragmentNavigation.pushFragment(new PrevisaoFinanceiraGrid());

                if(resposta1.equals("1")){
                    mFragmentNavigation.pushFragment(new PrevisaoFinanceiraGrid());
                }
                else{
                    Intent intent = new Intent(getActivity(), MainActivityP.class);
                    startActivity(intent);
                }
            }
        });



        imgdesempenho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mFragmentNavigation.pushFragment(new FragmentGrid2());

                if(resposta1.equals("1")){
                    mFragmentNavigation.pushFragment(new FragmentGrid2());
                }
                else{
                    Intent intent = new Intent(getActivity(), MainActivityP.class);
                    startActivity(intent);
                }
            }
        });


        imgmensal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragmentNavigation.pushFragment(new Mensal_anual());
            }
        });

        imgdiario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragmentNavigation.pushFragment(new RecyclerDiario2());
            }
        });

        imgfichaindividual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragmentNavigation.pushFragment(new FichaIndividual());
            }
        });

        imgdividasrelatorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragmentNavigation.pushFragment(new RecyclerDividasCartaoCredito());
            }
        });
//endregion

        ButterKnife.bind(this, view);

        ( (MainActivity)getActivity()).updateToolbarTitle("Relatórios");

        return view;
    }


    private void LogpesToDatabase1(){
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {

                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "http://premiumcontrol.com.br/NakasoneSoftapp/select_status_pagamento.php?id_cliente="+Static.getId_cliente()+"");


                    HttpResponse response = httpClient.execute(httpPost);

                    HttpEntity entity = response.getEntity();
                    BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

                    StringBuffer sb = new StringBuffer("");
                    String line="";

                    while ((line = in.readLine()) != null) {
                        sb.append(line);
                        break;
                    }
                    in.close();
                    resposta1 = sb.toString().replace(" ","");
                    Log.d("TAG", resposta1);
                    return sb.toString();

                    //is = entity.getContent();


                } catch (ClientProtocolException e) {

                } catch (IOException e) {

                }
                return resposta1;


            }

            @Override
            protected void onPostExecute(String result)
            {
                super.onPostExecute(result);
                try
                {
                     resposta1 = result.replace(" ","");
                   // mSwipeRefreshLayout.setRefreshing(false);
                }
                catch (Exception e)
                {Toast.makeText(getActivity(),"Por favor, recarregue a página para calcular seus dados", Toast.LENGTH_LONG);}

            }
        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();

        sendPostReqAsyncTask.execute();

    }




}
