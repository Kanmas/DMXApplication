package com.example.quentinlehmann.dmxv2;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;

public class ParametreGlobaux extends AppCompatActivity {

    public static ParametreGlobaux currentInstance;
    private static void setCurrentInstance (ParametreGlobaux parametreGlobaux) {
        ParametreGlobaux.currentInstance = parametreGlobaux;
    }
    public static ParametreGlobaux getCurrentInstance () {
        return currentInstance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView(R.layout.activity_parametre_globaux);
        setCurrentInstance(this);

        ConfigurationOld.getCurrentInstance().setOnPropertyChanged(new ConfigurationOld.PropertyChangedListener() {
            @Override
            public void OnPropertyChanged(String propertyName) {
                switch (propertyName) {
                    case "Type":
                        break;
                    case "Address":
                        break;
                    case "Port":
                        break;
                    case "Hostname":
                        break;
                    default:
                        break;
                }
            }
        });

        ((EditText)findViewById(R.id.editTextAddrIP)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                ConfigurationOld.getCurrentInstance().setHostname(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        ((EditText) findViewById(R.id.editTextAddrCible)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                ConfigurationOld.getCurrentInstance().setAddress(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        ((EditText)findViewById(R.id.editTextPort)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                ConfigurationOld.getCurrentInstance().setPort(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        (findViewById( R.id.btnOKParaGlobo )).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    ConfigurationOld.getCurrentInstance().Sauvegarder();
                } catch (IOException e) {
                    Toast.makeText( getCurrentInstance(), e.getMessage(), Toast.LENGTH_LONG ).show();
                    e.printStackTrace();
                }
            }
        } );
    }


}
