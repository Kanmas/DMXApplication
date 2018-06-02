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
    private static final double BASE_TIME = 1.0;

    private LinearLayout redLayout;
    private LinearLayout greenLayout;
    private LinearLayout blueLayout;
    private LinearLayout blendedLayout;

    private SeekBar redSeekbar;
    private SeekBar greenSeekbar;
    private SeekBar blueSeekbar;

    private EditText timeEditText;

    private ColorWrapper wrapper = new ColorWrapper();
    private StoryboardElement storyboardElement = new StoryboardElement(BASE_RED_BALANCE, BASE_GREEN_BALANCE, BASE_GREEN_BALANCE, BASE_TIME);

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_change_storyboard_element);
        Toast.makeText(this, "ChangeStoryboardElement.java", Toast.LENGTH_LONG).show();

        // initialisation des layouts
        redLayout = findViewById( R.id.linearLayoutRedSb );
        greenLayout = findViewById( R.id.linearLayoutGreenSb );
        blueLayout = findViewById( R.id.linearLayoutBlueSb );
        blendedLayout = findViewById( R.id.linearLayoutMelangeSb );

        // initilisation des curseurs
        redSeekbar = findViewById( R.id.seekBarRedSb );
        greenSeekbar = findViewById( R.id.seekBarGreenSb );
        blueSeekbar = findViewById( R.id.seekBarBlueSb);

        // initilisation du champs texte pour le temps
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

        if (json != null && !json.isEmpty()) {
            storyboardElement = (StoryboardElement) Json.getInstance().Deserialize(json, StoryboardElement.class);
            wrapper.setRed(storyboardElement.getRed());
            wrapper.setGreen(storyboardElement.getGreen());
            wrapper.setBlue(storyboardElement.getBlue());
            timeEditText.setText(String.valueOf(storyboardElement.getTime()));
            redSeekbar.setProgress(storyboardElement.getRed());
            greenSeekbar.setProgress(storyboardElement.getGreen());
            blueSeekbar.setProgress(storyboardElement.getBlue());
        } else {
            wrapper.setRed( BASE_RED_BALANCE );
            wrapper.setGreen( BASE_GREEN_BALANCE );
            wrapper.setBlue( BASE_BLUE_BALANCE );
            redSeekbar.setProgress(BASE_RED_BALANCE);
            greenSeekbar.setProgress(BASE_GREEN_BALANCE);
            blueSeekbar.setProgress(BASE_BLUE_BALANCE);
        }

        // Initialisation du gestionnaire d'événement du champs texte du temps
        timeEditText.addTextChangedListener( new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    storyboardElement.setTime(Double.parseDouble(charSequence.toString()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        } );

        // Initialisation du gestionnaire d'événement de curseur de la couleur rouge
        redSeekbar.setOnSeekBarChangeListener( new SeekBar.OnSeekBarChangeListener() {

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
        blueSeekbar.setOnSeekBarChangeListener( new SeekBar.OnSeekBarChangeListener() {

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
        greenSeekbar.setOnSeekBarChangeListener( new SeekBar.OnSeekBarChangeListener() {

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
        findViewById( R.id.btnenvoyerCouleurSb ).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        } );


    }
}
