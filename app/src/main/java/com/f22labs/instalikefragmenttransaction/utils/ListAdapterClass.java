package com.f22labs.instalikefragmenttransaction.utils;

/**
 * Created by Juned on 12/24/2016.
 */

import android.content.Context;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.f22labs.instalikefragmenttransaction.R;
import com.f22labs.instalikefragmenttransaction.adapters.GetDataAdapterDesativarContas;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class ListAdapterClass extends BaseAdapter {

    Context context;
    List<GetDataAdapterDesativarContas> valueList;

    public ListAdapterClass(List<GetDataAdapterDesativarContas> listValue, Context context) {
        this.context = context;
        this.valueList = listValue;
    }

    @Override
    public int getCount() {
        return this.valueList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.valueList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewItem viewItem = null;

        if (convertView == null) {
            viewItem = new ViewItem();

            LayoutInflater layoutInfiater = (LayoutInflater) this.context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            convertView = layoutInfiater.inflate(R.layout.layout_items, null);

            viewItem.TextViewSubjectName = (TextView) convertView.findViewById(R.id.textView1);

            viewItem.SwitchViewSubjectName = (Switch) convertView.findViewById(R.id.switch122);

            if (valueList.get(position).getStatus().equals("1"))
            {
                viewItem.SwitchViewSubjectName.setChecked(true);
                viewItem.SwitchViewSubjectName.setText("Ativada");
                Log.d("Caiu no if", "Bom menino kkkk");
            }
            else
            {
                viewItem.SwitchViewSubjectName.setChecked(false);
                viewItem.SwitchViewSubjectName.setText("Desativada");
                Log.d("Caiu no else", "Se fodeu kkkk");
            }

            viewItem.SwitchViewSubjectName.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked)
                    {
                        UpdateToDatabase(valueList.get(position).getId_conta());
                        buttonView.setText("Ativada");
                    } else {
                        if (isChecked == false)
                        {
                            UpdateToDatabaseDesativar(valueList.get(position).getId_conta());
                            buttonView.setText("Desativada");
                        }

                    }

                }
            });

            convertView.setTag(viewItem);
        } else {
            viewItem = (ViewItem) convertView.getTag();
        }

        viewItem.TextViewSubjectName.setText(valueList.get(position).getNome_conta());

        return convertView;
    }

    private void UpdateToDatabase(final String id_conta) {
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {
                String paramConta = params[0];


                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("id_conta", id_conta));


                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost("http://premiumcontrol.com.br/NakasoneSoftapp/select/conta_inicial_ativar.php?id_conta=" + id_conta + "");
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


    private void UpdateToDatabaseDesativar(final String id_conta) {
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {
                String paramConta = params[0];


                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("id_conta", id_conta));


                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost("http://premiumcontrol.com.br/NakasoneSoftapp/select/conta_inicial_desativar.php?id_conta=" + id_conta + "");
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


    class ViewItem
    {
        TextView TextViewSubjectName;
         Switch SwitchViewSubjectName;

    }
}
