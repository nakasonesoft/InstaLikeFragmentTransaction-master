package com.f22labs.instalikefragmenttransaction.activities;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.f22labs.instalikefragmenttransaction.R;
import com.f22labs.instalikefragmenttransaction.adapters.GetDataAdapterDesativarContas;
import com.f22labs.instalikefragmenttransaction.utils.HttpServicesClass;
import com.f22labs.instalikefragmenttransaction.utils.ListAdapterClass;
import com.f22labs.instalikefragmenttransaction.utils.Static;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListViewTeste extends AppCompatActivity {

    ListView SubjectListView,SubjectListView2,SubjectListView3,SubjectListView4,SubjectListView5,SubjectListView6,SubjectListView7,SubjectListView8;
    Button btnSalvar;
    ProgressBar progressBarSubject;
    TextView textView90;

    String ServerURL = "http://premiumcontrol.com.br/NakasoneSoftapp/grupos/caixas_bancos.php?id_cliente="+ Static.getId_cliente()+"";
    String ServerURL2 = "http://premiumcontrol.com.br/NakasoneSoftapp/grupos/investimentos_outros.php?id_cliente="+ Static.getId_cliente()+"";
    String ServerURL3= "http://premiumcontrol.com.br/NakasoneSoftapp/grupos/bens.php?id_cliente="+ Static.getId_cliente()+"";
    String ServerURL4 = "http://premiumcontrol.com.br/NakasoneSoftapp/grupos/dividas_cartao.php?id_cliente="+ Static.getId_cliente()+"";
    String ServerURL5 = "http://premiumcontrol.com.br/NakasoneSoftapp/grupos/outras_dividas.php?id_cliente="+ Static.getId_cliente()+"";
    String ServerURL6 = "http://premiumcontrol.com.br/NakasoneSoftapp/grupos/receitas.php?id_cliente="+ Static.getId_cliente()+"";
    String ServerURL7 = "http://premiumcontrol.com.br/NakasoneSoftapp/grupos/despesas_necessarias.php?id_cliente="+ Static.getId_cliente()+"";
    String ServerURL8 = "http://premiumcontrol.com.br/NakasoneSoftapp/grupos/despesas_superfluas.php?id_cliente="+ Static.getId_cliente()+"";


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_teste);

        progressBarSubject = (ProgressBar)findViewById(R.id.progressBar);

        new GetHttpResponse(ListViewTeste.this).execute();
        new GetHttpResponse2(ListViewTeste.this).execute();
        new GetHttpResponse3(ListViewTeste.this).execute();
        new GetHttpResponse4(ListViewTeste.this).execute();
        new GetHttpResponse5(ListViewTeste.this).execute();
        new GetHttpResponse6(ListViewTeste.this).execute();
        new GetHttpResponse7(ListViewTeste.this).execute();
        new GetHttpResponse8(ListViewTeste.this).execute();

        SubjectListView = (ListView)findViewById(R.id.listview1);
        SubjectListView2 = (ListView)findViewById(R.id.listview2);
        SubjectListView3 = (ListView)findViewById(R.id.listview3);
        SubjectListView4 = (ListView)findViewById(R.id.listview4);
        SubjectListView5 = (ListView)findViewById(R.id.listview5);
        SubjectListView6 = (ListView)findViewById(R.id.listview6);
        SubjectListView7 = (ListView)findViewById(R.id.listview7);
        SubjectListView8 = (ListView)findViewById(R.id.listview8);

        textView90 = (TextView)findViewById(R.id.textView90);

        //region Botão
        btnSalvar = (Button)findViewById(R.id.pesquisa_rapida);

        btnSalvar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplicationContext(), ListViewTeste2.class);
                startActivity(intent);
            }
        });
        //endregion

    }

    private class GetHttpResponse extends AsyncTask<Void, Void, Void>
    {
        public Context context;

        String ResultHolder;

        List<GetDataAdapterDesativarContas> subjectsList;

        public GetHttpResponse(Context context)
        {
            this.context = context;
        }

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0)
        {
            HttpServicesClass httpServiceObject = new HttpServicesClass(ServerURL);
            try
            {
                httpServiceObject.ExecutePostRequest();

                if(httpServiceObject.getResponseCode() == 200)
                {
                    ResultHolder = httpServiceObject.getResponse();

                    if(ResultHolder != null)
                    {
                        JSONArray jsonArray = null;

                        try {
                            jsonArray = new JSONArray(ResultHolder);

                            JSONObject jsonObject;

                            GetDataAdapterDesativarContas subjects;

                            subjectsList = new ArrayList<GetDataAdapterDesativarContas>();

                            for(int i=0; i<jsonArray.length(); i++)
                            {
                                subjects = new GetDataAdapterDesativarContas();

                                jsonObject = jsonArray.getJSONObject(i);

                                subjects.setNome_conta(jsonObject.getString("nome_conta"));
                                subjects.setId_conta(jsonObject.getString("id_conta"));
                                subjects.setStatus(jsonObject.getString("status"));
                                subjectsList.add(subjects);
                            }
                        }
                        catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }
                else
                {
                    Toast.makeText(context, httpServiceObject.getErrorMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            catch (Exception e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result)

        {
            progressBarSubject.setVisibility(View.GONE);

            SubjectListView.setVisibility(View.VISIBLE);

            if(subjectsList != null)
            {
                ListAdapterClass adapter = new ListAdapterClass(subjectsList, context);

                SubjectListView.setAdapter(adapter);
            }
        }
    }

    private class GetHttpResponse2 extends AsyncTask<Void, Void, Void>
    {
        public Context context;

        String ResultHolder;

        List<GetDataAdapterDesativarContas> subjectsList;

        public GetHttpResponse2(Context context)
        {
            this.context = context;
        }

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0)
        {
            HttpServicesClass httpServiceObject = new HttpServicesClass(ServerURL2);
            try
            {
                httpServiceObject.ExecutePostRequest();

                if(httpServiceObject.getResponseCode() == 200)
                {
                    ResultHolder = httpServiceObject.getResponse();

                    if(ResultHolder != null)
                    {
                        JSONArray jsonArray = null;

                        try {
                            jsonArray = new JSONArray(ResultHolder);

                            JSONObject jsonObject;

                            GetDataAdapterDesativarContas subjects;

                            subjectsList = new ArrayList<GetDataAdapterDesativarContas>();

                            for(int i=0; i<jsonArray.length(); i++)
                            {
                                subjects = new GetDataAdapterDesativarContas();

                                jsonObject = jsonArray.getJSONObject(i);

                                subjects.setNome_conta(jsonObject.getString("nome_conta"));
                                subjects.setId_conta(jsonObject.getString("id_conta"));
                                subjects.setStatus(jsonObject.getString("status"));
                                subjectsList.add(subjects);
                            }
                        }
                        catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }
                else
                {
                    Toast.makeText(context, httpServiceObject.getErrorMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            catch (Exception e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result)

        {
            progressBarSubject.setVisibility(View.GONE);

            SubjectListView2.setVisibility(View.VISIBLE);

            if(subjectsList != null)
            {
                ListAdapterClass adapter = new ListAdapterClass(subjectsList, context);

                SubjectListView2.setAdapter(adapter);
            }
            else{
                SubjectListView2.setVisibility(View.GONE);
                textView90.setVisibility(View.GONE);
            }
        }
    }

    private class GetHttpResponse3 extends AsyncTask<Void, Void, Void>
    {
        public Context context;

        String ResultHolder;

        List<GetDataAdapterDesativarContas> subjectsList;

        public GetHttpResponse3(Context context)
        {
            this.context = context;
        }

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0)
        {
            HttpServicesClass httpServiceObject = new HttpServicesClass(ServerURL3);
            try
            {
                httpServiceObject.ExecutePostRequest();

                if(httpServiceObject.getResponseCode() == 200)
                {
                    ResultHolder = httpServiceObject.getResponse();

                    if(ResultHolder != null)
                    {
                        JSONArray jsonArray = null;

                        try {
                            jsonArray = new JSONArray(ResultHolder);

                            JSONObject jsonObject;

                            GetDataAdapterDesativarContas subjects;

                            subjectsList = new ArrayList<GetDataAdapterDesativarContas>();

                            for(int i=0; i<jsonArray.length(); i++)
                            {
                                subjects = new GetDataAdapterDesativarContas();

                                jsonObject = jsonArray.getJSONObject(i);

                                subjects.setNome_conta(jsonObject.getString("nome_conta"));
                                subjects.setId_conta(jsonObject.getString("id_conta"));
                                subjects.setStatus(jsonObject.getString("status"));
                                subjectsList.add(subjects);
                            }
                        }
                        catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }
                else
                {
                    Toast.makeText(context, httpServiceObject.getErrorMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            catch (Exception e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result)

        {
            progressBarSubject.setVisibility(View.GONE);

            SubjectListView3.setVisibility(View.VISIBLE);

            if(subjectsList != null)
            {
                ListAdapterClass adapter = new ListAdapterClass(subjectsList, context);

                SubjectListView3.setAdapter(adapter);
            }
        }
    }

    private class GetHttpResponse4 extends AsyncTask<Void, Void, Void>
    {
        public Context context;

        String ResultHolder;

        List<GetDataAdapterDesativarContas> subjectsList;

        public GetHttpResponse4(Context context)
        {
            this.context = context;
        }

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0)
        {
            HttpServicesClass httpServiceObject = new HttpServicesClass(ServerURL4);
            try
            {
                httpServiceObject.ExecutePostRequest();

                if(httpServiceObject.getResponseCode() == 200)
                {
                    ResultHolder = httpServiceObject.getResponse();

                    if(ResultHolder != null)
                    {
                        JSONArray jsonArray = null;

                        try {
                            jsonArray = new JSONArray(ResultHolder);

                            JSONObject jsonObject;

                            GetDataAdapterDesativarContas subjects;

                            subjectsList = new ArrayList<GetDataAdapterDesativarContas>();

                            for(int i=0; i<jsonArray.length(); i++)
                            {
                                subjects = new GetDataAdapterDesativarContas();

                                jsonObject = jsonArray.getJSONObject(i);

                                subjects.setNome_conta(jsonObject.getString("nome_conta"));
                                subjects.setId_conta(jsonObject.getString("id_conta"));
                                subjects.setStatus(jsonObject.getString("status"));
                                subjectsList.add(subjects);
                            }
                        }
                        catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }
                else
                {
                    Toast.makeText(context, httpServiceObject.getErrorMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            catch (Exception e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result)

        {
            progressBarSubject.setVisibility(View.GONE);

            SubjectListView4.setVisibility(View.VISIBLE);

            if(subjectsList != null)
            {
                ListAdapterClass adapter = new ListAdapterClass(subjectsList, context);

                SubjectListView4.setAdapter(adapter);
            }
        }
    }

    private class GetHttpResponse5 extends AsyncTask<Void, Void, Void>
    {
        public Context context;

        String ResultHolder;

        List<GetDataAdapterDesativarContas> subjectsList;

        public GetHttpResponse5(Context context)
        {
            this.context = context;
        }

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0)
        {
            HttpServicesClass httpServiceObject = new HttpServicesClass(ServerURL5);
            try
            {
                httpServiceObject.ExecutePostRequest();

                if(httpServiceObject.getResponseCode() == 200)
                {
                    ResultHolder = httpServiceObject.getResponse();

                    if(ResultHolder != null)
                    {
                        JSONArray jsonArray = null;

                        try {
                            jsonArray = new JSONArray(ResultHolder);

                            JSONObject jsonObject;

                            GetDataAdapterDesativarContas subjects;

                            subjectsList = new ArrayList<GetDataAdapterDesativarContas>();

                            for(int i=0; i<jsonArray.length(); i++)
                            {
                                subjects = new GetDataAdapterDesativarContas();

                                jsonObject = jsonArray.getJSONObject(i);

                                subjects.setNome_conta(jsonObject.getString("nome_conta"));
                                subjects.setId_conta(jsonObject.getString("id_conta"));
                                subjects.setStatus(jsonObject.getString("status"));
                                subjectsList.add(subjects);
                            }
                        }
                        catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }
                else
                {
                    Toast.makeText(context, httpServiceObject.getErrorMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            catch (Exception e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result)

        {
            progressBarSubject.setVisibility(View.GONE);

            SubjectListView5.setVisibility(View.VISIBLE);

            if(subjectsList != null)
            {
                ListAdapterClass adapter = new ListAdapterClass(subjectsList, context);

                SubjectListView5.setAdapter(adapter);
            }
        }
    }

    private class GetHttpResponse6 extends AsyncTask<Void, Void, Void>
    {
        public Context context;

        String ResultHolder;

        List<GetDataAdapterDesativarContas> subjectsList;

        public GetHttpResponse6(Context context)
        {
            this.context = context;
        }

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0)
        {
            HttpServicesClass httpServiceObject = new HttpServicesClass(ServerURL6);
            try
            {
                httpServiceObject.ExecutePostRequest();

                if(httpServiceObject.getResponseCode() == 200)
                {
                    ResultHolder = httpServiceObject.getResponse();

                    if(ResultHolder != null)
                    {
                        JSONArray jsonArray = null;

                        try {
                            jsonArray = new JSONArray(ResultHolder);

                            JSONObject jsonObject;

                            GetDataAdapterDesativarContas subjects;

                            subjectsList = new ArrayList<GetDataAdapterDesativarContas>();

                            for(int i=0; i<jsonArray.length(); i++)
                            {
                                subjects = new GetDataAdapterDesativarContas();

                                jsonObject = jsonArray.getJSONObject(i);

                                subjects.setNome_conta(jsonObject.getString("nome_conta"));
                                subjects.setId_conta(jsonObject.getString("id_conta"));
                                subjects.setStatus(jsonObject.getString("status"));
                                subjectsList.add(subjects);
                            }
                        }
                        catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }
                else
                {
                    Toast.makeText(context, httpServiceObject.getErrorMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            catch (Exception e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result)

        {
            progressBarSubject.setVisibility(View.GONE);

            SubjectListView6.setVisibility(View.VISIBLE);

            if(subjectsList != null)
            {
                ListAdapterClass adapter = new ListAdapterClass(subjectsList, context);

                SubjectListView6.setAdapter(adapter);
            }
        }
    }

    private class GetHttpResponse7 extends AsyncTask<Void, Void, Void>
    {
        public Context context;

        String ResultHolder;

        List<GetDataAdapterDesativarContas> subjectsList;

        public GetHttpResponse7(Context context)
        {
            this.context = context;
        }

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0)
        {
            HttpServicesClass httpServiceObject = new HttpServicesClass(ServerURL7);
            try
            {
                httpServiceObject.ExecutePostRequest();

                if(httpServiceObject.getResponseCode() == 200)
                {
                    ResultHolder = httpServiceObject.getResponse();

                    if(ResultHolder != null)
                    {
                        JSONArray jsonArray = null;

                        try {
                            jsonArray = new JSONArray(ResultHolder);

                            JSONObject jsonObject;

                            GetDataAdapterDesativarContas subjects;

                            subjectsList = new ArrayList<GetDataAdapterDesativarContas>();

                            for(int i=0; i<jsonArray.length(); i++)
                            {
                                subjects = new GetDataAdapterDesativarContas();

                                jsonObject = jsonArray.getJSONObject(i);

                                subjects.setNome_conta(jsonObject.getString("nome_conta"));
                                subjects.setId_conta(jsonObject.getString("id_conta"));
                                subjects.setStatus(jsonObject.getString("status"));
                                subjectsList.add(subjects);
                            }
                        }
                        catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }
                else
                {
                    Toast.makeText(context, httpServiceObject.getErrorMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            catch (Exception e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result)

        {
            progressBarSubject.setVisibility(View.GONE);

            SubjectListView7.setVisibility(View.VISIBLE);

            if(subjectsList != null)
            {
                ListAdapterClass adapter = new ListAdapterClass(subjectsList, context);

                SubjectListView7.setAdapter(adapter);
            }
        }
    }

    private class GetHttpResponse8 extends AsyncTask<Void, Void, Void>
    {
        public Context context;

        String ResultHolder;

        List<GetDataAdapterDesativarContas> subjectsList;

        public GetHttpResponse8(Context context)
        {
            this.context = context;
        }

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0)
        {
            HttpServicesClass httpServiceObject = new HttpServicesClass(ServerURL8);
            try
            {
                httpServiceObject.ExecutePostRequest();

                if(httpServiceObject.getResponseCode() == 200)
                {
                    ResultHolder = httpServiceObject.getResponse();

                    if(ResultHolder != null)
                    {
                        JSONArray jsonArray = null;

                        try {
                            jsonArray = new JSONArray(ResultHolder);

                            JSONObject jsonObject;

                            GetDataAdapterDesativarContas subjects;

                            subjectsList = new ArrayList<GetDataAdapterDesativarContas>();

                            for(int i=0; i<jsonArray.length(); i++)
                            {
                                subjects = new GetDataAdapterDesativarContas();

                                jsonObject = jsonArray.getJSONObject(i);

                                subjects.setNome_conta(jsonObject.getString("nome_conta"));
                                subjects.setId_conta(jsonObject.getString("id_conta"));
                                subjects.setStatus(jsonObject.getString("status"));
                                subjectsList.add(subjects);
                            }
                        }
                        catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }
                else
                {
                    Toast.makeText(context, httpServiceObject.getErrorMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            catch (Exception e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result)

        {
            progressBarSubject.setVisibility(View.GONE);

            SubjectListView8.setVisibility(View.VISIBLE);

            if(subjectsList != null)
            {
                ListAdapterClass adapter = new ListAdapterClass(subjectsList, context);

                SubjectListView8.setAdapter(adapter);
            }
        }
    }




}