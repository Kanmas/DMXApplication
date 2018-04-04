package com.example.quentinlehmann.dmxv2;

import android.app.Dialog;
import android.content.ClipData;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class Changer_Couleur extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_changer__couleur );

        }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //ajoute les entrées de menu_test à l'ActionBar
        getMenuInflater().inflate(R.menu.menuh_changer_couleur, menu);
        return true;
    }


    //gère le click sur une action de l'ActionBar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case R.id.MenuChangerCouleur:
                AlertDialog.Builder mBuilder = new AlertDialog.Builder( Changer_Couleur.this );
                View mView = getLayoutInflater().inflate( R.layout.boite_dialogue_settings, null);
                mBuilder.setView( mView );
                AlertDialog dialog = mBuilder.create();
                dialog.show();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
