package com.f22labs.instalikefragmenttransaction.activities;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.f22labs.instalikefragmenttransaction.R;
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
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
public class implantacaoErotina extends AppCompatActivity {

    TextView ativar_desativar_contas, cadastrar, cadastrar1, cadastrar2, cadastrar3, cadastrar_passo3;
    Button rotina;
    String nome_conta2, id_grupo_conta2;
    TextView id_grupo_conta;

    private Activity context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_implantacao_rotina);

        ativar_desativar_contas = (TextView) findViewById(R.id.ativar_desativar_contas);
        cadastrar = (TextView) findViewById(R.id.cadastrar);
        cadastrar1 = (TextView) findViewById(R.id.cadastrar1);
        cadastrar2 = (TextView) findViewById(R.id.cadastrar2);
        cadastrar3 = (TextView) findViewById(R.id.cadastrar3);
        cadastrar_passo3 = (TextView) findViewById(R.id.cadastrar_passo3);

        rotina = (Button) findViewById(R.id.rotina);



        rotina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), rotina.class));

            }
        });


        ativar_desativar_contas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ListViewTeste.class));

            }
        });

        //region Caixa e bancos
        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Puxando o xml da outra tela.-----------------------------------------------------
                final Dialog dialog = new Dialog(implantacaoErotina.this);
                dialog.setContentView(R.layout.novaconta);
                dialog.setTitle("This");

                // FindViews seguidos de implementações em cada!------------------------------------
                Button dialogButton = (Button) dialog.findViewById(R.id.cadastrar_nova_conta);
                final EditText nome_conta = (EditText) dialog.findViewById(R.id.nome_conta);
                id_grupo_conta = (TextView) dialog.findViewById(R.id.id_grupo_conta);
                final TextView grupo_conta = (TextView) dialog.findViewById(R.id.grupo_conta);

                //SetText --------------------------------------------------------------------------
                grupo_conta.setText("Caixa e Bancos");
                id_grupo_conta.setText("1");



                // Clique do OK do botão para fechar o Dialog/Segundo Layout.-----------------------
                dialogButton.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        nome_conta2 = nome_conta.getText().toString();
                        id_grupo_conta2= id_grupo_conta.getText().toString();
                        insert();
                        dialog.dismiss();
                    }
                });

                dialog.show();

            }
        });
//endregion

        //region Grupo investimentos, outros
        cadastrar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Puxando o xml da outra tela.-----------------------------------------------------
                final Dialog dialog = new Dialog(implantacaoErotina.this);
                dialog.setContentView(R.layout.novaconta);
                dialog.setTitle("This");

                // FindViews seguidos de implementações em cada!------------------------------------
                Button dialogButton = (Button) dialog.findViewById(R.id.cadastrar_nova_conta);
                final EditText nome_conta = (EditText) dialog.findViewById(R.id.nome_conta);
                final TextView id_grupo_conta = (TextView) dialog.findViewById(R.id.id_grupo_conta);
                final TextView grupo_conta = (TextView) dialog.findViewById(R.id.grupo_conta);

                //SetText --------------------------------------------------------------------------
                grupo_conta.setText("Grupo investimentos, outros");
                id_grupo_conta.setText("2");


                // Clique do OK do botão para fechar o Dialog/Segundo Layout.-----------------------
                dialogButton.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        nome_conta2 = nome_conta.getText().toString();
                        id_grupo_conta2= id_grupo_conta.getText().toString();
                        insert();
                        dialog.dismiss();
                    }
                });

                dialog.show();

            }
        });
//endregion

        //region Cartão de crédito
        cadastrar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Puxando o xml da outra tela.-----------------------------------------------------
                final Dialog dialog = new Dialog(implantacaoErotina.this);
                dialog.setContentView(R.layout.novaconta);
                dialog.setTitle("This");

                // FindViews seguidos de implementações em cada!------------------------------------
                Button dialogButton = (Button) dialog.findViewById(R.id.cadastrar_nova_conta);
                final EditText nome_conta = (EditText) dialog.findViewById(R.id.nome_conta);
                final TextView id_grupo_conta = (TextView) dialog.findViewById(R.id.id_grupo_conta);
                final TextView grupo_conta = (TextView) dialog.findViewById(R.id.grupo_conta);

                //SetText --------------------------------------------------------------------------
                grupo_conta.setText("Cartão de crédito");
                id_grupo_conta.setText("3");

                // Clique do OK do botão para fechar o Dialog/Segundo Layout.-----------------------
                dialogButton.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        nome_conta2 = nome_conta.getText().toString();
                        id_grupo_conta2= id_grupo_conta.getText().toString();

                        insert();
                        dialog.dismiss();
                    }
                });

                dialog.show();

            }
        });
        //endregion

        //region Outras Dívidas
        cadastrar3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                // Puxando o xml da outra tela.-----------------------------------------------------
                final Dialog dialog = new Dialog(implantacaoErotina.this);
                dialog.setContentView(R.layout.novaconta);
                dialog.setTitle("This");

                // FindViews seguidos de implementações em cada!------------------------------------
                Button dialogButton = (Button) dialog.findViewById(R.id.cadastrar_nova_conta);
                final EditText nome_conta = (EditText) dialog.findViewById(R.id.nome_conta);
                id_grupo_conta = (TextView) dialog.findViewById(R.id.id_grupo_conta);
                final TextView grupo_conta = (TextView) dialog.findViewById(R.id.grupo_conta);

                //SetText --------------------------------------------------------------------------
                grupo_conta.setText("Outras Dívidas");
                id_grupo_conta.setText("4");


                // Clique do OK do botão para fechar o Dialog/Segundo Layout.-----------------------
                dialogButton.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        nome_conta2 = nome_conta.getText().toString();
                        id_grupo_conta2= id_grupo_conta.getText().toString();

                        insert();
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });
//endregion

        cadastrar_passo3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ListViewTeste2.class));
            }
        });
    }

    public void insert(){

        String  id_grupo = id_grupo_conta2;
        String  nome_conta = nome_conta2;
        String  id_cliente = String.valueOf(Static.getId_cliente());

        insertToDatabase(id_grupo, nome_conta,id_cliente);

    }

    private void insertToDatabase(String id_grupo, String nome_conta,String id_cliente){
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String>
        {
            @Override
            protected String doInBackground(String... params)
            {
                String paramid_grupo = params[0];
                String paramnome_conta = params[1];
                String paramid_cliente = params[2];


                String  id_grupo = id_grupo_conta2;
                String  nome_conta = nome_conta2;
                String  id_cliente = String.valueOf(Static.getId_cliente());

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("id_grupo", id_grupo));
                nameValuePairs.add(new BasicNameValuePair("nome_conta", nome_conta));
                nameValuePairs.add(new BasicNameValuePair("id_cliente", id_cliente));

                try
                {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "http://premiumcontrol.com.br/NakasoneSoftapp/cadastro_contas_nakasone.php");
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                    HttpResponse response = httpClient.execute(httpPost);

                    HttpEntity entity = response.getEntity();

                    //is = entity.getContent();

                }
                catch (ClientProtocolException e)
                {

                }
                catch (IOException e)
                {

                }
                return "success";
            }

            @Override
            protected void onPostExecute(String result)
            {
                super.onPostExecute(result);
                Toast.makeText(implantacaoErotina.this, result, Toast.LENGTH_LONG).show();
            }



        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(id_grupo, nome_conta,id_cliente);
    }
}