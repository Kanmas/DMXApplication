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
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;

public class Changer_Couleur extends AppCompatActivity {

    private static Changer_Couleur currentInstance;
    private static void setCurrentInstance (Changer_Couleur changer_couleur) {
        Changer_Couleur.currentInstance = changer_couleur;
    }
    public static Changer_Couleur getCurrentInstance () {
        return currentInstance;
    }

    private ColorPacket packet = new ColorPacket();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_changer__couleur );
        setCurrentInstance(this);

        ((LinearLayout)findViewById( R.id.LayoutRed )).setBackgroundColor( Color.rgb( 127, 0,0 ) );
        ((LinearLayout)findViewById( R.id.LayoutGreen )).setBackgroundColor( Color.rgb( 0, 127,0 ) );
        ((LinearLayout)findViewById( R.id.LayoutBlue )).setBackgroundColor( Color.rgb(0 , 0,127 ) );
        ((LinearLayout)findViewById( R.id.linearLayoutMelange )).setBackgroundColor( Color.rgb(127 , 127,127 ) );

        ((SeekBar)findViewById( R.id.seekBarRed )).setOnSeekBarChangeListener( new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                ((LinearLayout)findViewById( R.id.LayoutRed)).setBackgroundColor( Color.rgb( seekBar.getProgress(), 0, 0 ) );
                ChangerLayoutMelange();
                getCurrentInstance().packet.getCouleur().setRed( seekBar.getProgress() );
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
                getCurrentInstance().packet.getCouleur().setBlue( seekBar.getProgress() );
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
                getCurrentInstance().packet.getCouleur().setGreen( seekBar.getProgress() );
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        } );

        ((Button)findViewById( R.id.btnenvoyerCouleur )).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Socket.getCurrentInstance().test();

                try {
                    int targetAddress = 0;
                    try {
                        targetAddress = Integer.parseInt( Configuration.getCurrentInstance().getAddress() );
                    } catch (Exception ex){
                        Toast.makeText( getCurrentInstance(), "Finally", Toast.LENGTH_SHORT ).show();
                        targetAddress = 0;
                    }

                    packet.couleur.setTargetAddress( targetAddress );
                    NetworkManager.getInstance().SendFragment( Json.getInstance().Serialize( getCurrentInstance().packet ).toString(), InetAddress.getByName(Configuration.getCurrentInstance().getHostname()), Integer.parseInt( Configuration.getCurrentInstance().getPort()));

                    Toast.makeText(Changer_Couleur.getCurrentInstance(), "Envoyer", Toast.LENGTH_SHORT).show();
                } catch (Exception e){
                    Toast.makeText(Changer_Couleur.getCurrentInstance(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
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

                        try {
                            Configuration.getCurrentInstance().SauvegarderCC();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    });
                Configuration.getCurrentInstance().setOnPropertyChanged(new Configuration.PropertyChangedListener() {
                    @Override
                    public void OnPropertyChanged(String propertyName) {
                        switch (propertyName) {
                            case "Type":
                                break;
                            case "Address":
                                try {
                                    getCurrentInstance().packet.couleur.setTargetAddress( Integer.parseInt( Configuration.getCurrentInstance().getAddress() ) );
                                } finally {
                                    getCurrentInstance().packet.couleur.setTargetAddress( 0 );
                                }

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

                ((EditText)mView.findViewById( R.id.editextePortDistCC )).setText( Configuration.getCurrentInstance().getPort() );
                ((EditText)mView.findViewById( R.id.editTextAddrCibleCC )).setText( Configuration.getCurrentInstance().getAddress() );
                ((EditText)mView.findViewById( R.id.editTextAddrIPCC )).setText( Configuration.getCurrentInstance().getHostname() );

                (( EditText ) mView.findViewById( R.id.editTextAddrIPCC )).addTextChangedListener( new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    Configuration.getCurrentInstance().setHostname(charSequence.toString());
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
                        Configuration.getCurrentInstance().setAddress(charSequence.toString());
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
                        Configuration.getCurrentInstance().setPort(charSequence.toString());
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

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
}


