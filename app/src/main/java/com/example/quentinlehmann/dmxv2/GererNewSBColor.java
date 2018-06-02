package com.example.quentinlehmann.dmxv2;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.quentinlehmann.dmxv2.Configurations.Configuration;
import com.example.quentinlehmann.dmxv2.JSON.StoryboardPacket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Activité de gestion de couleur pour les nouvelles storyboards
 */
public class GererNewSBColor extends AppCompatActivity {

    private static GererNewSBColor currentInstance;

    private ArrayList<StoryboardElement> arrayList = new ArrayList<StoryboardElement>();
    private MyRecyclerViewAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_gerer_new_sbcolor );
        Toast.makeText(this, "GererNewSBColor.java", Toast.LENGTH_LONG).show();
        setCurrentInstance( this );
        recyclerView = findViewById( R.id.rcElementList );


        arrayList.addAll( StoryboardElement.fake(10) );

        recyclerView.setLayoutManager( new LinearLayoutManager( this ) );
        adapter = new MyRecyclerViewAdapter( this, arrayList);



        adapter.setClickListener( new MyRecyclerViewAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText( GererNewSBColor.this, "You clicked" + adapter.getItem( position ), Toast.LENGTH_SHORT ).show();
            }
        } );

        recyclerView.setAdapter( adapter );

        FloatingActionButton fab = (FloatingActionButton) findViewById( R.id.fab3 );
        fab.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               ChangerCouleurSB( view );
            }
        } );

        ((Button)findViewById( R.id.btnPlay )).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    StoryboardPacket packet = new StoryboardPacket();
                    StoryboardElement[] storyboardElements = new StoryboardElement[adapter.getmData().size()];

                    packet.storyboard.elements = adapter.getmData().toArray(storyboardElements);


                    String json = Json.getInstance().Serialize( packet );
                    Toast.makeText( GererNewSBColor.this, json, Toast.LENGTH_SHORT ).show();
                    NetworkManager.getInstance().SendFragment( json, Configuration.getInstance().getHostname(), Configuration.getInstance().getSendPort() );
                } catch (Exception ex) {

                    Toast.makeText( GererNewSBColor.this, ex.getMessage(), Toast.LENGTH_SHORT ).show();
                }
            }
        } );
    }


    public void ChangerCouleurSB (View view){
        startActivity( new Intent( this, ChangerCouleurSB.class) );
    }

    private static void setCurrentInstance (GererNewSBColor gererNewSBColor) {
        GererNewSBColor.currentInstance = gererNewSBColor;
    }
    public static GererNewSBColor getCurrentInstance () {
        return currentInstance;
    }
}
