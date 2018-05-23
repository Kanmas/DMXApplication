package com.example.quentinlehmann.dmxv2;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Toast;

import java.net.InetAddress;

public class ChangerCouleurSB extends AppCompatActivity {

    private static ChangerCouleurSB currentInstance;
    private static void setCurrentInstance (ChangerCouleurSB changer_couleurSB) {
        ChangerCouleurSB.currentInstance = changer_couleurSB;
    }
    public static ChangerCouleurSB getCurrentInstance () {
        return currentInstance;
    }

    private ColorPacket packet = new ColorPacket();
    public String tmps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_changer_couleur_sb );
        currentInstance = this;

        ((LinearLayout )findViewById( R.id.linearLayoutRedSb )).setBackgroundColor( Color.rgb( 127, 0,0 ) );
        ((LinearLayout)findViewById( R.id.linearLayoutGreenSb )).setBackgroundColor( Color.rgb( 0, 127,0 ) );
        ((LinearLayout)findViewById( R.id.linearLayoutBlueSb )).setBackgroundColor( Color.rgb(0 , 0,127 ) );
        ((LinearLayout)findViewById( R.id.linearLayoutMelangeSb )).setBackgroundColor( Color.rgb(127 , 127,127 ) );
        ((EditText)findViewById( R.id.editTextTemps )).setText( tmps );

        ((SeekBar )findViewById( R.id.seekBarRedSb )).setOnSeekBarChangeListener( new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                ((LinearLayout)findViewById( R.id.linearLayoutRedSb)).setBackgroundColor( Color.rgb( seekBar.getProgress(), 0, 0 ) );
                ChangerLayoutMelange();
                getCurrentInstance().packet.getCouleur().setRed( seekBar.getProgress() );
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        } );

        ((SeekBar)findViewById( R.id.seekBarBlueSb)).setOnSeekBarChangeListener( new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                ((LinearLayout)findViewById( R.id.linearLayoutBlueSb)).setBackgroundColor( Color.rgb(0, 0, seekBar.getProgress() ) );
                ChangerLayoutMelange();
                getCurrentInstance().packet.getCouleur().setBlue( seekBar.getProgress() );
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        } );

        ((SeekBar)findViewById( R.id.seekBarGreenSb )).setOnSeekBarChangeListener( new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                ((LinearLayout)findViewById( R.id.linearLayoutGreenSb)).setBackgroundColor( Color.rgb( 0, seekBar.getProgress(), 0 ) );
                ChangerLayoutMelange();
                getCurrentInstance().packet.getCouleur().setGreen( seekBar.getProgress() );
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        } );

        ((Button )findViewById( R.id.btnenvoyerCouleurSb )).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Json.getInstance().Serialize( getCurrentInstance().packet ).toString();
                Toast.makeText( ChangerCouleurSB.getCurrentInstance(), "Sauvegarder",Toast.LENGTH_LONG ).show();
                GererNewSBColor(view);

            }
        } );


    }

    public void ChangerLayoutMelange (){
        ((LinearLayout)findViewById( R.id.linearLayoutMelangeSb )).setBackgroundColor( Color.rgb( (( SeekBar ) findViewById( R.id.seekBarRedSb )).getProgress(),
                (( SeekBar ) findViewById( R.id.seekBarGreenSb )).getProgress(),
                (( SeekBar ) findViewById( R.id.seekBarBlueSb )).getProgress()));
    }

    public void GererNewSBColor (View view){
        startActivity( new Intent( this, GererNewSBColor.class ) );
    }
}
