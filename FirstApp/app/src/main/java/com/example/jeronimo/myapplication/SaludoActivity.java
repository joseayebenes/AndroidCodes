package com.example.jeronimo.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class SaludoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        TextView txtSaludo;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saludo);

        // Localizar los controles
        txtSaludo = (TextView)findViewById(R.id.TxtSaludo);

        // Recuperamos la informacion pasada en el intent
        Bundle bundle = this.getIntent().getExtras();

        // Construimos el mensaje a mostrar
        txtSaludo.setText("Hola " + bundle.getString("NOMBRE"));

        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

}
