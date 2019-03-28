package com.example.readfile;

import android.content.Context;
import android.graphics.Color;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    public Spinner datos;
    public EditText texto;
    public TextView entidad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //inicializamos los componentes de la vista
        datos = (Spinner) findViewById(R.id.spinner_uno);
        texto = (EditText) findViewById(R.id.editext_telefono);
        entidad = (TextView) findViewById(R.id.txt_entidad);

        //realizamos la consulta y agregamos los datos a mostrar en el spinner mediante el ArrayAdapter
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.prueba, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        datos.setAdapter(adapter);

        // se realiza la función que queremos realizar al seleccionar cada item del spinner
        datos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //mostramos en el textview cada selección que se elige
                entidad.setText(adapter.getItem(position));
                //esto sirve para mostrar el editext de la opción OTRO
                // dependiendo el número de posición en que se encuentre
                if (parent == datos) {
                    int visible = (position == 14) ? View.VISIBLE : View.GONE;
                    texto.setVisibility(visible);

                    if (datos.getSelectedItem().toString().equals("OTRO")) {
                        texto.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {

                            }

                            @Override
                            public void afterTextChanged(Editable s) {
                                //obtener Y mostrar valor de la opción OTRO
                                entidad.setText(texto.getText());
                            }
                        });

                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
    }

}

