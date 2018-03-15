package com.f22labs.instalikefragmenttransaction.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.f22labs.instalikefragmenttransaction.R;
import com.f22labs.instalikefragmenttransaction.activities.MainActivity;
import com.f22labs.instalikefragmenttransaction.activities.activity_cadastro;
import com.f22labs.instalikefragmenttransaction.activities.activity_login;
import com.f22labs.instalikefragmenttransaction.activities.activity_senha;
import com.f22labs.instalikefragmenttransaction.interfaces.MainActivityP;
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.f22labs.instalikefragmenttransaction.activities.activity_login.PREFS_NAME;


public class opcoes extends BaseFragment{

    TextView licencas, politica, uso, quemsomos, objetivos, cartilha, tabprecos;
    Button sair, apagar_conta;
    String resposta;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public void insert(){

        String  id_cliente = String.valueOf(Static.getId_cliente());
        insertToDatabase(id_cliente);

    }


    private void insertToDatabase(String id_cliente){
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {
                String paramid_cliente = params[0];

                //InputStream is = null;

                String  id_cliente = String.valueOf(Static.getId_cliente());

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("id_cliente", id_cliente));


                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "http://premiumcontrol.com.br/NakasoneSoftapp/delete/delete_cliente.php");
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

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
                    resposta = sb.toString();

                    Log.d("TAG", resposta);
                    return sb.toString();

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
                if(Integer.parseInt(result) == 1){
                    SharedPreferences settings = getActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putString("0", "");

                    //Confirma a gravação dos dados
                    editor.commit();
                    Static.setId_cliente(0);
                    startActivity(new Intent(getActivity(), activity_login.class));
                    Toast.makeText(getActivity(), "Conta apagada com sucesso", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getActivity(), "Falha ao apagar conta", Toast.LENGTH_LONG).show();
                }




            }



        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(id_cliente);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view = inflater.inflate(R.layout.fragment_opcoes, container, false);


        licencas = (TextView)view.findViewById(R.id.licencas);
        tabprecos = (TextView)view.findViewById(R.id.tabprecos);
        cartilha = (TextView)view.findViewById(R.id.cartilha);
        politica = (TextView)view.findViewById(R.id.politica);
        uso = (TextView)view.findViewById(R.id.uso);
        objetivos = (TextView)view.findViewById(R.id.objetivos);
        sair = (Button)view.findViewById(R.id.sair);
        apagar_conta = (Button)view.findViewById(R.id.apagar_conta);
        quemsomos = (TextView)view.findViewById(R.id.quemsomos);
        licencas = (TextView)view.findViewById(R.id.licencas);

        //region Licenças

        licencas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragmentNavigation.pushFragment(new admin());
            }
        });


        tabprecos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivityP.class);
                startActivity(intent);
            }
        });


        cartilha.setText(
                Html.fromHtml(
                        "<a href=\"http://www.procon.al.gov.br/legislacao/cartilhadoconsumidor.pdf\">Cartilha do consumidor</a> "));
        cartilha.setMovementMethod(LinkMovementMethod.getInstance());

        //endregion

        //region SAIR
        sair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences settings = getActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = settings.edit();
                editor.putString("0", "");

                //Confirma a gravação dos dados
                editor.commit();
                Static.setId_cliente(0);
                startActivity(new Intent(getActivity(), activity_login.class));
                Toast.makeText(getActivity(), "Volte em breve!", Toast.LENGTH_LONG).show();
            }
        });
//endregion

        //region quem somos

        quemsomos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragmentNavigation.pushFragment(new quemsomos());
            }
        });

        //endregion

        //region politica

        politica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragmentNavigation.pushFragment(new politica());

            }
        });

        //endregion

        //region termos de uso

        uso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragmentNavigation.pushFragment(new termos());
            }
        });

        //endregion

        //region objetivos

        objetivos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragmentNavigation.pushFragment(new objetivos());
            }
        });

        //endregion

        //region Apagar Conta
        apagar_conta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert();
            }
        });

        //endregion

        ButterKnife.bind(this, view);

        ( (MainActivity)getActivity()).updateToolbarTitle("Opções");


        return view;
    }









}
