package com.f22labs.instalikefragmenttransaction.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.f22labs.instalikefragmenttransaction.R;
import com.f22labs.instalikefragmenttransaction.activities.MainActivity;
import com.f22labs.instalikefragmenttransaction.activities.activity_login;
import com.f22labs.instalikefragmenttransaction.adapters.ConfigRetrieve;
import com.f22labs.instalikefragmenttransaction.utils.Static;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;


public class HomeNakasone extends BaseFragment {
    int fragCount;
    String resposta;
    TextView getreceita, getdespesa, getresultado, getsaldo, getcartao, getdividas, settotal;
    EditText setconsorcio, setprestacao;
    Button salvar_visao;
    ImageView imgreceita_visao, imgdespesa_visao, imgsaldo_visao, imgcartao_visao, imgdividas_visao, imgtotal_visao;
    ProgressDialog loading;
    SwipeRefreshLayout mSwipeRefreshLayout;

    public static HomeFragment newInstance(int instance) {
        Bundle args = new Bundle();
        args.putInt(ARGS_INSTANCE, instance);
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }


    public HomeNakasone() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_home_nakasone, container, false);


        mSwipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.swifeRefreshaltexcconsorcio);

        //TextView
        getreceita = (TextView) view.findViewById(R.id.getreceita);
        getdespesa = (TextView) view.findViewById(R.id.getdespesa);
        getresultado = (TextView) view.findViewById(R.id.getresultado);
        getsaldo = (TextView) view.findViewById(R.id.getsaldo);
        getcartao = (TextView) view.findViewById(R.id.getcartao);
        getdividas = (TextView) view.findViewById(R.id.getdividas);
        settotal = (TextView) view.findViewById(R.id.settotal);



        //ImgView
        imgreceita_visao = (ImageView) view.findViewById(R.id.imgreceita_visao);
        imgdespesa_visao = (ImageView) view.findViewById(R.id.imgdespesa_visao);
        imgsaldo_visao = (ImageView) view.findViewById(R.id.imgsaldo_visao);
        imgcartao_visao = (ImageView) view.findViewById(R.id.imgcartao_visao);
        imgdividas_visao = (ImageView) view.findViewById(R.id.imgdividas_visao);
        imgtotal_visao = (ImageView) view.findViewById(R.id.imgtotal_visao);
        //EditText
        setconsorcio = (EditText) view.findViewById(R.id.setconsorcio);
        setprestacao = (EditText) view.findViewById(R.id.setprestacao);
        //Button
        salvar_visao = (Button) view.findViewById(R.id.salvar_visao);

        imgreceita_visao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragmentNavigation.pushFragment(new RecyclerAltExcReceita());
            }
        });

        imgdespesa_visao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragmentNavigation.pushFragment(new RecyclerAltExcDespesa());
            }
        });


        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                LogpesToDatabase();

            }
        });

        ButterKnife.bind(this, view);

        Bundle args = getArguments();
        if (args != null) {
            fragCount = args.getInt(ARGS_INSTANCE);
        }

        LogpesToDatabase();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);





        ( (MainActivity)getActivity()).updateToolbarTitle("Visão Geral");

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }










    //region Login Normal


    private void LogpesToDatabase(){
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {

                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "http://premiumcontrol.com.br/NakasoneSoftapp/visao_geral.php");


                    HttpResponse response = httpClient.execute(httpPost);

                    HttpEntity entity = response.getEntity();
                    BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

                    StringBuffer sb = new StringBuffer("");
                    String line="";

                    while ((line = in.readLine()) != null) {
                        sb.append(line + "\n");
                        break;
                    }
                    in.close();
                    resposta = sb.toString();
                    Log.d("TAG", resposta);
                    return sb.toString();

                    //is = entity.getContent();


                } catch (ClientProtocolException e) {

                } catch (IOException e) {

                }
                return resposta;


            }

            @Override
            protected void onPostExecute(String result)
            {
                super.onPostExecute(result);
                try {
                    String ronaldo = result.substring(0, result.indexOf("<br>"));
                    String felipe = result.replace(ronaldo, "");
                    String alexandre = felipe.replace("<br>", "");
                    String Pablo = alexandre.substring(0, alexandre.indexOf("/"));
                    String Vittar = result.replace(ronaldo, "");
                    String Gabriel = Vittar.replace(Pablo, "");
                    String Guilherme = Gabriel.replace("<br>/", "");
                    String Reginaldo = resposta.substring(resposta.lastIndexOf("/") + 1);
                    Log.d("FOI LEK", ronaldo);
                    Log.d("FOI LEK", Pablo);
                    Log.d("Resultado", Guilherme);
                    Log.d("A", resposta.substring(resposta.lastIndexOf("/") + 1));
                    getreceita.setText("R$  " + Pablo);
                    getdespesa.setText("R$  " + ronaldo);
                    getresultado.setText("R$  " + Reginaldo);
                    mSwipeRefreshLayout.setRefreshing(false);
                }
                catch (Exception e){Toast.makeText(getActivity(),"Por favor, recarregue a página para calcular seus dados", Toast.LENGTH_LONG);}

            }


        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute();

    }
    //endregion


}
