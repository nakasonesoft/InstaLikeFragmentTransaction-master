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
import android.support.v4.app.FragmentManager;
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
import com.f22labs.instalikefragmenttransaction.activities.AdminActivity;
import com.f22labs.instalikefragmenttransaction.activities.ListViewTeste;
import com.f22labs.instalikefragmenttransaction.activities.MainActivity;
import com.f22labs.instalikefragmenttransaction.activities.activity_login;
import com.f22labs.instalikefragmenttransaction.activities.implantacaoErotina;
import com.f22labs.instalikefragmenttransaction.adapters.ConfigRetrieve;
import com.f22labs.instalikefragmenttransaction.utils.MaskEditUtil;
import com.f22labs.instalikefragmenttransaction.utils.MoneyTextWatcher;
import com.f22labs.instalikefragmenttransaction.utils.Static;
import com.f22labs.instalikefragmenttransaction.utils.staticd;

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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.ButterKnife;

public class HomeNakasone extends BaseFragment {

    //region variaveis
    int fragCount;
    String resposta, resposta1, resposta2;
    double resultado;
    TextView getreceita, getdespesa, getresultado, getsaldo, getcartao, getdividas, settotal;
    EditText setconsorcio, setprestacao;
    Button salvar_visao;
    ImageView imgreceita_visao, imgdespesa_visao, imgsaldo_visao, imgcartao_visao, imgdividas_visao, imgtotal_visao, imgsetconsorcio, imgsetimovel;
    ProgressDialog loading;
    SwipeRefreshLayout mSwipeRefreshLayout;
//endregion
   public static final String PREFS_PRIMEIRA = "0";

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

    public void insert(){



        Date date = Calendar.getInstance().getTime();

        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String today = formatter.format(date);
        System.out.println("Today : " + today);


        String  valor_prestImovel = setprestacao.getText().toString();
        String  data_prestImovel = today;
        String id_cliente = String.valueOf(Static.getId_cliente());

        insertToDatabase(valor_prestImovel, data_prestImovel,id_cliente);

    }

    private void insertToDatabase(String valor_prestImovel, String data_prestImovel,String id_cliente){
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {
                String paramvalor_prestImovel = params[0];
                String paramdata_prestImovel = params[1];
                String paramdata_cliente = params[2];

                Date date = Calendar.getInstance().getTime();

                DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                String today = formatter.format(date);
                System.out.println("Today : " + today);

                //InputStream is = null;

                String  valor_prestImovel = setprestacao.getText().toString();
                String  data_prestImovel = today;
                String id_cliente = String.valueOf(Static.getId_cliente());

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("valor_prestImovel", valor_prestImovel));
                nameValuePairs.add(new BasicNameValuePair("data_prestImovel", data_prestImovel));
                nameValuePairs.add(new BasicNameValuePair("id_cliente", id_cliente));


                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "http://premiumcontrol.com.br/NakasoneSoftapp/cadastro_imoveis_nakasone.php");
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                    HttpResponse response = httpClient.execute(httpPost);

                    HttpEntity entity = response.getEntity();

