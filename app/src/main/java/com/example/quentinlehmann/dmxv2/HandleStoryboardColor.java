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

import com.example.quentinlehmann.dmxv2.Adapters.MyRecyclerViewAdapter;
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

        // debug
        Toast.makeText(this, "HandleStoryboardColor.java", Toast.LENGTH_LONG).show();

        Storyboard.getCurrentInstance().write(this);

        // actualisation du titre
        setTitle(Storyboard.getCurrentInstance().getName());
        Toast.makeText(HandleStoryboardColor.this, Storyboard.getCurrentInstance().toString(), Toast.LENGTH_LONG).show();

        // initialisation du recycler
        RecyclerView rc = findViewById(R.id.rcGererCouleurSB);
        rc.setLayoutManager(new LinearLayoutManager(this));

        // initialisation de l'adapteur
        myRecyclerViewAdapter = new MyRecyclerViewAdapter(this, Storyboard.getCurrentInstance().getStoryboardElements());
        myRecyclerViewAdapter.setClickListener(new MyRecyclerViewAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                StoryboardElement element = myRecyclerViewAdapter.getItem(position);
                element.setPosition(position);
                StoryboardElement.setCurrentInstance(element);
                HandleStoryboardElement(view);
                Toast.makeText(HandleStoryboardColor.this, "You clicked: " + myRecyclerViewAdapter.getItem(position).toString(), Toast.LENGTH_SHORT).show();
            }
        });

        // ajout de l'adapteur dans le recycler
        rc.setAdapter(myRecyclerViewAdapter);

        // intialisation du bouton flottant
        FloatingActionButton fab = findViewById( R.id.fab2 );
        fab.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StoryboardElement element = new StoryboardElement(127,127,127, 1.0);
                element.setPosition(-1);
                StoryboardElement.setCurrentInstance(element);
                HandleStoryboardElement(view);
                //Snackbar.make( view, "Replace with your own action 2", Snackbar.LENGTH_LONG ).setAction( "Action", null ).show();
            }
        } );
    }

    /**
     * Change la vue pour aller sur l'activité de gestion de la couleur
     * @param view
     * @param element
     */
    public void HandleStoryboardElement (View view) {
        Intent intent = new Intent(this, ChangeStoryboardElement.class);
        startActivity(intent);
    }
}
