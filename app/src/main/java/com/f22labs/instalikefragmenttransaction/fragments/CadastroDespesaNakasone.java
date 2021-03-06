package com.f22labs.instalikefragmenttransaction.fragments;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.f22labs.instalikefragmenttransaction.R;
import com.f22labs.instalikefragmenttransaction.activities.MainActivity;
import com.f22labs.instalikefragmenttransaction.utils.MaskEditUtil;
import com.f22labs.instalikefragmenttransaction.utils.MoneyTextWatcher;
import com.f22labs.instalikefragmenttransaction.utils.Static;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;


public class CadastroDespesaNakasone extends BaseFragment
{

    EditText descricaodespesa,valordespesa,contadespesa,datadespesa,comofoipagadespesa;

    Button salvardespesa;
    String resposta;
    AutoCompleteTextView auto1;
    List<String> responseList;

    //region spinner variaveis
    private Spinner spinner;
    private ArrayList<String> students;

    private ArrayList<String> ids;
    private JSONArray result;
    static String id_spinner;
//endregion

    //region spinner variaveis 2
    private Spinner spinner2;
    private ArrayList<String> students2;

    private ArrayList<String> ids2;
    private JSONArray result2;
    static String id_spinner2;
    //endregion

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void insert(){

        String  descricao_despesas = auto1.getText().toString();
        String  conta_despesas = id_spinner.toString();
        String  valor_despesas = valordespesa.getText().toString();
        String  comofoipago_despesas = id_spinner2.toString();
        String  data_despesas = datadespesa.getText().toString();
        String  id_cliente = String.valueOf(Static.getId_cliente());



        insertToDatabase(descricao_despesas, conta_despesas, valor_despesas, comofoipago_despesas, data_despesas,id_cliente);

    }

