package com.f22labs.instalikefragmenttransaction.interfaces;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.f22labs.instalikefragmenttransaction.R;
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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Shows how the payment process works with pagseguro.
 * Simulates an user buying a playstation using your sandbox account.
 */
public class MainActivityP extends ActionBarActivity {


    Button assinar1, assinar2, assinar3, assinar4;
   String plano;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.precos);
        /*
        findViewById(R.id.assinar4).setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                // at this point you should check if the user has internet connection
                // before stating the pagseguro checkout process.(it will need internet connection)

                // simulating an user buying an iphone 6
                final PagSeguroFactory pagseguro = PagSeguroFactory.instance();
                List<PagSeguroItem> shoppingCart = new ArrayList<>();
                shoppingCart.add(pagseguro.item("123", "PlayStation", BigDecimal.valueOf(3.50), 1, 300));
                PagSeguroPhone buyerPhone = pagseguro.phone(PagSeguroAreaCode.DDD81, "998187427");
                PagSeguroBuyer buyer = pagseguro.buyer("Ricardo Ferreira", "14/02/1978", "15061112000", "test@email.com.br", buyerPhone);
                PagSeguroAddress buyerAddress = pagseguro.address("Av. Boa Viagem", "51", "Apt201", "Boa Viagem", "51030330", "Recife", PagSeguroBrazilianStates.PERNAMBUCO);
                PagSeguroShipping buyerShippingOption = pagseguro.shipping(PagSeguroShippingType.PAC, buyerAddress);
                PagSeguroCheckout checkout = pagseguro.checkout("Ref0001", shoppingCart, buyer, buyerShippingOption);
                // starting payment process
                new PagSeguroPayment(MainActivityP.this).pay(checkout.buildCheckoutXml());
            }
        });
        */
        assinar1 = (Button)findViewById(R.id.assinar1);
        assinar2 = (Button)findViewById(R.id.assinar2);
        assinar3 = (Button)findViewById(R.id.assinar3);
        assinar4 = (Button)findViewById(R.id.assinar4);


        //region Botao Mensal 7,50
        assinar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // at this point you should check if the user has internet connection
                // before stating the pagseguro checkout process.(it will need internet connection)
                // simulating an user buying an iphone 6
                final PagSeguroFactory pagseguro = PagSeguroFactory.instance();
                List<PagSeguroItem> shoppingCart = new ArrayList<>();
                shoppingCart.add(pagseguro.item("123", "Plano Mensal", BigDecimal.valueOf(7.50), 1, 300));
                PagSeguroPhone buyerPhone = pagseguro.phone(PagSeguroAreaCode.DDD81, "998187427");
                PagSeguroBuyer buyer = pagseguro.buyer("Ricardo Ferreira", "14/02/1978", "15061112000", "test@email.com.br", buyerPhone);
                PagSeguroAddress buyerAddress = pagseguro.address("Av. Boa Viagem", "51", "Apt201", "Boa Viagem", "51030330", "Recife", PagSeguroBrazilianStates.PERNAMBUCO);
                PagSeguroShipping buyerShippingOption = pagseguro.shipping(PagSeguroShippingType.PAC, buyerAddress);
                PagSeguroCheckout checkout = pagseguro.checkout("Ref0001", shoppingCart, buyer, buyerShippingOption);
                // starting payment process
                new PagSeguroPayment(MainActivityP.this).pay(checkout.buildCheckoutXml());

                plano = "Mensal";

            }
        });
//endregion

        //region Botao Trimestral 18,90
        assinar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // at this point you should check if the user has internet connection
                // before stating the pagseguro checkout process.(it will need internet connection)

                // simulating an user buying an iphone 6
                final PagSeguroFactory pagseguro = PagSeguroFactory.instance();
                List<PagSeguroItem> shoppingCart = new ArrayList<>();
                shoppingCart.add(pagseguro.item("1234", "Plano Trimestral", BigDecimal.valueOf(18.90), 1, 300));
                PagSeguroPhone buyerPhone = pagseguro.phone(PagSeguroAreaCode.DDD81, "998187427");
                PagSeguroBuyer buyer = pagseguro.buyer("Ricardo Ferreira", "14/02/1978", "15061112000", "test@email.com.br", buyerPhone);
                PagSeguroAddress buyerAddress = pagseguro.address("Av. Boa Viagem", "51", "Apt201", "Boa Viagem", "51030330", "Recife", PagSeguroBrazilianStates.PERNAMBUCO);
                PagSeguroShipping buyerShippingOption = pagseguro.shipping(PagSeguroShippingType.PAC, buyerAddress);
                PagSeguroCheckout checkout = pagseguro.checkout("Ref0001", shoppingCart, buyer, buyerShippingOption);
                // starting payment process
                new PagSeguroPayment(MainActivityP.this).pay(checkout.buildCheckoutXml());

                plano = "Trimestral";

            }
        });
