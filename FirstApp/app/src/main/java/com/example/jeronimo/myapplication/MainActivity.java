package com.example.jeronimo.myapplication;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private Button btnMostrar;
    private TextView txtExito;

    private ListView listAnadir;
    private EditText txtAnadir;
    private Button btnAnadir;
    private Button btnEliminar;

    ArrayList<String> listItems = new ArrayList<String>();

    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtenemos una referencia a los controles
        btnMostrar = (Button)findViewById(R.id.BtnMostrar);
        txtExito = (TextView)findViewById(R.id.TxtExito);
        listAnadir = (ListView)findViewById(R.id.ListAnadir);
        txtAnadir = (EditText)findViewById(R.id.TxtAnadir);
        btnAnadir = (Button)findViewById(R.id.BtnAnadir);
        btnEliminar = (Button)findViewById(R.id.BtnEliminar);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems);
        listAnadir.setAdapter(adapter);
        listAnadir.setChoiceMode(ListView.CHOICE_MODE_SINGLE);



        // Implementamos el evento click de los botones
        btnMostrar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View w){

                if(txtExito.getVisibility() == View.VISIBLE){
                    txtExito.setVisibility(View.INVISIBLE);
                    btnMostrar.setText("Mostrar");
                }
                else if(txtExito.getVisibility() == View.INVISIBLE){
                    txtExito.setVisibility(View.VISIBLE);
                    btnMostrar.setText("Ocultar");
                }
            }

        });

        btnAnadir.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v){
                if(txtAnadir.length() != 0){
                    adapter.add(txtAnadir.getText().toString());
                }
            }

        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                SparseBooleanArray seleccionados = listAnadir.getCheckedItemPositions();
                Boolean encontrado = false;

                if (seleccionados != null && seleccionados.size() != 0) {
                    StringBuilder resultado = new StringBuilder();
                    int i = 0;

                    while (!encontrado && i < seleccionados.size()) {
                        if (seleccionados.valueAt(i)) {
                            adapter.remove(adapter.getItem(i));
                            encontrado = true;
                        } else i++;
                    }
                }
            }

        });

    }

    public void deleteSelectedItem(View v){
        adapter.remove(listAnadir.getItemAtPosition(listAnadir.getSelectedItemPosition()).toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
