package com.example.quentinlehmann.dmxv2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.quentinlehmann.dmxv2.Common.ColorWrapper;
import com.example.quentinlehmann.dmxv2.Common.StoryboardElement;
import com.example.quentinlehmann.dmxv2.JSON.Json;

public class ChangeStoryboardElement extends AppCompatActivity {


    private static final int BASE_RED_BALANCE = 127;
    private static final int BASE_GREEN_BALANCE = 127;
    private static final int BASE_BLUE_BALANCE = 127;

    private LinearLayout redLayout;
    private LinearLayout greenLayout;
    private LinearLayout blueLayout;
    private LinearLayout blendedLayout;

    private EditText timeEditText;

    private ColorWrapper wrapper = new ColorWrapper();


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_change_storyboard_element);
        Toast.makeText(this, "ChangeStoryboardElement.java", Toast.LENGTH_LONG).show();
        redLayout = findViewById( R.id.linearLayoutRedSb );
        greenLayout = findViewById( R.id.linearLayoutGreenSb );
        blueLayout = findViewById( R.id.linearLayoutBlueSb );
        blendedLayout = findViewById( R.id.linearLayoutMelangeSb );

        timeEditText = findViewById(R.id.editTextTemps);

        wrapper.setOnPropertyChanged( new BaseModel.PropertyChangedListener() {
            @Override
            public void OnPropertyChanged(String propertyName) {
                switch (propertyName) {
                    case ColorWrapper.RED:
                        redLayout.setBackgroundColor( wrapper.getRedBalance() );
                    case ColorWrapper.GREEN:
                        greenLayout.setBackgroundColor( wrapper.getGreenBalance() );
                    case ColorWrapper.BLUE:
                        blueLayout.setBackgroundColor( wrapper.getBlueBalance() );

                    blendedLayout.setBackgroundColor( wrapper.getBlendedBalance() );
                }
            }
        } );

        String json = (String)getIntent().getSerializableExtra("element");
        StoryboardElement element;
        if (json != null && !json.isEmpty()) {
            element = (StoryboardElement) Json.getInstance().Deserialize(json, StoryboardElement.class);
            wrapper.setRed(element.getRed());
            wrapper.setGreen(element.getGreen());
            wrapper.setBlue(element.getBlue());
            timeEditText.setText(String.valueOf(element.getTime()));
        } else {
            wrapper.setRed( BASE_RED_BALANCE );
            wrapper.setGreen( BASE_GREEN_BALANCE );
            wrapper.setBlue( BASE_BLUE_BALANCE );
        }




        // Initialisation du gestionnaire d'événement du champs texte du temps
        timeEditText.addTextChangedListener( new TextWatcher() {
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

        // Initialisation du gestionnaire d'événement de curseur de la couleur rouge
        ((SeekBar)findViewById( R.id.seekBarRedSb )).setOnSeekBarChangeListener( new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                wrapper.setRed( i );
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        } );

        // Initialisation du gestionnaire d'événement du curseur de la couleur bleu
        ((SeekBar)findViewById( R.id.seekBarBlueSb)).setOnSeekBarChangeListener( new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                wrapper.setBlue( i );
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        } );

        // Initialisation du gestionnaire d'événelent du curseur de la couleur vert
        ((SeekBar)findViewById( R.id.seekBarGreenSb )).setOnSeekBarChangeListener( new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                wrapper.setGreen( i );
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        } );


        // Intialisation du gestionnaire du bouton envoyer
        (findViewById( R.id.btnenvoyerCouleurSb )).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GererNewSBColor(view);
            }
        } );


    }

    // Sert à faire la liaison entre les deux fenêtre
    public void GererNewSBColor (View view){
        startActivity( new Intent( this, GererNewSBColor.class ) );
    }
}
