package com.f22labs.instalikefragmenttransaction.activities;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.f22labs.instalikefragmenttransaction.R;
import com.f22labs.instalikefragmenttransaction.adapters.GetDataAdapterDesativarContas;
import com.f22labs.instalikefragmenttransaction.utils.HttpServicesClass;
import com.f22labs.instalikefragmenttransaction.utils.ListAdapterClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListViewTeste extends AppCompatActivity {

    ListView SubjectListView;
    ProgressBar progressBarSubject;
    String ServerURL = "http://premiumcontrol.com.br/NakasoneSoftapp/select/select_conta.php";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list_view_teste);

        SubjectListView = (ListView)findViewById(R.id.listview1);

        progressBarSubject = (ProgressBar)findViewById(R.id.progressBar);

        new GetHttpResponse(ListViewTeste.this).execute();
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

}