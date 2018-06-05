package com.example.quentinlehmann.dmxv2;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quentinlehmann.dmxv2.Adapters.MyRecyclerViewAdapter;
import com.example.quentinlehmann.dmxv2.Common.Storyboard;
import com.example.quentinlehmann.dmxv2.Common.StoryboardElement;
import com.example.quentinlehmann.dmxv2.JSON.Json;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

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
        //Toast.makeText(this, "HandleStoryboardColor.java", Toast.LENGTH_LONG).show();

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
     */
    public void HandleStoryboardElement (View view) {
        Intent intent = new Intent(this, ChangeStoryboardElement.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_handle_storyboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_delete_storyboard:

                AlertDialog.Builder deleteBuilder = new AlertDialog.Builder( HandleStoryboardColor.this );
                deleteBuilder.setTitle( "Effacer ?" );
                deleteBuilder.setMessage( "Voulez-vous vraiment supprimer cette storyboard?" );
                deleteBuilder.setPositiveButton( R.string.valider, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Storyboard.getCurrentInstance().delete(HandleStoryboardColor.this);
                        startActivity(new Intent(HandleStoryboardColor.this, HandleStoryboard.class));
                    }
                } );
                deleteBuilder.setNegativeButton( R.string.annuler, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                } );

                Dialog deleteDialog = deleteBuilder.create();

                deleteDialog.show();
                break;
            case R.id.action_rename_storyboard:
                //boolean b = Storyboard.getCurrentInstance().rename (this, "newName" + Calendar.getInstance().getTime().toString());

                //Toast.makeText(this, (b) ? "Ok": "Not", Toast.LENGTH_LONG).show();

                AlertDialog.Builder builder = new AlertDialog.Builder( HandleStoryboardColor.this );
                final View mView = getLayoutInflater().inflate( R.layout.boite_dialogue_rename, null );
                builder.setView( mView );
                builder.setTitle( R.string.rename_storyboard );

                builder.setNegativeButton( R.string.annuler, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                } );
                builder.setPositiveButton( R.string.valider, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        startActivity(new Intent(HandleStoryboardColor.this, HandleStoryboard.class));
                        Storyboard.getCurrentInstance().rename( HandleStoryboardColor.this, ((EditText) mView.findViewById( R.id.tvRenameStoryboard )).getText().toString() );


                    }
                } );
                Dialog dialog = builder.create();
                dialog.show();

                break;
            case R.id.action_send_storyboard:

                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
