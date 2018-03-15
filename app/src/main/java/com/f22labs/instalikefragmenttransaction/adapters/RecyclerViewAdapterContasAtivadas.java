package com.f22labs.instalikefragmenttransaction.adapters;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.f22labs.instalikefragmenttransaction.R;
import com.f22labs.instalikefragmenttransaction.activities.MainActivity;
import com.f22labs.instalikefragmenttransaction.fragments.AlterarContaNakasone;
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

public class RecyclerViewAdapterContasAtivadas extends RecyclerView.Adapter<RecyclerViewAdapterContasAtivadas.ViewHolder>
{

    //Context context;
    private Activity context;

    List<GetDataAdapter> getDataAdapter;
    ImageLoader imageLoader1;


    Object mContext;
    public RecyclerViewAdapterContasAtivadas(List<GetDataAdapter> getDataAdapter, Activity context){

        super();
        this.getDataAdapter = getDataAdapter;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_items_contas_ativas, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder Viewholder, final int position)
    {

        final GetDataAdapter getDataAdapter1 =  getDataAdapter.get(position);
        final String distancia ;


        Viewholder.id_conta_ativada.setText(getDataAdapter1.getId_conta_conta());
        Viewholder.saldo_ativada.setText(getDataAdapter1.getSaldoinicial_conta());
        Viewholder.nome_conta_ativada.setText(getDataAdapter1.getNome_conta());
        Viewholder.data_encerramento_ativada.setText(getDataAdapter1.getDatafechamento_conta());


        Viewholder.desativar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UpdateToDatabaseDesativar( Viewholder.id_conta_ativada.getText().toString());
            }
        });



        Viewholder.itemView.setOnClickListener(
                new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Static.setId_conta(Integer.parseInt(String.valueOf(Viewholder.id_conta_ativada.getText())));

                AlterarContaNakasone frag = new AlterarContaNakasone();

                MainActivity mainActivity = (MainActivity) context;

                mainActivity.pushFragment(frag);
            }
        });


    }

    @Override
    public int getItemCount() {

        return getDataAdapter.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder
    {

        public TextView id_conta_ativada, saldo_ativada,data_encerramento_ativada,nome_conta_ativada;
        ImageView desativar;

        public ProgressBar bar;
        CardView cad;
        RelativeLayout cdll;



        public ViewHolder(View itemView)
        {
            super(itemView);

            id_conta_ativada= (TextView) itemView.findViewById(R.id.id_conta_ativada);
            saldo_ativada = (TextView) itemView.findViewById(R.id.saldo_ativada);
            data_encerramento_ativada= (TextView) itemView.findViewById(R.id.data_encerramento_ativada);
            nome_conta_ativada = (TextView)itemView.findViewById(R.id.nome_conta_ativada);
            desativar = (ImageView)itemView.findViewById(R.id.desativar);


            bar=(ProgressBar)itemView.findViewById(R.id.progressBar);
            cad = (CardView)itemView.findViewById(R.id.cardview1);
            cdll= (RelativeLayout) itemView.findViewById(R.id.cdll2);
        }
    }



    private void UpdateToDatabaseDesativar(final String id_conta) {
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {
                String paramConta = params[0];


                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("id_conta", id_conta));


                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost("http://premiumcontrol.com.br/NakasoneSoftapp/select/conta_inicial_desativar.php?id_cliente="+Static.getId_cliente()+"&id_conta=" + id_conta + "");
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    HttpResponse response = httpClient.execute(httpPost);
                    HttpEntity entity = response.getEntity();
                } catch (ClientProtocolException e) {

                } catch (IOException e) {

                }
                return "success";
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                //Toast.makeText(getActivity(), result, Toast.LENGTH_LONG).show();
            }


        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(id_conta);
    }
}