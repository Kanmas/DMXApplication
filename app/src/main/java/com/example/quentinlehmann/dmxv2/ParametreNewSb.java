package com.example.quentinlehmann.dmxv2;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class ParametreNewSb extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_parametre_new_sb );

            Button btn = findViewById( R.id.btnOKNewSb );
        btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GererNewSBColor(view);
            }
        } );
        }
    public void GererNewSBColor (View view){
        startActivity( new Intent( this, GererNewSBColor.class ) );
    }
}
