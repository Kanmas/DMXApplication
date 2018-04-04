package com.example.quentinlehmann.dmxv2;

import android.content.Intent;
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
import java.net.Inet4Address;

public class ParametreGlobaux extends AppCompatActivity implements AdapterView.OnItemSelectedListener, ModeleParametreGlobaux.IEvent {

    public EditText editTextAddrCible;
    public EditText editTextPort;
    public EditText editTextAddrIP;
    public Spinner spinerTypeCible;
    public Button btnAnnuler;
    public Button btnOk;
    public ModeleParametreGlobaux MPG;
    private static final String[] TypeCible = {"Barre LED", "Spot"};

    public static ParametreGlobaux param;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        MPG = new ModeleParametreGlobaux();
        MPG.subs.add( this );
        setContentView( R.layout.activity_parametre_globaux );

        editTextAddrCible = ( EditText ) findViewById( R.id.editTextAddrCible );
        spinerTypeCible = ( Spinner ) findViewById( R.id.SpinerTypeCibleParaGlobo );
        editTextPort = ( EditText ) findViewById( R.id.editTextPort );
        editTextAddrIP = ( EditText ) findViewById( R.id.editTextAddrIP );
        btnAnnuler = ( Button ) findViewById( R.id.btnAnnulerGlobaux );
        btnOk = ( Button ) findViewById( R.id.btnOKParaGlobo );

        btnAnnuler.setOnClickListener( annuleAction );
        btnOk.setOnClickListener( valideAction );

        ArrayAdapter<String> TCible=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,TypeCible);
        spinerTypeCible.setAdapter(TCible);
        spinerTypeCible.setOnItemSelectedListener( this );

        param = this;
    }

    private View.OnClickListener annuleAction = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ParametreGlobaux( view );
        }
    };

    public void onPropertyChange(String nom) {

        switch (nom)
        {
            case "Port":
                editTextPort.addTextChangedListener( new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                } );
                break;
            case "Hostname":
                editTextAddrIP.addTextChangedListener( new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        try {
                            Inet4Address ip = ( Inet4Address ) Inet4Address.getByName(editTextAddrIP.getText().toString());
                        } catch (Exception e) {
                            Toast.makeText(ParametreGlobaux.this, "Non valide pendant que ca change", Toast.LENGTH_LONG );
                        }

                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                        try {
                            Inet4Address ip = ( Inet4Address ) Inet4Address.getByName(editTextAddrIP.getText().toString());
                        } catch (Exception e) {
                            Toast.makeText(ParametreGlobaux.this, "Non valide", Toast.LENGTH_LONG );
                        }

                    }
                } );
                break;

            case "Type":
                ArrayAdapter<String> TCible = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, TypeCible);
                spinerTypeCible.setAdapter(TCible);
                spinerTypeCible.setOnItemSelectedListener( this );
                break;

            case "Adress":
                editTextAddrCible.addTextChangedListener( new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        try
                        {
                            MPG.setAdress( charSequence.toString());
                        }catch (NumberFormatException e)
                        {
                            Toast.makeText(ParametreGlobaux.this, "Rentre un nombre", Toast.LENGTH_LONG );
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                } );

        }

    }

    private View.OnClickListener valideAction = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(ParametreGlobaux.param ,  Json.Serialize( new ModeleParametreGlobaux()), Toast.LENGTH_LONG).show();
        }
    };

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void ParametreGlobaux (View view){
        startActivity( new Intent( this, Bienvenue.class) );
    }
}
