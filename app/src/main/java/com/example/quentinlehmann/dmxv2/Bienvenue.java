package com.example.quentinlehmann.dmxv2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Bienvenue extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_bienvenue );
    }

    public void Page_Changer_Couleur(View view) {
        startActivity( new Intent( this, Changer_Couleur.class ) );
    }
    public void GererSB(View view){
        startActivity( new Intent( this, GererSB.class));
    }

    public void ParametreGlobaux (View view){
        startActivity( new Intent( this, ParametreGlobaux.class) );
    }
}