    private void insertToDatabase(String descricao_despesas, String conta_despesas,String valor_despesas,String comofoipago_despesas, String data_despesas, String id_cliente){
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {
                String paramdescricao_despesas = params[0];
                String paramconta_despesas = params[1];
                String paramvalor_despesas = params[2];
                String paramcomofoipago_despesas = params[3];
                String paramdata_despesas = params[4];
                String paramid_cliente= params[5];


                //InputStream is = null;

                String  descricao_despesas = auto1.getText().toString();
                String  conta_despesas = String.valueOf(Integer.parseInt(id_spinner.toString()) - 1);
                String  valor_despesas = valordespesa.getText().toString();
                String  comofoipago_despesas =  String.valueOf(Integer.parseInt(id_spinner2.toString())-1);
                String  data_despesas = datadespesa.getText().toString();
                String  id_cliente = String.valueOf(Static.getId_cliente());


                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("descricao_despesas", descricao_despesas));
                nameValuePairs.add(new BasicNameValuePair("conta_despesas", conta_despesas));
                nameValuePairs.add(new BasicNameValuePair("valor_despesas", valor_despesas));
                nameValuePairs.add(new BasicNameValuePair("comofoipago_despesas", comofoipago_despesas));
                nameValuePairs.add(new BasicNameValuePair("data_despesas", data_despesas));
                nameValuePairs.add(new BasicNameValuePair("id_cliente", id_cliente));


                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "http://premiumcontrol.com.br/NakasoneSoftapp/cadastro_despesa_nakasone.php");
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
                UltimoID();
                //TextView textViewResult = (TextView) findViewById(R.id.textViewResult);
                // textViewResult.setText("Inserted");
            }
        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(descricao_despesas, conta_despesas, valor_despesas, comofoipago_despesas, data_despesas,id_cliente);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_cadastro_despesa, container, false);


        auto1 = (AutoCompleteTextView)view.findViewById(R.id.autoCompleteTextView1);

        new HttpGetTask().execute();

        //region FindViews
        descricaodespesa = (EditText) view.findViewById(R.id.descricaodespesa);
        contadespesa = (EditText) view.findViewById(R.id.contadespesa);
        valordespesa = (EditText) view.findViewById(R.id.valordespesa);
        datadespesa = (EditText) view.findViewById(R.id.datadespesa);
        comofoipagadespesa = (EditText) view.findViewById(R.id.comofoipagadespesa);
        salvardespesa = (Button) view.findViewById(R.id.salvardespesa);
        //endregion

        //region declarar variaveis spinner
        students = new ArrayList<String>();
        ids = new ArrayList<String>();
        spinner = (Spinner) view.findViewById(R.id.spinner_despesa);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {

                id_spinner = ids.get(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                ids.get(spinner.getSelectedItemPosition());

            }
        });
        //endregion

        //region variaveis spinner 2
        students2 = new ArrayList<String>();
        ids2 = new ArrayList<String>();
        spinner2 = (Spinner) view.findViewById(R.id.spinner_despesa1);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                id_spinner2 = ids2.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                ids2.get(spinner2.getSelectedItemPosition());
            }
        });
        //endregion

        //region Máscaras
        datadespesa.addTextChangedListener(MaskEditUtil.mask(datadespesa, MaskEditUtil.FORMAT_DATE));
        valordespesa.addTextChangedListener(new MoneyTextWatcher(valordespesa));
        //endregion]

        //region Clique do Botão
        salvardespesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                insert();
            }
        });
        //endregion

        //region Toldbar
        ButterKnife.bind(this, view);

        ( (MainActivity)getActivity()).updateToolbarTitle("Lançamento de Despesa");

        //endregion

        //region CHAMAR MÉTODOS
        getData();
        getData2();
        //endregion

        return view;

    }

    //region Spinner
    private void getData(){
        StringRequest stringRequest = new StringRequest("http://premiumcontrol.com.br/NakasoneSoftapp/select/select_grupos.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject j = null;
                        try {
                            j = new JSONObject(response);
                            result = j.getJSONArray("result");
                            getStudents(result);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

    private void getStudents(JSONArray j){

        for(int i=0;i<j.length();i++){
            try {
                JSONObject json = j.getJSONObject(i);
                students.add(json.getString("nome_grupo"));
                ids.add(json.getString("id_grupo"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        spinner.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, students));
    }

    //endregion

    //region Spinner2
    private void getData2(){
        StringRequest stringRequest = new StringRequest("http://premiumcontrol.com.br/NakasoneSoftapp/teste.php?id_cliente="+Static.getId_cliente()+"",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject j = null;
                        try {
                            j = new JSONObject(response);
                            result2 = j.getJSONArray("result");
                            getStudents2(result2);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

    private void getStudents2(JSONArray j){

        for(int i=0;i<j.length();i++)
        {
            try
            {
                JSONObject json = j.getJSONObject(i);
                students2.add(json.getString("nome_conta"));
                ids2.add(json.getString("id_conta"));
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
        }

        spinner2.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, students2));

    }


    //endregion

    private class HttpGetTask extends AsyncTask<Void, Void, String> {

        String URL = "http://premiumcontrol.com.br/NakasoneSoftapp/select/select_minidicionario.php";
        AndroidHttpClient mClient = AndroidHttpClient.newInstance("");

        @Override
        protected String doInBackground(Void... params) {
            HttpGet request = new HttpGet(URL);
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            try {
                return mClient.execute(request, responseHandler);
            } catch (ClientProtocolException exception) {
                exception.printStackTrace();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                JSONArray json = new JSONArray(result);
                Log.v("ResponseCity", result);

                responseList = new ArrayList<String>();
                List<String> responseList = new ArrayList<String>();

                for (int i = 0; i < json.length(); i++)
                {
                    final JSONObject e = json.getJSONObject(i);
                    String name = e.getString("descricao_item");
                    String desc = e.getString("id_grupo");
                    String Categoria;
                    switch (desc)
                    {
                        case "1":  Categoria= "ALIMENTAÇÃO E DESPESAS DO LAR"; break;
                        case "2":  Categoria= "MÓVEIS, APARELHOS E UTENSÍLIOS"; break;
                        case "3":  Categoria= "DESPESAS COM VEÍCULO";break;
                        case "4":  Categoria= "VESTUÁRIO E ACESSÓRIOS";break;
                        case "5":  Categoria= "SAÚDE"; break;
                        case "6":  Categoria= "DESPESAS SUPÉRFLUAS";break;
                        case "7":  Categoria= "CUIDADO PESSOAL";break;
                        case "8":  Categoria= "OUTROS BENS"; break;
                        case "9":  Categoria= "23 FINANCEIRAS"; break;
                        case "10": Categoria= "JUROS, ETC, CARTÃO DE CRÉDITO";break;
                        case "11": Categoria= "EDUCAÇÃO"; break;
                        case "12": Categoria= "DESPESAS DIVERSAS";break;
                        case "13": Categoria= "EMPREGADO (A)"; break;
                        case "14": Categoria= "ÁGUA"; break;
                        case "15": Categoria= "DESPESAS COM ANIMAL DOMÉSTICO"; break;
                        case "16": Categoria= "ALUGUEL - DESPESA"; break;
                        case "17": Categoria= "CASA";break;
                        case "18": Categoria= "DESPESAS COM TELEFONE E CELULAR\n";break;
                        case "19": Categoria= "VEÍCULO";break;
                        case "20": Categoria= "CONDUÇÃO";break;
                        case "21": Categoria= "FERRAMENTAS"; break;
                        case "22": Categoria= "OUTRAS DESPESAS";break;
                        case "23": Categoria= "EQUIPAMENTOS DE INFORMÁTICA"; break;
                        case "24": Categoria= "CONDOMÍNIO";break;
                        case "25": Categoria= "CONSÓRCIO DE IMÓVEL";break;
                        case "26": Categoria= "CONSÓRCIO DE 20"; break;
                        case "27": Categoria= "LUZ E GÁS ENCANADO";break;
                        case "28": Categoria= "SALÁRIO - RECEITA"; break;
                        case "29": Categoria= "DESPESAS COM 5";break;
                        case "30": Categoria= "CAIXA";break;
                        case "31": Categoria= "JUROS E IOF CHEQUE ESPECIAL";break;
                        case "32": Categoria= "JUROS E IOF CARTÃO DE CRÉDITO";break;
                        case "33": Categoria= "MESADA - DESPESA";break;
                        case "34": Categoria= "ALIMENTAÇÃO, HIGIENE E UTENSÍLIOS";break;
                        case "35": Categoria= "TERRENO"; break;
                        case "36": Categoria= "Selecione o banco em que foi feito o credito";break;
                        case "37": Categoria= "Grupo Investimento (criar conta)"; break;
                        case "38": Categoria= "Selecione a conta para onde foi o dinheiro";break;
                        case "39": Categoria= "Ver grupo RECEITAS"; break;
                        case "40": Categoria= "OUTRAS RECEITAS";break;
                        case "41": Categoria= "ALUGUEL - RECEITA"; break;
                        case "42": Categoria= "APOSENTADORIA";break;
                        case "43": Categoria= "GORJETA E GRATIFICAÇÃO RECEBIDA";break;
                        case "44": Categoria= "SERVIÇOS PRESTADOS";break;
                        case "45": Categoria= "SEGURO DESEMPREGO";break;
                        case "46": Categoria= "MESADA - RECEITA";break;
                        default:Categoria= "Outros";break;
                    }
                    responseList.add(name + " - " + Categoria);
                }
                ArrayAdapter<String> adapter;
                adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_dropdown_item_1line, responseList);

                auto1.setAdapter(adapter);

            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (null != mClient)
                mClient.close();





        }
    }

    //region Necessário para o Diário.

    private void UltimoID(){
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {

                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "http://premiumcontrol.com.br/NakasoneSoftapp/select/select_ultimo_despesa.php");


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
                return resposta;


            }

            @Override
            protected void onPostExecute(String result)
            {
                super.onPostExecute(result);
                try
                {
                    insertDiario();
                }
                catch (Exception e){Toast.makeText(getActivity(),"Por favor, recarregue a página para calcular seus dados", Toast.LENGTH_LONG);}

            }


        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute();

    }


    public void insertDiario()
    {
        String  origem_diario = id_spinner.toString();
        String  destino_diario = id_spinner2.toString();
        String  descricao_diario = descricaodespesa.getText().toString();
        String  valor_diario = valordespesa.getText().toString();
        String  data_diario = datadespesa.getText().toString();
        String  tipo_diario = "Despesa";
        String  idtipo_diario = resposta;
        String  id_cliente = String.valueOf(Static.getId_cliente());

        insertToDatabaseDiario(origem_diario,destino_diario,descricao_diario,valor_diario,data_diario,tipo_diario,idtipo_diario,id_cliente);
    }

    private void insertToDatabaseDiario(String origem_diario, String destino_diario,String descricao_diario,String valor_diario,String data_diario,String tipo_diario,String idtipo_diario,String id_cliente)
    {
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String>
        {
            @Override
            protected String doInBackground(String... params)
            {
                String paramorigem_diario = params[0];
                String paramdestino_diario = params[1];
                String paramdescricao_diario = params[2];
                String paramvalor_diario = params[3];
                String paramdata_diario = params[4];
                String paramtipo_diario = params[5];
                String paramidtipo_diario = params[6];
                String paramid_cliente = params[7];

                String  origem_diario = id_spinner.toString();
                String  destino_diario = id_spinner2.toString();
                String  descricao_diario = descricaodespesa.getText().toString();
                String  valor_diario = valordespesa.getText().toString();
                String  data_diario = datadespesa.getText().toString();
                String  tipo_diario = "Despesa";
                String  idtipo_diario = resposta;
                String  id_cliente = String.valueOf(Static.getId_cliente());

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("origem_diario", origem_diario));
                nameValuePairs.add(new BasicNameValuePair("destino_diario", destino_diario));
                nameValuePairs.add(new BasicNameValuePair("descricao_diario", descricao_diario));
                nameValuePairs.add(new BasicNameValuePair("valor_diario", valor_diario));
                nameValuePairs.add(new BasicNameValuePair("data_diario", data_diario));
                nameValuePairs.add(new BasicNameValuePair("tipo_diario", tipo_diario));
                nameValuePairs.add(new BasicNameValuePair("idtipo_diario", idtipo_diario));
                nameValuePairs.add(new BasicNameValuePair("id_cliente", id_cliente));

                try
                {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "http://premiumcontrol.com.br/NakasoneSoftapp/Cadastrar_Diario_Final.php");
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
                Toast.makeText(getActivity(), result, Toast.LENGTH_LONG).show();

            }
        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(origem_diario,destino_diario,descricao_diario,valor_diario,data_diario,tipo_diario,idtipo_diario,id_cliente);
    }


    //endregion


}
