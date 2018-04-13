package com.example.quentinlehmann.dmxv2;

import android.app.Dialog;
import android.content.ClipData;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;

public class Changer_Couleur extends AppCompatActivity {

    private static Changer_Couleur currentInstance;
    private static void setCurrentInstance (Changer_Couleur changer_couleur) {
        Changer_Couleur.currentInstance = changer_couleur;
    }
    public static Changer_Couleur getCurrentInstance () {
        return currentInstance;
    }
    private Configuration modele;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_changer__couleur );
        setCurrentInstance(this);
        modele = new Configuration();

        ((LinearLayout)findViewById( R.id.LayoutRed )).setBackgroundColor( Color.rgb( 127, 0,0 ) );
        ((LinearLayout)findViewById( R.id.LayoutGreen )).setBackgroundColor( Color.rgb( 0, 127,0 ) );
        ((LinearLayout)findViewById( R.id.LayoutBlue )).setBackgroundColor( Color.rgb(0 , 0,127 ) );
        ((LinearLayout)findViewById( R.id.linearLayoutMelange )).setBackgroundColor( Color.rgb(127 , 127,127 ) );

        ((SeekBar)findViewById( R.id.seekBarRed )).setOnSeekBarChangeListener( new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                ((LinearLayout)findViewById( R.id.LayoutRed)).setBackgroundColor( Color.rgb( seekBar.getProgress(), 0, 0 ) );
                ChangerLayoutMelange();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        } );

        ((SeekBar)findViewById( R.id.seekBarBlue)).setOnSeekBarChangeListener( new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                ((LinearLayout)findViewById( R.id.LayoutBlue)).setBackgroundColor( Color.rgb(0, 0, seekBar.getProgress() ) );
                ChangerLayoutMelange();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        } );

        ((SeekBar)findViewById( R.id.seekBarGreen )).setOnSeekBarChangeListener( new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                ((LinearLayout)findViewById( R.id.LayoutGreen)).setBackgroundColor( Color.rgb( 0, seekBar.getProgress(), 0 ) );
                ChangerLayoutMelange();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        } );

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
                final View mView = getLayoutInflater().inflate( R.layout.boite_dialogue_settings, null);
                mBuilder.setView( mView );


               mBuilder.setPositiveButton( "Valider", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        try{
                            ((Spinner ) mView.findViewById(R.id.SpinerTypeCible)).setAdapter(new ArrayAdapter<String>(getCurrentInstance(), android.R.layout.simple_spinner_item, getCurrentInstance().getModel().getTargetType()));
                        }catch (Exception ex){
                            Toast.makeText(getCurrentInstance(), ex.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        ((Spinner)mView.findViewById(R.id.SpinerTypeCible)).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                try {
                                    getCurrentInstance().getModel().setType(getCurrentInstance().getModel().getTargetType()[i]);
                                }
                                catch (Exception ex)
                                {
                                    Toast.makeText(getCurrentInstance(), ex.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });
                        try {
                            getCurrentInstance().getModel().SauvegarderCC();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    });
                modele.setOnPropertyChanged(new Configuration.PropertyChangedListener() {
                    @Override
                    public void OnPropertyChanged(String propertyName) {
                        switch (propertyName) {
                            case "Type":
                                break;
                            case "Address":
                                break;
                            case "Port":
                                break;
                            case "Hostname":
                                break;
                            default:
                                break;
                        }
                    }
                });




            (( EditText ) mView.findViewById( R.id.editTextAddrIPCC )).addTextChangedListener( new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    Toast.makeText( getCurrentInstance(), "Ok", Toast.LENGTH_SHORT ).show();
                    getCurrentInstance().getModel().setHostname(charSequence.toString());
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            } );

                ((EditText) mView.findViewById(R.id.editTextAddrCibleCC)).addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        Toast.makeText( getCurrentInstance(), "Ok2", Toast.LENGTH_SHORT ).show();
                        getCurrentInstance().getModel().setAddress(charSequence.toString());
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });

                ((EditText) mView.findViewById(R.id.editextePortDistCC)).addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        Toast.makeText( getCurrentInstance(), "Ok3", Toast.LENGTH_SHORT ).show();
                        getCurrentInstance().getModel().setPort(charSequence.toString());
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });

                ((Spinner)mView.findViewById(R.id.SpinerTypeCible)).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        Toast.makeText(getCurrentInstance(), "salut", Toast.LENGTH_SHORT).show();
                        try {
                            getCurrentInstance().getModel().setType(getCurrentInstance().getModel().getTargetType()[i]);
                        }
                        catch (Exception ex)
                        {
                            Toast.makeText(getCurrentInstance(), ex.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

                mBuilder.setNegativeButton( "Annuler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                } );
                AlertDialog dialog = mBuilder.create();
                dialog.show();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void ChangerLayoutMelange (){
        ((LinearLayout)findViewById( R.id.linearLayoutMelange )).setBackgroundColor( Color.rgb( (( SeekBar ) findViewById( R.id.seekBarRed )).getProgress(),
                (( SeekBar ) findViewById( R.id.seekBarGreen )).getProgress(),
                (( SeekBar ) findViewById( R.id.seekBarBlue )).getProgress()));
    }

    public Configuration getModel() {
        return modele;
    }

    public void setModel(Configuration model) {
        this.modele = modele;
    }
}


