package com.example.quentinlehmann.dmxv2;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.quentinlehmann.dmxv2.JSON.PacketConstructor;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class ChangerCouleurSB extends AppCompatActivity {


    private static final int BASE_RED_BALANCE = 127;
    private static final int BASE_GREEN_BALANCE = 127;
    private static final int BASE_BLUE_BALANCE = 127;

    private static ChangerCouleurSB currentInstance;


    private LinearLayout redLayout;
    private LinearLayout greenLayout;
    private LinearLayout blueLayout;
    private LinearLayout blendedLayout;

    private ColorWrapper wrapper = new ColorWrapper();


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_changer_couleur_sb );
        currentInstance = this;

        redLayout = findViewById( R.id.linearLayoutRedSb );
        greenLayout = findViewById( R.id.linearLayoutGreenSb );
        blueLayout = findViewById( R.id.linearLayoutBlueSb );
        blendedLayout = findViewById( R.id.linearLayoutMelangeSb );

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

        wrapper.setRed( BASE_RED_BALANCE );
        wrapper.setGreen( BASE_GREEN_BALANCE );
        wrapper.setBlue( BASE_BLUE_BALANCE );


        // Initialisation du gestionnaire d'événement du champs texte du temps
        ((EditText)findViewById( R.id.editTextTemps )).addTextChangedListener( new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //getCurrentInstance().sbe.setTime ( Integer.parseInt( charSequence.toString()) );
                //getCurrentInstance().storyboardElement.setTime( Double.parseDouble( charSequence.toString() ) );
                //Toast.makeText( ChangerCouleurSB.getCurrentInstance(), charSequence.toString(),Toast.LENGTH_LONG ).show();
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
                //((LinearLayout)findViewById( R.id.linearLayoutRedSb)).setBackgroundColor( Color.rgb( seekBar.getProgress(), 0, 0 ) );
                //ChangerLayoutMelange();
                //getCurrentInstance().sbe.setRed( seekBar.getProgress() );
                //getCurrentInstance().storyboardElement.setRed( seekBar.getProgress() );
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
                ((LinearLayout)findViewById( R.id.linearLayoutBlueSb)).setBackgroundColor( Color.rgb(0, 0, seekBar.getProgress() ) );
                //ChangerLayoutMelange();
                //getCurrentInstance().sbe.setBlue( seekBar.getProgress() );
                //getCurrentInstance().storyboardElement.setBlue( seekBar.getProgress() );
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

                //((LinearLayout)findViewById( R.id.linearLayoutGreenSb)).setBackgroundColor( Color.rgb( 0, seekBar.getProgress(), 0 ) );
                //ChangerLayoutMelange();
                //getCurrentInstance().sbe.setGreen( seekBar.getProgress() );
                //getCurrentInstance().storyboardElement.setGreen( seekBar.getProgress() );
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        } );


        // Intialisation du gestionnaire du bouton envoyer
        ((Button )findViewById( R.id.btnenvoyerCouleurSb )).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //GererNewSBColor.getCurrentInstance().arrayList.add(  )

                //final ArrayAdapter<String[]> adapter = new ArrayAdapter<String[]>(GererNewSBColor.getCurrentInstance(),
                //        android.R.layout.simple_list_item_1, GererNewSBColor.getCurrentInstance().arrayList);
                //String[] chaine = new String[]{ "RED :"+String.valueOf( wrapper.getRed() )+"BLUE :"+ String.valueOf( wrapper.getBlue() )+ "GREEN :"+String.valueOf( wrapper.getGreen() )};
                //GererNewSBColor.getCurrentInstance().arrayList.add(chaine);
                GererNewSBColor(view);
            }
        } );


    }

    // Sert à changer le carré représentant les mélanges de couleurs
    public void ChangerLayoutMelange (){
        ((LinearLayout)findViewById( R.id.linearLayoutMelangeSb )).setBackgroundColor( Color.rgb( (( SeekBar ) findViewById( R.id.seekBarRedSb )).getProgress(),
                (( SeekBar ) findViewById( R.id.seekBarGreenSb )).getProgress(),
                (( SeekBar ) findViewById( R.id.seekBarBlueSb )).getProgress()));
    }






    // Sert à faire la liaison entre les deux fenêtre
    public void GererNewSBColor (View view){
        startActivity( new Intent( this, GererNewSBColor.class ) );
    }

    /**
     * Singleton
     * @param changer_couleurSB
     */
    private static void setCurrentInstance (ChangerCouleurSB changer_couleurSB) {
        ChangerCouleurSB.currentInstance = changer_couleurSB;
    }

    /**
     * Singleton
     * @return currentInstace
     */
    public static ChangerCouleurSB getCurrentInstance () {
        return currentInstance;
    }
}
