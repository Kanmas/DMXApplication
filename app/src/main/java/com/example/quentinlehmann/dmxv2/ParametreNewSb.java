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
import java.net.InetAddress;

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
        currentInstance = this;
            Button btn = findViewById( R.id.btnOKNewSb );
        ((EditText )findViewById( R.id.editTextPortNewSb )).setText( ConfigurationOld.getCurrentInstance().getPort() );
        ((EditText)findViewById( R.id.editTextaddrCibleNewSb )).setText( ConfigurationOld.getCurrentInstance().getAddress() );
        ((EditText)findViewById( R.id.editTextAddrIPNewsb )).setText( ConfigurationOld.getCurrentInstance().getHostname() );

        (( EditText )findViewById( R.id.editTextAddrIPNewsb )).addTextChangedListener( new TextWatcher() {
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
        } );

        ((EditText)findViewById(R.id.editTextaddrCibleNewSb)).addTextChangedListener(new TextWatcher() {
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

        ((EditText)findViewById(R.id.editTextPortNewSb)).addTextChangedListener(new TextWatcher() {
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



        btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    ConfigurationOld.getCurrentInstance().SauvegarderSB();

                    GererNewSBColor(view);
                } catch (IOException e) {

                        Toast.makeText( ParametreNewSb.getCurrentInstance(), e.getMessage(), Toast.LENGTH_LONG ).show();

                }catch (Exception e) {
                    Toast.makeText( ParametreNewSb.getCurrentInstance(), e.getMessage(), Toast.LENGTH_LONG ).show();
                }
            }

        });
        ConfigurationOld.getCurrentInstance().setOnPropertyChanged(new ConfigurationOld.PropertyChangedListener() {
            @Override
            public void OnPropertyChanged(String propertyName) {
                switch (propertyName) {
                    case "Type":
                        break;
                    case "Address":

                        try {
                            getCurrentInstance().packet.couleur.setTargetAddress( Integer.parseInt( ConfigurationOld.getCurrentInstance().getAddress() ) );
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
