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

public class GererSB extends AppCompatActivity {

    private ListView mListView;
    private String[] prenoms = new String[]{
            "Liste differentes SB", "Benoit", "Cyril", "David", "Eloise", "Florent",
            "Gerard", "Hugo", "Ingrid", "Jonathan", "Kevin", "Logan",
            "Mathieu", "Noemie", "Olivia", "Philippe", "Quentin", "Romain",
            "Sophie", "Tristan", "Ulric", "Vincent", "Willy", "Xavier",
            "Yann", "Bonjour"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_gerer_sb );



        mListView = (ListView) findViewById(R.id.ListViewGererSB);

        //android.R.layout.simple_list_item_1 est une vue disponible de base dans le SDK android,
        //Contenant une TextView avec comme identifiant "@android:id/text1"

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(GererSB.this,
                android.R.layout.simple_list_item_1, prenoms);
        mListView.setAdapter(adapter);

        FloatingActionButton fab = findViewById( R.id.fab );
        fab.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParametreNewSb(view);
            }
        } );
    }

    public void GererCouleurSB (View view){
        startActivity( new Intent( this, GererCouleurSB.class) );
    }

    public void ParametreNewSb (View view){
        startActivity( new Intent( this, ParametreNewSb.class) );
    }

}
