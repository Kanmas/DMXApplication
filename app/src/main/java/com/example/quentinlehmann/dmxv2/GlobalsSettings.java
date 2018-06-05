package com.example.quentinlehmann.dmxv2;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quentinlehmann.dmxv2.Configurations.Configuration;

/**
 * Activité de changement des paramètres globaux de l'application
 */
public class GlobalsSettings extends AppCompatActivity {

    /**
     * Configuration locale
     */
    private Configuration localeConfiguration = new Configuration();

    /**
     * Champs texte pour l'adresse IP
     */
    private EditText hostnameEditText;
    /**
     * Champs texte pour le port d'envoi
     */
    private EditText sendPortEditText;
    /**
     * Champs texte pour l'adresse de la cible
     */
    private EditText targetAddressEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView(R.layout.activity_globals_settings);

        //Toast.makeText(this, "GlobalsSettings.java", Toast.LENGTH_LONG).show();

        // initialisation des champs textes
        hostnameEditText = findViewById(R.id.editTextAddrIP);
        targetAddressEditText = findViewById(R.id.editTextAddrCible);
        sendPortEditText = findViewById(R.id.editTextPort);

        // initialisation des callbacks
        localeConfiguration.setOnPropertyChanged(new BaseModel.PropertyChangedListener() {
            @Override
            public void OnPropertyChanged(String propertyName) {
                switch (propertyName) {
                    case Configuration.HOSTNAME:
                        break;
                    case Configuration.RECEIVE_PORT:
                        break;
                    case Configuration.SEND_PORT:
                        break;
                    case Configuration.TARGET_ADDRESS:
                        break;
                }
            }
        });

        // appliquation de la configuration globale sur cette configuration
        localeConfiguration.ApplyConfiguration(Configuration.getInstance());
        // initialisation des valeurs des champs textes
        hostnameEditText.setText(String.valueOf(localeConfiguration.getHostname().getHostAddress()));
        sendPortEditText.setText(String.valueOf(localeConfiguration.getSendPort()));
        targetAddressEditText.setText(String.valueOf(localeConfiguration.getTargetAddress()));

        // initalisation du gestionnaire d'événement du champs texte
        hostnameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
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
                localeConfiguration.setSendPort(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        // initialisation du gestionnaire d'événement du bouton
        (findViewById( R.id.btnOKParaGlobo )).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Configuration.getInstance().ApplyConfiguration(localeConfiguration);
                Configuration.setInstance( localeConfiguration );
                Configuration.getInstance().Write(GlobalsSettings.this);
                Toast.makeText(GlobalsSettings.this, "Sauvegarder", Toast.LENGTH_LONG).show();
                Welcome(view);
            }
        } );

        findViewById(R.id.btnAnnulerGlobaux).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Welcome(view);
            }
        });
    }

    public void Welcome (View view) {
        startActivity(new Intent(this, Welcome.class));
    }
}
