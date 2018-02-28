package com.f22labs.instalikefragmenttransaction.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DataParser extends AsyncTask<Void,Void,Integer> {

    Context c;
    GridView gv;
    static int contagem;
    String jsonData;
    double soma;
    ProgressDialog pd;
    ArrayList<String> spacecrafts=new ArrayList<>();
   public static ArrayList<String> itemsa=new ArrayList<>();


    public DataParser(Context c, GridView gv, String jsonData) {
        this.c = c;
        this.gv = gv;
        this.jsonData = jsonData;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        pd=new ProgressDialog(c);
        pd.setTitle("Parse");
        pd.setMessage("Parsing..Please Wait");
        pd.show();
    }

    @Override
    protected Integer doInBackground(Void... params) {
        return this.parseData();
    }

    @Override
    protected void onPostExecute(Integer result) {
        super.onPostExecute(result);

        pd.dismiss();

        if(result==0)
        {
            Toast.makeText(c,"Unable to parse",Toast.LENGTH_SHORT).show();
        }else
        {
            ArrayAdapter adapter=new ArrayAdapter(c,android.R.layout.simple_list_item_1,spacecrafts);
            gv.setAdapter(adapter);

            gv.setOnItemClickListener(new AdapterView.OnItemClickListener()
            {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                {
                    Toast.makeText(c,spacecrafts.get(position),Toast.LENGTH_SHORT).show();

                }
            });

        }
    }

    private int parseData()
    {
        try {

            JSONArray ja=new JSONArray(jsonData);
            JSONObject jo=null;

            spacecrafts.clear();

            for(int i=0;i<ja.length();i++)
            {
                switch (i)
                {
                    case 0: spacecrafts.add("Total de Janeiro:"); break;
                    case 1: spacecrafts.add("Total de Fevereiro:"); break;
                    case 2: spacecrafts.add("Total de Março:"); break;
                    case 3: spacecrafts.add("Total de Abril:"); break;
                    case 4: spacecrafts.add("Total de Maio:"); break;
                    case 5: spacecrafts.add("Total de Junho:"); break;
                    case 6: spacecrafts.add("Total de Julho:"); break;
                    case 7: spacecrafts.add("Total de Agosto:"); break;
                    case 8: spacecrafts.add("Total de Setembro:"); break;
                    case 9: spacecrafts.add("Total de Outubro:"); break;
                    case 10: spacecrafts.add("Total de Novembro:"); break;
                    case 11: spacecrafts.add("Total de Dezembro:"); break;
                    default: spacecrafts.add("Total:"); break;
                }

                jo=ja.getJSONObject(i);

                String name  =   jo.getString("valor");

                if(name == "null"){spacecrafts.add("R$ 0.00");}

                else{spacecrafts.add("R$ " + name); soma = soma + Double.parseDouble(name);}

               if(contagem ==0 && i == 0)
               {
                   if(name == "null")
                   {
                       staticd.setJaneiro_parcial("0");
                   }
                   else{staticd.setJaneiro_parcial(name);}

                   Log.d("JAN DESPESAS",staticd.getJaneiro_parcial());
               }
               if(contagem ==0 && i == 1)
                {
                    if(name == "null")
                    {
                        staticd.setFevereiro_parcial("0");
                    }
                    else{staticd.setFevereiro_parcial(name);}
                    Log.d("FEV DESPESAS",staticd.getFevereiro_parcial());
                }

                if(contagem ==0 && i == 2)
                {

                    if(name == "null")
                    {
                        staticd.setMarco_parcial("0");
                    }
                    else{staticd.setMarco_parcial(name);}
                    Log.d("MAR DESPESAS",staticd.getMarco_parcial());
                }
                if(contagem ==0 && i == 3)
                {

                    if(name == "null")
                    {
                        staticd.setAbril_parcial("0");
                    }
                    else{
                    staticd.setAbril_parcial(name);}
                    Log.d("ABRIL DESPESAS",staticd.getAbril_parcial());
                }
                if(contagem ==0 && i == 4)
                {
                    if(name == "null")
                    {
                        staticd.setMaio_parcial("0");
                    }
                    else{staticd.setMaio_parcial(name);}
                    Log.d("MAIO DESPESAS",staticd.getMaio_parcial());
                }
                if(contagem ==0 && i == 5)
                {

                    if(name == "null")
                    {
                        staticd.setJunho_parcial("0");
                    }
                    else{staticd.setJunho_parcial(name);}
                    Log.d("JUNHO DESPESAS",staticd.getJunho_parcial());
                }
                if(contagem ==0 && i == 6)
                {

                    if(name == "null")
                    {
                        staticd.setJulho_parcial("0");
                    }
                    else{staticd.setJulho_parcial(name);}
                    Log.d("JULHO DESPESAS",staticd.getJulho_parcial());
                }
                if(contagem ==0 && i == 7)
                {

                    if(name == "null")
                    {
                        staticd.setAgosto_parcial("0");
                    }
                    else{staticd.setAgosto_parcial(name);}
                    Log.d("AGOSTO DESPESAS",staticd.getAgosto_parcial());
                }
                if(contagem ==0 && i == 8)
                {

                    if(name == "null")
                    {
                        staticd.setSetembro_parcial("0");
                    }
                    else{staticd.setSetembro_parcial(name);}
                    Log.d("SETEMBRO DESPESAS",staticd.getSetembro_parcial());
                }
                if(contagem ==0 && i == 9)
                {
                    if(name == "null")
                    {
                        staticd.setOutubro_parcial("0");
                    }
                    else{staticd.setOutubro_parcial(name);}
                    Log.d("OUTUBRO DESPESAS",staticd.getOutubro_parcial());
                }
                if(contagem ==0 && i == 10)
                {

                    if(name == "null")
                    {
                        staticd.setNovembro_parcial("0");
                    }
                    else{staticd.setNovembro_parcial(name);}
                    Log.d("NOVEMBRO DESPESAS",staticd.getNovembro_parcial());
                }
                if(contagem ==0 && i == 11)
                {

                    if(name == "null")
                    {
                        staticd.setDezembro_parcial("0");
                    }
                    else{staticd.setDezembro_parcial(name);}
                    Log.d("DEZEMBRO DESPESAS",staticd.getDezembro_parcial());
                }




                if(contagem ==1 && i == 0)
                {
                    if(name == "null")
                    {
                        staticd.setJaneiro_parcial_second("0");
                    }
                    else{staticd.setJaneiro_parcial_second(name);}
                    Log.d("JAN DESPESAS",staticd.getJaneiro_parcial_second());
                }
                if(contagem ==1 && i == 1)
                {
                    if(name == "null")
                    {
                        staticd.setFevereiro_parcial_second("0");
                    }
                    else{staticd.setFevereiro_parcial_second(name);}
                    Log.d("FEV DESPESAS",staticd.getFevereiro_parcial_second());
                }

                if(contagem ==1 && i == 2)
                {
                    if(name == "null")
                    {
                        staticd.setMarco_parcial_second("0");
                    }
                    else{staticd.setMarco_parcial_second(name);}
                    Log.d("MAR DESPESAS",staticd.getMarco_parcial_second());
                }
                if(contagem ==1 && i == 3)
                {
                    if(name == "null")
                    {
                        staticd.setAbril_parcial_second("0");
                    }
                    else{staticd.setAbril_parcial_second(name);}
                    Log.d("ABRIL DESPESAS",staticd.getAbril_parcial_second());
                }
                if(contagem ==1 && i == 4)
                {
                    if(name == "null")
                    {
                        staticd.setMaio_parcial_second("0");
                    }
                    else{staticd.setMaio_parcial_second(name);}
                    Log.d("MAIO DESPESAS",staticd.getMaio_parcial_second());
                }
                if(contagem ==1 && i == 5)
                {

                    if(name == "null")
                    {
                        staticd.setJunho_parcial_second("0");
                    }
                    else{staticd.setJunho_parcial_second(name);}
                    Log.d("JUNHO DESPESAS",staticd.getJunho_parcial_second());
                }
                if(contagem ==1 && i == 6)
                {

                    if(name == "null")
                    {
                        staticd.setJulho_parcial_second("0");
                    }
                    else{staticd.setJulho_parcial_second(name);}
                    Log.d("JULHO DESPESAS",staticd.getJulho_parcial_second());
                }
                if(contagem ==1 && i == 7)
                {

                    if(name == "null")
                    {
                        staticd.setAgosto_parcial_second("0");
                    }
                    else{staticd.setAgosto_parcial_second(name);}
                    Log.d("AGOSTO DESPESAS",staticd.getAgosto_parcial_second());
                }
                if(contagem ==1 && i == 8)
                {

                    if(name == "null")
                    {
                        staticd.setSetembro_parcial_second("0");
                    }
                    else{staticd.setSetembro_parcial_second(name);}
                    Log.d("SETEMBRO DESPESAS",staticd.getSetembro_parcial_second());
                }
                if(contagem ==1 && i == 9)
                {

                    if(name == "null")
                    {
                        staticd.setOutubro_parcial_second("0");
                    }
                    else{staticd.setOutubro_parcial_second(name);}
                    Log.d("OUTUBRO DESPESAS",staticd.getOutubro_parcial_second());
                }
                if(contagem ==1 && i == 10)
                {

                    if(name == "null")
                    {
                        staticd.setNovembro_parcial_second("0");
                    }
                    else{staticd.setNovembro_parcial_second(name);itemsa.add(String.valueOf(Double.parseDouble(staticd.getNovembro_parcial()) - Double.parseDouble(staticd.getNovembro_parcial_second())));}
                    Log.d("NOVEMBRO DESPESAS",staticd.getNovembro_parcial_second());
                }
                if(contagem ==1 && i == 11)
                {

                    if(name == "null")
                    {
                        staticd.setDezembro_parcial_second("0");
                    }
                    else{staticd.setDezembro_parcial_second(name); itemsa.add(String.valueOf(Double.parseDouble(staticd.getDezembro_parcial()) - Double.parseDouble(staticd.getDezembro_parcial_second()))); }
                    Log.d("DEZEMBRO DESPESAS",staticd.getDezembro_parcial_second());
                }

                if(i == 11){spacecrafts.add("Total Geral: "); spacecrafts.add("R$ " + String.valueOf(soma));contagem++;}

                if(contagem == 2)
                {
                   /* Log.d("Passou por aqui","1");

                       spacecrafts.clear();
                       spacecrafts.add(staticd.getDezembro_parcial());
                       spacecrafts.add(staticd.getDezembro_parcial());
                       contagem++;*/

                }


            }

            return 1;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return 0;
    }

}
