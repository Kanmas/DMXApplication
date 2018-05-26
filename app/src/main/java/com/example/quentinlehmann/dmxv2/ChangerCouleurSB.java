package com.example.quentinlehmann.dmxv2;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Toast;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class ChangerCouleurSB extends AppCompatActivity {

    private static ChangerCouleurSB currentInstance;
    private static void setCurrentInstance (ChangerCouleurSB changer_couleurSB) {
        ChangerCouleurSB.currentInstance = changer_couleurSB;
    }
    public static ChangerCouleurSB getCurrentInstance () {
        return currentInstance;
    }

    private StoryboardElement storyboardElement = new StoryboardElement();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_changer_couleur_sb );
        currentInstance = this;

        // Initialisation des fonds des carrés de couleurs dans la vue
        ((LinearLayout )findViewById( R.id.linearLayoutRedSb )).setBackgroundColor( Color.rgb( 127, 0,0 ) ); // rouge
        ((LinearLayout)findViewById( R.id.linearLayoutGreenSb )).setBackgroundColor( Color.rgb( 0, 127,0 ) ); // vert
        ((LinearLayout)findViewById( R.id.linearLayoutBlueSb )).setBackgroundColor( Color.rgb(0 , 0,127 ) ); // bleu
        ((LinearLayout)findViewById( R.id.linearLayoutMelangeSb )).setBackgroundColor( Color.rgb(127 , 127,127 ) ); // melanger

        // Initialisation du gestionnaire d'événement du champs texte du temps
        ((EditText)findViewById( R.id.editTextTemps )).addTextChangedListener( new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //getCurrentInstance().sbe.setTime ( Integer.parseInt( charSequence.toString()) );
                getCurrentInstance().storyboardElement.setTime( Double.parseDouble( charSequence.toString() ) );
                Toast.makeText( ChangerCouleurSB.getCurrentInstance(), charSequence.toString(),Toast.LENGTH_LONG ).show();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        } );

        // Initialisation du gestionnaire d'événement de curseur de la couleur rouge
        ((SeekBar )findViewById( R.id.seekBarRedSb )).setOnSeekBarChangeListener( new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                ((LinearLayout)findViewById( R.id.linearLayoutRedSb)).setBackgroundColor( Color.rgb( seekBar.getProgress(), 0, 0 ) );
                ChangerLayoutMelange();
                //getCurrentInstance().sbe.setRed( seekBar.getProgress() );
                getCurrentInstance().storyboardElement.setRed( seekBar.getProgress() );
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
                ((LinearLayout)findViewById( R.id.linearLayoutBlueSb)).setBackgroundColor( Color.rgb(0, 0, seekBar.getProgress() ) );
                ChangerLayoutMelange();
                //getCurrentInstance().sbe.setBlue( seekBar.getProgress() );
                getCurrentInstance().storyboardElement.setBlue( seekBar.getProgress() );
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
                ((LinearLayout)findViewById( R.id.linearLayoutGreenSb)).setBackgroundColor( Color.rgb( 0, seekBar.getProgress(), 0 ) );
                ChangerLayoutMelange();
                //getCurrentInstance().sbe.setGreen( seekBar.getProgress() );
                getCurrentInstance().storyboardElement.setGreen( seekBar.getProgress() );
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

                int targetAddress = 0;
                try {
                    targetAddress = Integer.parseInt( ConfigurationOld.getCurrentInstance().getAddress() );
                } catch (Exception ex){
                    Toast.makeText( getCurrentInstance(), "Finally", Toast.LENGTH_SHORT ).show();
                    targetAddress = 0;
                }

                //packet.couleur.setTargetAddress( targetAddress );

                Toast.makeText(getCurrentInstance(), "Enregistrer", Toast.LENGTH_SHORT).show();
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

    public StoryboardElement getStoryboardElement() {
        return storyboardElement;
    }

    public void setStoryboardElement(StoryboardElement storyboardElement) {
        this.storyboardElement = storyboardElement;
    }
}
