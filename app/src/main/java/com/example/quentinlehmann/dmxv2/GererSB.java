package com.example.quentinlehmann.dmxv2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class GererSB extends AppCompatActivity {

    private RecyclerView rcStoryboardList;
    private ArrayList<Storyboard> arrayList = new ArrayList<>(  );
    private StoryboardRecyclerViewAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_gerer_sb );

        rcStoryboardList = findViewById( R.id.rcStoryboardList );

        arrayList.addAll( Storyboard.fake(10) );

        recyclerView.setLayoutManager( new LinearLayoutManager( this ) );
        adapter = new StoryboardRecyclerViewAdapter( this, arrayList );

        adapter.setmClickListener( new StoryboardRecyclerViewAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText( GererSB.this, adapter.getItem( position ).getName(), Toast.LENGTH_SHORT ).show();
            }
        } );

        recyclerView.setAdapter( adapter );

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
