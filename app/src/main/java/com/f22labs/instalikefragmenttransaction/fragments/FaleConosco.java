package com.f22labs.instalikefragmenttransaction.fragments;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.f22labs.instalikefragmenttransaction.R;
import com.f22labs.instalikefragmenttransaction.activities.MainActivity;
import com.f22labs.instalikefragmenttransaction.utils.GMailSender;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.sql.DataSource;

import butterknife.ButterKnife;

/**
 * Created by f22labs on 07/03/17.
 */

public class FaleConosco extends BaseFragment {

    public static final String ARGS_INSTANCE = "com.f22labs.instalikefragmenttransaction";

    BaseFragment.FragmentNavigation mFragmentNavigation;

    EditText titulo,mensagem;
    String Sender = "Zuurc@hotmail.com";

    Button enviar_mensagem;

    ProgressDialog loading;

    private Handler mHandler;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_fale_conosco, container, false);


        titulo = (EditText) view.findViewById(R.id.titulo);
        mensagem = (EditText) view.findViewById(R.id.mensagem);
        enviar_mensagem = (Button) view.findViewById(R.id.button23);
        mHandler = new Handler();
        enviar_mensagem.setOnClickListener(new View.OnClickListener()
        {

            public void onClick(View v)
            {
                loading = ProgressDialog.show(getActivity(), "Enviando E-mail!", "Por favor, aguarde", false, false);
                new Thread(new Runnable()
                {

                    @Override
                    public void run()
                    {
                        try
                        {

                            GMailSender sender = new GMailSender("qmguiziii@gmail.com",
                                                                 "Lacoperfeito44");
                            sender.sendMail(
                                    titulo.getText().toString(),
                                    mensagem.getText().toString() +"\n"+ "\n"+"Email : " + Sender.toString()    ,
                                    "qmguiziii@gmail.com",
                                    "qmguiziii@gmail.com");
                                    loading.setMessage("Mensagem enviada!");



                        }
                        catch (Exception e)
                        {
                            Log.e("SendMail", e.getMessage(), e);
                        }

                        loading.dismiss();
                        mHandler.post(new Runnable() {
                            @Override
                            public void run()
                            {
                                Funcionou();
                                mensagem.setText("");
                                titulo.setText("");

                            }
                        });



                    }

                }).start();


            }
        });

        ButterKnife.bind(this, view);

        ( (MainActivity)getActivity()).updateToolbarTitle("Fale Conosco");

        return view;
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        if (context instanceof BaseFragment.FragmentNavigation)
        {
            mFragmentNavigation = (BaseFragment.FragmentNavigation) context;
        }
    }

    public interface FragmentNavigation
    {
        void pushFragment(Fragment fragment);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {

        new AlertDialog.Builder(getActivity())
                .setMessage("Recebemos seu e-mail com sucesso, obrigado!")
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dialog.cancel();
                    }
                })
                .show();
    }



    public void Funcionou()
    {

        new AlertDialog.Builder(getActivity())
                .setMessage("Recebemos seu e-mail com sucesso, obrigado!")
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dialog.cancel();
                    }
                })
                .show();
    }



    public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3)
    {


    }


    public void onNothingSelected(AdapterView<?> arg0)
    {


    }
    public boolean isOnline()
    {
        ConnectivityManager cm =(ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting())
        {
            return true;
        }
        return false;
    }


}
