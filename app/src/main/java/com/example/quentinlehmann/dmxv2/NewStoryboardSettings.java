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

public class NewStoryboardSettings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_new_storyboard_settings);
        Toast.makeText(this, "NewStoryboardSettings.java", Toast.LENGTH_LONG).show();

        final Configuration localeConfiguration = new Configuration(Configuration.getInstance());


        ((EditText )findViewById( R.id.editTextPortNewSb )).setText( String.valueOf(localeConfiguration.getSendPort()) );
        ((EditText)findViewById( R.id.editTextaddrCibleNewSb )).setText( String.valueOf(localeConfiguration.getTargetAddress()) );
        ((EditText)findViewById( R.id.editTextAddrIPNewsb )).setText( (String.valueOf(localeConfiguration.getHostname().getHostAddress())) );

        (( EditText )findViewById( R.id.editTextAddrIPNewsb )).addTextChangedListener( new TextWatcher() {
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
        } );
        ((EditText)findViewById(R.id.editTextaddrCibleNewSb)).addTextChangedListener(new TextWatcher() {
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
        ((EditText)findViewById(R.id.editTextPortNewSb)).addTextChangedListener(new TextWatcher() {
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



        findViewById( R.id.btnOKNewSb ).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Configuration.getInstance().ApplyConfiguration( localeConfiguration );
                Toast.makeText( NewStoryboardSettings.this, localeConfiguration.toString(), Toast.LENGTH_LONG ).show();
                GererNewSBColor( view );
/*
                Toast.makeText(NewStoryboardSettings.this, localeConfiguration.toString(), Toast.LENGTH_LONG).show();
                Configuration.getInstance().ApplyConfiguration(localeConfiguration);
                try {
                    ConfigurationOld.getCurrentInstance().SauvegarderCC();
                } catch (IOException e) {
                    Toast.makeText(NewStoryboardSettings.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }

                try {
                    //ConfigurationOld.getCurrentInstance().SauvegarderSB();

                    GererNewSBColor(view);
                } catch (IOException e) {

                    Toast.makeText( NewStoryboardSettings.getCurrentInstance(), "Erreur 1", Toast.LENGTH_LONG ).show();

                }catch (Exception e) {
                    Toast.makeText( NewStoryboardSettings.getCurrentInstance(), "Erreur 2", Toast.LENGTH_LONG ).show();
                }
                */
            }
        });
    }
    public void GererNewSBColor (View view){
        startActivity( new Intent( this, GererNewSBColor.class ) );
    }
}
