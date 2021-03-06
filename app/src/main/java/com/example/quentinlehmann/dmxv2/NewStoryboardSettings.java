package com.example.quentinlehmann.dmxv2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quentinlehmann.dmxv2.Common.Storyboard;
import com.example.quentinlehmann.dmxv2.Configurations.Configuration;
import com.example.quentinlehmann.dmxv2.JSON.Json;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewStoryboardSettings extends AppCompatActivity {

    public static final String IP_ADDRESS_PATTERN = "^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";

    private boolean isHostnameValid = true;

    EditText portEditText;
    EditText targetAddressEditText;
    EditText hostnameEditText;
    EditText storyboardNameEditText;

    Storyboard newStoryBoard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_new_storyboard_settings);
        ////Toast.makeText(this, "NewStoryboardSettings.java", Toast.LENGTH_LONG).show();

        final Configuration localeConfiguration = new Configuration(Configuration.getInstance());

        newStoryBoard = new Storyboard();

        portEditText = findViewById(R.id.editTextPortNewSb);
        targetAddressEditText = findViewById( R.id.editTextaddrCibleNewSb );
        hostnameEditText = findViewById( R.id.editTextAddrIPNewsb );
        storyboardNameEditText = findViewById(R.id.newStoryboardName);

        portEditText.setText( String.valueOf(localeConfiguration.getSendPort()) );
        targetAddressEditText.setText( String.valueOf(localeConfiguration.getTargetAddress()) );
        hostnameEditText.setText( (String.valueOf(localeConfiguration.getHostname().getHostAddress())) );
        storyboardNameEditText.setText("");

        hostnameEditText.addTextChangedListener( new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                isHostnameValid = localeConfiguration.setHostname(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        } );
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
        portEditText.addTextChangedListener(new TextWatcher() {
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
        storyboardNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                newStoryBoard.setName(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        findViewById( R.id.btnOKNewSb ).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Pattern p = Pattern.compile( IP_ADDRESS_PATTERN );
                Matcher m = p.matcher(localeConfiguration.getHostname().toString());

                    if (newStoryBoard.getName() != null && !newStoryBoard.getName().isEmpty()) {
                        if(isHostnameValid) {
                            Toast.makeText( NewStoryboardSettings.this, "Sauvegarder", Toast.LENGTH_LONG ).show();
                            Configuration.getInstance().ApplyConfiguration( localeConfiguration );
                            Configuration.getInstance().Write( NewStoryboardSettings.this );
                        if (newStoryBoard.checkName( NewStoryboardSettings.this )) {
                            Configuration.getInstance().ApplyConfiguration( localeConfiguration );
                            //Toast.makeText( NewStoryboardSettings.this, localeConfiguration.toString(), Toast.LENGTH_LONG ).show();
                            HandleStoryboardColor( view );
                        } else {
                            Toast.makeText( NewStoryboardSettings.this, "Name already taken", Toast.LENGTH_SHORT ).show();
                        }
                    } else {
                            Toast.makeText( NewStoryboardSettings.this, "Veuillez entrer une adresse valide", Toast.LENGTH_SHORT ).show();
                        }
                }else {
                        Toast.makeText( NewStoryboardSettings.this, "Veuillez nommer votre Storyboard", Toast.LENGTH_SHORT ).show();
                    }


            }
        });

        findViewById(R.id.btnCancelNewStoryboard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HandleStoryboard(view);
            }
        });

    }

    public void HandleStoryboardColor (View view) {
        Intent intent = new Intent(this, HandleStoryboardColor.class);
        //String json = Json.getInstance().Serialize(newStoryBoard);
        //intent.putExtra("Storyboard", json);
        Storyboard.setCurrentInstance(newStoryBoard);
        startActivity(intent);
    }

    public void HandleStoryboard (View view) {
        startActivity(new Intent(this, HandleStoryboard.class));
    }
}