                    //is = entity.getContent();

                } catch (ClientProtocolException e) {

                } catch (IOException e) {

                }
                return "success";
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                Toast.makeText(getActivity(), result, Toast.LENGTH_LONG).show();
                //TextView textViewResult = (TextView) findViewById(R.id.textViewResult);
                // textViewResult.setText("Inserted");
            }



        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute( valor_prestImovel, data_prestImovel,id_cliente);
    }

    public void insert1(){



        Date date = Calendar.getInstance().getTime();

        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String today = formatter.format(date);
        System.out.println("Today : " + today);


        String  valor_consorcio = setconsorcio.getText().toString();
        String  data_consorcio = today;
        String id_cliente = String.valueOf(Static.getId_cliente());
        insertToDatabase1(valor_consorcio, data_consorcio,id_cliente);

    }

    private void insertToDatabase1(String valor_consorcio, String data_consorcio,String id_cliente){
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {
                String paramvalor_consorcio = params[0];
                String paramdata_consorcio = params[1];
                String paramdata_cliente = params[2];

                Date date = Calendar.getInstance().getTime();

                DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                String today = formatter.format(date);
                System.out.println("Today : " + today);

                //InputStream is = null;

                String  valor_consorcio = setconsorcio.getText().toString();
                String  data_consorcio = today;
                String id_cliente = String.valueOf(Static.getId_cliente());

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("valor_consorcio", valor_consorcio));
                nameValuePairs.add(new BasicNameValuePair("data_consorcio", data_consorcio));
                nameValuePairs.add(new BasicNameValuePair("id_cliente", id_cliente));

                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "http://premiumcontrol.com.br/NakasoneSoftapp/cadastro_consorcio_nakasone.php");
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                    HttpResponse response = httpClient.execute(httpPost);

                    HttpEntity entity = response.getEntity();

                    //is = entity.getContent();

                } catch (ClientProtocolException e) {

                } catch (IOException e) {

                }
                return "success";
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                Toast.makeText(getActivity(), result, Toast.LENGTH_LONG).show();
                //TextView textViewResult = (TextView) findViewById(R.id.textViewResult);
                // textViewResult.setText("Inserted");
            }



        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(valor_consorcio, data_consorcio,id_cliente);
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

        //---------------------------------------------------------------------------------------------------
            if(staticd.getVirjao() == 1)
            {
                Log.d("Static Virjao",String.valueOf(staticd.getVirjao()));
                try
                {
                    //mFragmentNavigation.pushFragment(new RecyclerDesativarContas());
                    startActivity(new Intent(getActivity(), implantacaoErotina.class));
                }
                catch (Exception e)
                {

                }
                staticd.setVirjao(2);
            }


        //---------------------------------------------------------------------------------------------------

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
        imgsetconsorcio = (ImageView) view.findViewById(R.id.imgsetconsorcio);
        imgsetimovel = (ImageView) view.findViewById(R.id.imgsetimovel);

        //EditText
        setconsorcio = (EditText) view.findViewById(R.id.setconsorcio);
        setprestacao = (EditText) view.findViewById(R.id.setprestacao);

        //region Máscaras
        setconsorcio.addTextChangedListener(new MoneyTextWatcher(setconsorcio));
        setprestacao.addTextChangedListener(new MoneyTextWatcher(setprestacao));
        //endregion



        //Button
        salvar_visao = (Button) view.findViewById(R.id.salvar_visao);

        if(Static.getPrest_consorcio() != 0){setconsorcio.setText(String.valueOf(Static.getPrest_consorcio()));}
        if(Static.getPrest_imovel() != 0){setprestacao.setText(String.valueOf(Static.getPrest_imovel()));}


        imgsetconsorcio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             Static.setPrest_consorcio(Double.parseDouble(setconsorcio.getText().toString()));
                LogpesToDatabase();
                LogpesToDatabase1();



                if(Static.getPrest_consorcio() != 0){setconsorcio.setText(String.valueOf(Static.getPrest_consorcio()));}
                if(Static.getPrest_imovel() != 0){setprestacao.setText(String.valueOf(Static.getPrest_imovel()));}
                if(setprestacao.getText().toString() != "0.00" || setconsorcio.getText().toString() != "0.00")
                {
                    resultado = Double.parseDouble(getcartao.getText().toString())+ Double.parseDouble(getdividas.getText().toString()) + Double.parseDouble(setconsorcio.getText().toString()) + Double.parseDouble(setprestacao.getText().toString());
                    settotal.setText(String.valueOf(resultado));
                }

            }
        });

        imgsetimovel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Static.setPrest_imovel(Double.parseDouble(setprestacao.getText().toString()));
                LogpesToDatabase();
                LogpesToDatabase1();

                if(Static.getPrest_consorcio() != 0){setconsorcio.setText(String.valueOf(Static.getPrest_consorcio()));}
                if(Static.getPrest_imovel() != 0){setprestacao.setText(String.valueOf(Static.getPrest_imovel()));}
                if(setprestacao.getText().toString() != "0.00" || setconsorcio.getText().toString() != "0.00")
                {
                     resultado = Double.parseDouble(getcartao.getText().toString())+ Double.parseDouble(getdividas.getText().toString()) + Double.parseDouble(setconsorcio.getText().toString()) + Double.parseDouble(setprestacao.getText().toString());
                     settotal.setText(String.valueOf(resultado));
                }


            }
        });


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
                LogpesToDatabase1();
                if(Static.getPrest_consorcio() != 0){setconsorcio.setText(String.valueOf(Static.getPrest_consorcio()));}
                if(Static.getPrest_imovel() != 0){setprestacao.setText(String.valueOf(Static.getPrest_imovel()));}
                Log.d("Static 1", String.valueOf(Static.getPrest_consorcio()));
                Log.d("Static 2", String.valueOf(Static.getPrest_imovel()));

            }
        });

        ButterKnife.bind(this, view);

        Bundle args = getArguments();
        if (args != null) {
            fragCount = args.getInt(ARGS_INSTANCE);
        }

        LogpesToDatabase();
        LogpesToDatabase1();
        LogpesToDatabase2();



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









    //region SetCampos  Receitas e Despesas deste mês


    private void LogpesToDatabase(){
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {

                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "http://premiumcontrol.com.br/NakasoneSoftapp/visao_geral.php?id_cliente="+Static.getId_cliente()+"");


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

    //region SetCampos  Dívidas a vencer até o final do mês ---- Crédito/Outros


    private void LogpesToDatabase1(){
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {

                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "http://premiumcontrol.com.br/NakasoneSoftapp/select/credito_outros.php?id_cliente="+Static.getId_cliente()+"");


                    HttpResponse response = httpClient.execute(httpPost);

                    HttpEntity entity = response.getEntity();
                    BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

                    StringBuffer sb = new StringBuffer("");
                    String line="";

                    while ((line = in.readLine()) != null) {
                        sb.append(line + "");
                        break;
                    }
                    in.close();
                    resposta1 = sb.toString();
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
                try {
                    String Pablo = result.substring(0, result.indexOf("<br>"));
                    String Vittar = result.replace(Pablo, "");
                    String Gabriel = Vittar.replace(Pablo, "");
                    String Guilherme = Gabriel.replace("<br>/", "");
                    String Reginaldo = resposta1.substring(resposta1.lastIndexOf("/") + 1);
                    Log.d("FOI LEK", Pablo);
                    Log.d("FOI LEK", Pablo);
                    Log.d("Resultado", Guilherme);
                    Log.d("A", resposta1.substring(resposta1.lastIndexOf("/") + 1));
                    if(Pablo.equals("")){
                        getcartao.setText("0.00");
                    }
                    else{
                        getcartao.setText(Pablo);
                    }
                    if(Guilherme.equals("")){
                        getdividas.setText("0.00");
                    }
                    else{
                        getdividas.setText(Guilherme);
                    }

                    mSwipeRefreshLayout.setRefreshing(false);
                }
                catch (Exception e){Toast.makeText(getActivity(),"Por favor, recarregue a página para calcular seus dados", Toast.LENGTH_LONG);}

            }
        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute();

    }
    //endregion

    //region Set Saldo Caixa e Bancos
    private void LogpesToDatabase2(){
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {

                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "http://premiumcontrol.com.br/NakasoneSoftapp/select/saldo_caixa_bancos.php?id_cliente="+Static.getId_cliente()+"");


                    HttpResponse response = httpClient.execute(httpPost);

                    HttpEntity entity = response.getEntity();
                    BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

                    StringBuffer sb = new StringBuffer("");
                    String line="";

                    while ((line = in.readLine()) != null) {
                        sb.append(line + "");
                        break;
                    }
                    in.close();
                    resposta2 = sb.toString();
                    Log.d("AQUI PORRA DE SALDO", resposta2);
                    return sb.toString();

                    //is = entity.getContent();


                } catch (ClientProtocolException e) {

                } catch (IOException e) {

                }
                return resposta2;


            }

            @Override
            protected void onPostExecute(String result)
            {
                super.onPostExecute(result);
                try
                {
                    resultado = Double.parseDouble(getcartao.getText().toString())+ Double.parseDouble(getdividas.getText().toString());
                    settotal.setText(String.valueOf(resultado));
                       switch (Integer.parseInt(result))
                        {
                             case 0:
                             getsaldo.setText("R$  0,00");

                                 Log.d("OQ TEM AQUI?: ", String.valueOf(resultado));
                             break;
                             default:
                                 getsaldo.setText("R$  " + result);
                                 Log.d("AQUI PORRA", resposta2);
                                 //resultado = Double.parseDouble(getcartao.getText().toString())+ Double.parseDouble(getdividas.getText().toString()) + Double.parseDouble(setconsorcio.getText().toString()) + Double.parseDouble(setprestacao.getText().toString());
                                 break;

                        }

                     settotal.setText(String.valueOf(resultado));

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
