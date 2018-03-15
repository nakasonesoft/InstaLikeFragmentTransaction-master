package com.f22labs.instalikefragmenttransaction.adapters;

import android.app.Activity;
import android.app.Dialog;
import android.os.AsyncTask;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.f22labs.instalikefragmenttransaction.R;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapterPasso2 extends RecyclerView.Adapter<RecyclerViewAdapterPasso2.ViewHolder>
{

    //Context context;
    private Activity context;

    List<GetDataAdapterPasso2> getDataAdapter;
    ImageLoader imageLoader1;

    Object mContext;

    public RecyclerViewAdapterPasso2(List<GetDataAdapterPasso2> getDataAdapter, Activity context){

        super();
        this.getDataAdapter = getDataAdapter;
        this.context = context;


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_items_passo2, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder Viewholder, final int position)
    {
        final GetDataAdapterPasso2 getDataAdapter1 =  getDataAdapter.get(position);
        final String distancia;

        Viewholder.descricao_item.setText(getDataAdapter1.getNome_conta());

        Viewholder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

            }
        });

        Viewholder.buttonPasso2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // Puxando o xml da outra tela.-----------------------------------------------------
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.custom);
                dialog.setTitle("This");

                // FindViews seguidos de implementações em cada!------------------------------------
                Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
                final EditText Saldo = (EditText) dialog.findViewById(R.id.btnSaldoPasso2);


                // Clique do OK do botão para fechar o Dialog/Segundo Layout.-----------------------
                dialogButton.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        UpdateToDatabasePasso2(getDataAdapter1.getId_conta(), Saldo.getText().toString());
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });


    }

    @Override
    public int getItemCount(){return getDataAdapter.size();}


    class ViewHolder extends RecyclerView.ViewHolder
    {

        public TextView id_item, descricao_item, id_grupo, valor_despesas,comofoipago_despesas,data_despesas,nome_conta,datafechamento_conta,descricao_despesas;

        public ProgressBar bar;

        Button buttonPasso2;

        CardView cad;

        RelativeLayout cdll;

        public ViewHolder(View itemView)
        {
            super(itemView);
            descricao_item = (TextView) itemView.findViewById(R.id.descricao_itemPasso2);
            buttonPasso2  = (Button) itemView.findViewById(R.id.buttonPasso2 );

            cad = (CardView)itemView.findViewById(R.id.cardview1);
            cdll= (RelativeLayout) itemView.findViewById(R.id.cdll2);
        }



    }
     //AlteracaoPasso2.php

    private void UpdateToDatabasePasso2(final String id_conta, final String saldoinicial_conta) {
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String>
        {
            @Override
            protected String doInBackground(String... params)
            {
                String paramConta = params[0];
                String paramContaSala = params[0];



                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("id_conta", id_conta));
                nameValuePairs.add(new BasicNameValuePair("saldoinicial_conta", saldoinicial_conta));



                try
                {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost("http://premiumcontrol.com.br/NakasoneSoftapp/select/AlteracaoPasso2.php?id_conta="+id_conta+"");
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    HttpResponse response = httpClient.execute(httpPost);
                    HttpEntity entity = response.getEntity();
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
                //Toast.makeText(getActivity(), result, Toast.LENGTH_LONG).show();

            }


        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(id_conta,saldoinicial_conta);
    }


    private void UpdateToDatabaseDesativar(final String id_conta) {
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String>
        {
            @Override
            protected String doInBackground(String... params)
            {
                String paramConta = params[0];



                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("id_conta", id_conta));



                try
                {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost("http://premiumcontrol.com.br/NakasoneSoftapp/select/conta_inicial_desativar.php?id_conta="+id_conta+"");
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    HttpResponse response = httpClient.execute(httpPost);
                    HttpEntity entity = response.getEntity();
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
                //Toast.makeText(getActivity(), result, Toast.LENGTH_LONG).show();
            }


        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(id_conta);
    }


}