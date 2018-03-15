package com.f22labs.instalikefragmenttransaction.adapters;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.f22labs.instalikefragmenttransaction.R;
import com.f22labs.instalikefragmenttransaction.activities.MainActivity;
import com.f22labs.instalikefragmenttransaction.fragments.AlterarCarneNakasone;
import com.f22labs.instalikefragmenttransaction.fragments.AlterarConsorcioNakasone;
import com.f22labs.instalikefragmenttransaction.fragments.AlterarDepositoNakasone;
import com.f22labs.instalikefragmenttransaction.fragments.AlterarDespesaNakasone;
import com.f22labs.instalikefragmenttransaction.fragments.AlterarImoveisNakasone;
import com.f22labs.instalikefragmenttransaction.fragments.AlterarOutrosNakasone;
import com.f22labs.instalikefragmenttransaction.fragments.AlterarReceitaNakasone;
import com.f22labs.instalikefragmenttransaction.fragments.AlterarSaqueNakasone;
import com.f22labs.instalikefragmenttransaction.fragments.AlterarTransferenciaNakasone;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapterDiario2 extends RecyclerView.Adapter<RecyclerViewAdapterDiario2.ViewHolder>
{

    //Context context;
    private Activity context;

    List<GetDataAdapterDiario> getDataAdapter;
    ImageLoader imageLoader1;
    Object mContext;

    String GET_JSON_DATA_HTTP_URL = "http://premiumcontrol.com.br/NakasoneSoftapp/select/selectronaldo.php?id_conta=";
    JsonArrayRequest jsonArrayRequest;
    RequestQueue requestQueue;
    String nome;

    public RecyclerViewAdapterDiario2(List<GetDataAdapterDiario> getDataAdapter, Activity context){

        super();
        this.getDataAdapter = getDataAdapter;
        this.context = context;


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_items_diario2, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder Viewholder, final int position)
    {
        final GetDataAdapterDiario getDataAdapter1 =  getDataAdapter.get(position);
        final String distancia;

        Viewholder.descricao_diario.setText(getDataAdapter1.getDescricao_diario());
        Viewholder.destino_diario.setText(getDataAdapter1.getDestino_diario());
        Viewholder.origem_diario.setText(getDataAdapter1.getOrigem_diario());
        Viewholder.valor_diario.setText(getDataAdapter1.getValor_diario());
        Viewholder.data_diario.setText(getDataAdapter1.getData_diario());
        Viewholder.tipo_diario.setText(getDataAdapter1.getTipo_diario());
        Viewholder.idtipo_diario.setText(getDataAdapter1.getIdtipo_diario());
        Viewholder.id_cliente.setText(getDataAdapter1.getId_cliente());
        /*
            //region Clique
        Viewholder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                switch (getDataAdapter1.getTipo_diario())
                {
                    case "Receita":
                        Static.setId_receita(Integer.parseInt(String.valueOf(Viewholder.idtipo_diario.getText())));

                        AlterarReceitaNakasone frag = new AlterarReceitaNakasone();

                        MainActivity mainActivity = (MainActivity) context;

                        mainActivity.pushFragment(frag);

                         break;

                    case "Despesa":
                        Static.setId_depesas(Integer.parseInt(String.valueOf(Viewholder.idtipo_diario.getText())));

                        AlterarDespesaNakasone frag1 = new AlterarDespesaNakasone();

                        MainActivity mainActivity1 = (MainActivity) context;

                        mainActivity1.pushFragment(frag1);
                        break;

                    case "Transferencia":
                        Static.setId_transferencia(Integer.parseInt(String.valueOf(Viewholder.idtipo_diario.getText())));

                        AlterarTransferenciaNakasone frag2 = new AlterarTransferenciaNakasone();

                        MainActivity mainActivity2 = (MainActivity) context;

                        mainActivity2.pushFragment(frag2);

                        break;

                    case "Saque":
                        Static.setId_saque(Integer.parseInt(String.valueOf(Viewholder.idtipo_diario.getText())));

                        AlterarSaqueNakasone frag3 = new AlterarSaqueNakasone();

                        MainActivity mainActivity3 = (MainActivity) context;

                        mainActivity3.pushFragment(frag3);
                        break;

                    case "Deposito":
                        Static.setId_deposito(Integer.parseInt(String.valueOf(Viewholder.idtipo_diario.getText())));

                        AlterarDepositoNakasone frag4 = new AlterarDepositoNakasone();

                        MainActivity mainActivity4 = (MainActivity) context;

                        mainActivity4.pushFragment(frag4);
                        break;

                    case "Fatura":
                        Static.setId_depesas(Integer.parseInt(String.valueOf(Viewholder.idtipo_diario.getText())));

                        AlterarDespesaNakasone frag5 = new AlterarDespesaNakasone();

                        MainActivity mainActivity5 = (MainActivity) context;

                        mainActivity5.pushFragment(frag5);
                        break;

                    case "Carne":
                        Static.setId_carne(Integer.parseInt(String.valueOf(Viewholder.idtipo_diario.getText())));

                        AlterarCarneNakasone frag6 = new AlterarCarneNakasone();

                        MainActivity mainActivity6 = (MainActivity) context;

                        mainActivity6.pushFragment(frag6);
                        break;

                    case "Imoveis":
                        Static.setId_prestImovel(Integer.parseInt(String.valueOf(Viewholder.idtipo_diario.getText())));

                        AlterarImoveisNakasone frag7 = new AlterarImoveisNakasone();

                        MainActivity mainActivity7 = (MainActivity) context;

                        mainActivity7.pushFragment(frag7);
                        break;

                    case "Consorcio":
                        Static.setPrest_consorcio(Integer.parseInt(String.valueOf(Viewholder.idtipo_diario.getText())));

                        AlterarConsorcioNakasone frag8 = new AlterarConsorcioNakasone();

                        MainActivity mainActivity8 = (MainActivity) context;

                        mainActivity8.pushFragment(frag8);
                        break;

                    case "Outros":
                        Static.setId_outros(Integer.parseInt(String.valueOf(Viewholder.idtipo_diario.getText())));

                        AlterarOutrosNakasone frag9 = new AlterarOutrosNakasone();

                        MainActivity mainActivity9 = (MainActivity) context;

                        mainActivity9.pushFragment(frag9);
                        break;
                }
            }
        });
        //endregion
        */
    }

    @Override
    public int getItemCount(){return getDataAdapter.size();}


    class ViewHolder extends RecyclerView.ViewHolder
    {

        public TextView descricao_diario,destino_diario, origem_diario,valor_diario,data_diario,tipo_diario,idtipo_diario,id_cliente;

        public ProgressBar bar;

        CardView cad;

        RelativeLayout cdll;

        public ViewHolder(View itemView)
        {
            super(itemView);

            descricao_diario = (TextView) itemView.findViewById(R.id.descricao_diario);
            destino_diario = (TextView) itemView.findViewById(R.id.destino_diario);
            origem_diario = (TextView) itemView.findViewById(R.id.origem_diario);
            valor_diario = (TextView) itemView.findViewById(R.id.valor_diario);
            data_diario = (TextView) itemView.findViewById(R.id.data_diario);
            tipo_diario = (TextView) itemView.findViewById(R.id.tipo_diario);
            idtipo_diario = (TextView) itemView.findViewById(R.id.idtipo_diario);
            id_cliente = (TextView) itemView.findViewById(R.id.id_cliente);


            cad = (CardView)itemView.findViewById(R.id.cardview1);
            cdll= (RelativeLayout) itemView.findViewById(R.id.cdll2);
        }



    }

    private void UpdateToDatabase(final String id_conta) {
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
                    HttpPost httpPost = new HttpPost("http://premiumcontrol.com.br/NakasoneSoftapp/select/conta_inicial_ativar.php?id_conta="+id_conta+"");
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