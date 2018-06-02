package com.example.quentinlehmann.dmxv2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.quentinlehmann.dmxv2.Common.Storyboard;
import com.example.quentinlehmann.dmxv2.Common.StoryboardElement;
import com.example.quentinlehmann.dmxv2.JSON.Json;

/**
 * Activité de gestion des couleurs d'une storyboard
 * Il s'agit de la liste de couleur
 */
public class HandleStoryboardColor extends AppCompatActivity {

    private MyRecyclerViewAdapter myRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_handle_storyboard_color);

        Toast.makeText(this, "HandleStoryboardColor.java", Toast.LENGTH_LONG).show();

        String serializedStoryboard = (String)getIntent().getSerializableExtra("Storyboard");
        Storyboard storyboard = (Storyboard) Json.getInstance().Deserialize(serializedStoryboard, Storyboard.class);

        Toast.makeText(HandleStoryboardColor.this, storyboard.toString(), Toast.LENGTH_LONG).show();

        RecyclerView rc = findViewById(R.id.rcGererCouleurSB);
        rc.setLayoutManager(new LinearLayoutManager(this));
        myRecyclerViewAdapter = new MyRecyclerViewAdapter(this, storyboard.getStoryboardElements());
        myRecyclerViewAdapter.setClickListener(new MyRecyclerViewAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ChangerCouleurSB(view, myRecyclerViewAdapter.getItem(position));
                Toast.makeText(HandleStoryboardColor.this, "You clicked: " + myRecyclerViewAdapter.getItem(position).toString(), Toast.LENGTH_SHORT).show();
            }
        });

        rc.setAdapter(myRecyclerViewAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById( R.id.fab2 );
        fab.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make( view, "Replace with your own action 2", Snackbar.LENGTH_LONG )
                        .setAction( "Action", null ).show();
            }
        } );
    }

    public void ChangerCouleurSB (View view, StoryboardElement element) {
        Intent intent = new Intent(this, ChangeStoryboardElement.class);
        intent.putExtra("element", Json.getInstance().Serialize(element));
        startActivity(intent);
    }
}