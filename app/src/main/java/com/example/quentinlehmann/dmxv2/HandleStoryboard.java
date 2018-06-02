package com.example.quentinlehmann.dmxv2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.quentinlehmann.dmxv2.Common.Storyboard;
import com.example.quentinlehmann.dmxv2.JSON.Json;

import java.util.ArrayList;

public class HandleStoryboard extends AppCompatActivity {

    private RecyclerView rcStoryboardList;
    private ArrayList<Storyboard> arrayList = new ArrayList<>(  );
    private StoryboardRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_handle_storyboard);
        Toast.makeText(this, "HandleStoryboard.java", Toast.LENGTH_LONG).show();

        rcStoryboardList = findViewById( R.id.rcStoryboardList );

        arrayList.addAll( Storyboard.fake(10) );

        rcStoryboardList.setLayoutManager( new LinearLayoutManager( this ) );
        adapter = new StoryboardRecyclerViewAdapter( this, arrayList );

        adapter.setClickListener( new StoryboardRecyclerViewAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                try {

                    Toast.makeText( HandleStoryboard.this, "You clicked: " + adapter.getItem(position).getName(), Toast.LENGTH_SHORT ).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                GererCouleurSB(view, adapter.getItem(position));
            }
        } );

        rcStoryboardList.setAdapter( adapter );



        FloatingActionButton fab = findViewById( R.id.fab );
        fab.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParametreNewSb(view);
            }
        } );
    }

    public void GererCouleurSB (View view, Storyboard storyboard){
        Intent intent = new Intent(this, HandleStoryboardColor.class);
        intent.putExtra("Storyboard", Json.getInstance().Serialize(storyboard));
        startActivity( intent );
    }

    public void ParametreNewSb (View view){
        startActivity( new Intent( this, NewStoryboardSettings.class) );
    }

}
