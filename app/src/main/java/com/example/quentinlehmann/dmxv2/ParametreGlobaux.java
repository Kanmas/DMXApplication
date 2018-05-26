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

import com.example.quentinlehmann.dmxv2.Configurations.Configuration;

import java.io.IOException;

public class ParametreGlobaux extends AppCompatActivity {

    public static ParametreGlobaux currentInstance;
    private static void setCurrentInstance (ParametreGlobaux parametreGlobaux) {
        ParametreGlobaux.currentInstance = parametreGlobaux;
    }
    public static ParametreGlobaux getCurrentInstance () {
        return currentInstance;
    }

    private Configuration localeConfiguration = new Configuration();

    private EditText hostnameEditText;
    private EditText sendPortEditText;
    private EditText targetAddressEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView(R.layout.activity_parametre_globaux);
        setCurrentInstance(this);

        hostnameEditText = findViewById(R.id.editTextAddrIP);
        targetAddressEditText = findViewById(R.id.editTextAddrCible);
        sendPortEditText = findViewById(R.id.editTextPort);

        /*
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
        */
        localeConfiguration.setOnPropertyChanged(new BaseModel.PropertyChangedListener() {
            @Override
            public void OnPropertyChanged(String propertyName) {
                switch (propertyName) {
                    case Configuration.HOSTNAME:
                        //hostnameEditText.setText(localeConfiguration.getHostname().toString());
                        break;
                    case Configuration.RECEIVE_PORT:
                        break;
                    case Configuration.SEND_PORT:
                        //sendPortEditText.setText(String.valueOf(localeConfiguration.getSendPort()));
                        break;
                    case Configuration.TARGET_ADDRESS:
                        //targetAddressEditText.setText(String.valueOf(localeConfiguration.getTargetAddress()));
                        break;
                }
            }
        });

        localeConfiguration.ApplyConfiguration(Configuration.getInstance());
        hostnameEditText.setText(localeConfiguration.getHostname().toString());
        sendPortEditText.setText(String.valueOf(localeConfiguration.getSendPort()));
        targetAddressEditText.setText(String.valueOf(localeConfiguration.getTargetAddress()));


        hostnameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //ConfigurationOld.getCurrentInstance().setHostname(charSequence.toString());
                localeConfiguration.setHostname(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        targetAddressEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //ConfigurationOld.getCurrentInstance().setAddress(charSequence.toString());
                localeConfiguration.setTargetAddress(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        sendPortEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //ConfigurationOld.getCurrentInstance().setPort(charSequence.toString());
                localeConfiguration.setSendPort(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        (findViewById( R.id.btnOKParaGlobo )).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                try {
                    ConfigurationOld.getCurrentInstance().Sauvegarder();
                } catch (IOException e) {
                    Toast.makeText( getCurrentInstance(), e.getMessage(), Toast.LENGTH_LONG ).show();
                    e.printStackTrace();
                }*/

                Configuration.getInstance().ApplyConfiguration(localeConfiguration);
                Configuration.getInstance().Write();
                Toast.makeText(ParametreGlobaux.this, Configuration.getInstance().toString(), Toast.LENGTH_LONG).show();
            }
        } );
    }


}
