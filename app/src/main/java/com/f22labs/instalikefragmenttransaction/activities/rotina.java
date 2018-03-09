package com.f22labs.instalikefragmenttransaction.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.f22labs.instalikefragmenttransaction.R;

public class rotina extends AppCompatActivity {

    Button comecar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_rotina);

        comecar = (Button) findViewById(R.id.comecar);

        comecar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });



    }

}