//endregion

        //region Botao Semestral 30,60
        assinar3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // at this point you should check if the user has internet connection
                // before stating the pagseguro checkout process.(it will need internet connection)

                // simulating an user buying an iphone 6
                final PagSeguroFactory pagseguro = PagSeguroFactory.instance();
                List<PagSeguroItem> shoppingCart = new ArrayList<>();
                shoppingCart.add(pagseguro.item("12345", "Plano Semestral", BigDecimal.valueOf(30.60), 1, 300));
                PagSeguroPhone buyerPhone = pagseguro.phone(PagSeguroAreaCode.DDD81, "998187427");
                PagSeguroBuyer buyer = pagseguro.buyer("Ricardo Ferreira", "14/02/1978", "15061112000", "test@email.com.br", buyerPhone);
                PagSeguroAddress buyerAddress = pagseguro.address("Av. Boa Viagem", "51", "Apt201", "Boa Viagem", "51030330", "Recife", PagSeguroBrazilianStates.PERNAMBUCO);
                PagSeguroShipping buyerShippingOption = pagseguro.shipping(PagSeguroShippingType.PAC, buyerAddress);
                PagSeguroCheckout checkout = pagseguro.checkout("Ref0001", shoppingCart, buyer, buyerShippingOption);
                // starting payment process
                new PagSeguroPayment(MainActivityP.this).pay(checkout.buildCheckoutXml());

                plano = "Semestral";
            }
        });
//endregion

        //region Botao Anual 46.80
        assinar4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // at this point you should check if the user has internet connection
                // before stating the pagseguro checkout process.(it will need internet connection)

// simulating an user buying an iphone 6
                final PagSeguroFactory pagseguro = PagSeguroFactory.instance();
                List<PagSeguroItem> shoppingCart = new ArrayList<>();
                shoppingCart.add(pagseguro.item("1253", "Plano Anual", BigDecimal.valueOf(46.80), 1, 300));
                PagSeguroPhone buyerPhone = pagseguro.phone(PagSeguroAreaCode.DDD81, "998187427");
                PagSeguroBuyer buyer = pagseguro.buyer("Ricardo Ferreira", "14/02/1978", "15061112000", "test@email.com.br", buyerPhone);
                PagSeguroAddress buyerAddress = pagseguro.address("Av. Boa Viagem", "51", "Apt201", "Boa Viagem", "51030330", "Recife", PagSeguroBrazilianStates.PERNAMBUCO);
                PagSeguroShipping buyerShippingOption = pagseguro.shipping(PagSeguroShippingType.PAC, buyerAddress);
                PagSeguroCheckout checkout = pagseguro.checkout("Ref0001", shoppingCart, buyer, buyerShippingOption);
                // starting payment process
                new PagSeguroPayment(MainActivityP.this).pay(checkout.buildCheckoutXml());

                plano = "Anual";

            }
        });
//endregion


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_CANCELED) {
            // se foi uma tentativa de pagamento
            if(requestCode==PagSeguroPayment.PAG_SEGURO_REQUEST_CODE)
            {
                // exibir confirmação de cancelamento
                final String msg = getString(R.string.transaction_cancelled);
                AppUtil.showConfirmDialog(this, msg, null);
            }
        }
        else if (resultCode == RESULT_OK)
        {
            // se foi uma tentativa de pagamento
            if(requestCode==PagSeguroPayment.PAG_SEGURO_REQUEST_CODE)
            {
                insert();
                // exibir confirmação de sucesso
                final String msg = getString(R.string.transaction_succeded);
                AppUtil.showConfirmDialog(this, msg, null);

            }
        }
        else if(resultCode == PagSeguroPayment.PAG_SEGURO_REQUEST_CODE){
            switch (data.getIntExtra(PagSeguroPayment.PAG_SEGURO_EXTRA, 0))
            {
                case PagSeguroPayment.PAG_SEGURO_REQUEST_SUCCESS_CODE:
                {
                    final String msg =getString(R.string.transaction_succeded);
                    AppUtil.showConfirmDialog(this,msg,null);
                    insert();
                    break;
                }
                case PagSeguroPayment.PAG_SEGURO_REQUEST_FAILURE_CODE:{
                    final String msg = getString(R.string.transaction_error);
                    AppUtil.showConfirmDialog(this,msg,null);
                    break;
                }
                case PagSeguroPayment.PAG_SEGURO_REQUEST_CANCELLED_CODE:{
                    final String msg = getString(R.string.transaction_cancelled);
                    AppUtil.showConfirmDialog(this,msg,null);
                    break;
                }
            }
        }
    }


    public void insert()
    {
        String  id_cliente = String.valueOf(Static.getId_cliente());
        String  plano2 = plano;
        insertToDatabase(id_cliente, plano2);
    }

    private void insertToDatabase(String id_cliente, String plano2){
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {
                String paramid_cliente = params[0];
                String paramplano = params[1];



                //InputStream is = null;

                String  id_cliente = String.valueOf(Static.getId_cliente());
                String  plano2 = plano;
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("id_cliente", id_cliente));
                nameValuePairs.add(new BasicNameValuePair("plano", plano2));



                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "http://premiumcontrol.com.br/NakasoneSoftapp/cadastro_pagseguro.php");
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

                Toast.makeText(MainActivityP.this, result, Toast.LENGTH_LONG).show();

            }
        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(id_cliente,plano2);
    }

}
