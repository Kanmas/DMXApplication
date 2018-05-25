package com.example.quentinlehmann.dmxv2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class GererNewSBColor extends AppCompatActivity {

    private static GererNewSBColor currentInstance;
    private static void setCurrentInstance (GererNewSBColor gererNewSBColor) {
        GererNewSBColor.currentInstance = gererNewSBColor;
    }
    public static GererNewSBColor getCurrentInstance () {
        return currentInstance;
    }

    private ColorPacket packet = new ColorPacket();

    private ListView mListView;
    private String[] prenoms = new String[]{
            "Liste de Couleur de la SB",
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        currentInstance=this;
        setContentView( R.layout.activity_gerer_new_sbcolor );

        mListView = (ListView) findViewById(R.id.ListViewNewSBCOlor);

        //android.R.layout.simple_list_item_1 est une vue disponible de base dans le SDK android,
        //Contenant une TextView avec comme identifiant "@android:id/text1"

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(GererNewSBColor.this,
                android.R.layout.simple_list_item_1, prenoms);
        mListView.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById( R.id.fab3 );
        fab.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               ChangerCouleurSB( view );
            }
        } );
    }


    public void ChangerCouleurSB (View view){
        startActivity( new Intent( this, ChangerCouleurSB.class) );
    }

}
