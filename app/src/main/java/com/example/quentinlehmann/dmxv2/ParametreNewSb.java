package com.example.quentinlehmann.dmxv2;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;

public class ParametreNewSb extends AppCompatActivity {

    private static ParametreNewSb currentInstance;
    private static void setCurrentInstance (ParametreNewSb parametreNewSb) {
        ParametreNewSb.currentInstance = parametreNewSb;
    }
    public static ParametreNewSb getCurrentInstance () {
        return currentInstance;
    }

    private ColorPacket packet = new ColorPacket();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_parametre_new_sb );

            Button btn = findViewById( R.id.btnOKNewSb );
        ((EditText )findViewById( R.id.editTextPortNewSb )).setText( Configuration.getCurrentInstance().getPort() );
        ((EditText)findViewById( R.id.editTextaddrCibleNewSb )).setText( Configuration.getCurrentInstance().getAddress() );
        ((EditText)findViewById( R.id.editTextAddrIPNewsb )).setText( Configuration.getCurrentInstance().getHostname() );

        (( EditText )findViewById( R.id.editTextAddrIPNewsb )).addTextChangedListener( new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Configuration.getCurrentInstance().setHostname(charSequence.toString());
                Toast.makeText(ParametreNewSb.getCurrentInstance() ,  "IPOK", Toast.LENGTH_LONG).show();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        } );

        ((EditText)findViewById(R.id.editTextaddrCibleNewSb)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Configuration.getCurrentInstance().setAddress(charSequence.toString());
                Toast.makeText(ParametreNewSb.getCurrentInstance() ,  "addrCibleoK", Toast.LENGTH_LONG).show();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        ((EditText)findViewById(R.id.editTextPortNewSb)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Configuration.getCurrentInstance().setPort(charSequence.toString());
                Toast.makeText(ParametreNewSb.getCurrentInstance() ,  "port ok", Toast.LENGTH_LONG).show();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    Configuration.getCurrentInstance().SauvegarderSB();
                    GererNewSBColor(view);
                } catch (IOException e) {
                    Toast.makeText(getCurrentInstance() ,  e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

        });
        Configuration.getCurrentInstance().setOnPropertyChanged(new Configuration.PropertyChangedListener() {
            @Override
            public void OnPropertyChanged(String propertyName) {
                switch (propertyName) {
                    case "Type":
                        break;
                    case "Address":
                        try {
                            getCurrentInstance().packet.couleur.setTargetAddress( Integer.parseInt( Configuration.getCurrentInstance().getAddress() ) );
                        } finally {
                            getCurrentInstance().packet.couleur.setTargetAddress( 0 );
                        }

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

        }
    public void GererNewSBColor (View view){
        startActivity( new Intent( this, GererNewSBColor.class ) );
    }
}
