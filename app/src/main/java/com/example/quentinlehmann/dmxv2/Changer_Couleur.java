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

import com.example.quentinlehmann.dmxv2.Configurations.Configuration;
import com.example.quentinlehmann.dmxv2.JSON.PacketConstructor;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Changer_Couleur extends AppCompatActivity {

    private static Changer_Couleur currentInstance;
    private static void setCurrentInstance (Changer_Couleur changer_couleur) {
        Changer_Couleur.currentInstance = changer_couleur;
    }
    public static Changer_Couleur getCurrentInstance () {
        return currentInstance;
    }

    private ColorPacket packet = new ColorPacket();

    private ColorWrapper colorWrapper = new ColorWrapper();

    // layout affichant la couleur rouge
    private LinearLayout redLayout;
    // layout affichant la couleur bleu
    private LinearLayout blueLayout;
    // layout affichant la couleur verte
    private LinearLayout greenLayout;
    // layout affichant le melange de couleur
    private LinearLayout blendLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_changer__couleur );
        setCurrentInstance(this);

        redLayout = findViewById(R.id.LayoutRed);
        greenLayout = findViewById(R.id.LayoutGreen);
        blueLayout = findViewById(R.id.LayoutBlue);
        blendLayout = findViewById(R.id.linearLayoutMelange);

        // initialisation de l'écoute des champs du modèle
        colorWrapper.setOnPropertyChanged(new BaseModel.PropertyChangedListener() {
            @Override
            public void OnPropertyChanged(String propertyName) {
                switch (propertyName) {
                    case ColorWrapper.RED:
                        redLayout.setBackgroundColor(colorWrapper.getRedBalance());
                        break;
                    case ColorWrapper.GREEN:
                        greenLayout.setBackgroundColor(colorWrapper.getGreenBalance());
                        break;
                    case ColorWrapper.BLUE:
                        blueLayout.setBackgroundColor(colorWrapper.getBlueBalance());
                        break;
                }
                blendLayout.setBackgroundColor(colorWrapper.getBlendedBalance());
            }
        });

        colorWrapper.setRed(127);
        colorWrapper.setGreen(127);
        colorWrapper.setBlue(127);

        // initilisation du curseur rouge
        ((SeekBar)findViewById( R.id.seekBarRed )).setOnSeekBarChangeListener( new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                colorWrapper.setRed(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        } );

        // initialisation du curseur bleu
        ((SeekBar)findViewById( R.id.seekBarBlue)).setOnSeekBarChangeListener( new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                colorWrapper.setBlue(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        } );

        // initialisation du curseur vert
        ((SeekBar)findViewById( R.id.seekBarGreen )).setOnSeekBarChangeListener( new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                colorWrapper.setGreen(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        } );

        // intialisation du bouton d'envoi de couleur
        (findViewById( R.id.btnenvoyerCouleur )).setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String json = PacketConstructor.constructColorPacket(colorWrapper);
                try {
                    NetworkManager.getInstance().SendFragment(json, InetAddress.getByName(ConfigurationOld.getCurrentInstance().getHostname()), Integer.parseInt(ConfigurationOld.getCurrentInstance().getPort()));
                    Toast.makeText(Changer_Couleur.this, "Sended", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(Changer_Couleur.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }

                /*
                try {

                    int targetAddress = 0;
                    try {
                        targetAddress = Integer.parseInt( ConfigurationOld.getCurrentInstance().getAddress() );
                    } catch (Exception ex){
                        Toast.makeText( getCurrentInstance(), "Finally", Toast.LENGTH_SHORT ).show();
                        targetAddress = 0;
                    }

                    packet.couleur.setTargetAddress( targetAddress );
                    NetworkManager.getInstance().SendFragment( Json.getInstance().Serialize( getCurrentInstance().packet ).toString(), InetAddress.getByName(ConfigurationOld.getCurrentInstance().getHostname()), Integer.parseInt( ConfigurationOld.getCurrentInstance().getPort()));

                    Toast.makeText(Changer_Couleur.getCurrentInstance(), "Envoyer", Toast.LENGTH_SHORT).show();
                } catch (Exception e){
                    Toast.makeText(Changer_Couleur.getCurrentInstance(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                */
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
                final Configuration localeConfiguration = new Configuration(Configuration.getInstance());
                AlertDialog.Builder mBuilder = new AlertDialog.Builder( Changer_Couleur.this );
                final View mView = getLayoutInflater().inflate( R.layout.boite_dialogue_settings, null);
                mBuilder.setView( mView );


                mBuilder.setPositiveButton( "Valider", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(Changer_Couleur.this, localeConfiguration.toString(), Toast.LENGTH_LONG).show();
                        Configuration.getInstance().ApplyConfiguration(localeConfiguration);
                        try {
                            ConfigurationOld.getCurrentInstance().SauvegarderCC();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                /*
                ConfigurationOld.getCurrentInstance().setOnPropertyChanged(new ConfigurationOld.PropertyChangedListener() {
                        @Override
                        public void OnPropertyChanged(String propertyName) {
                            switch (propertyName) {
                                case "Type":
                                    break;
                                case "Address":
                                    try {
                                        getCurrentInstance().packet.couleur.setTargetAddress( Integer.parseInt( ConfigurationOld.getCurrentInstance().getAddress() ) );
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
*/
                //((EditText)mView.findViewById( R.id.editextePortDistCC )).setText( ConfigurationOld.getCurrentInstance().getPort() );
                ((EditText)mView.findViewById( R.id.editextePortDistCC )).setText( String.valueOf(localeConfiguration.getSendPort()) );
                //((EditText)mView.findViewById( R.id.editTextAddrCibleCC )).setText( ConfigurationOld.getCurrentInstance().getAddress() );
                ((EditText)mView.findViewById( R.id.editTextAddrCibleCC )).setText( String.valueOf(localeConfiguration.getTargetAddress()) );
                //((EditText)mView.findViewById( R.id.editTextAddrIPCC )).setText( ConfigurationOld.getCurrentInstance().getHostname() );
                ((EditText)mView.findViewById( R.id.editTextAddrIPCC )).setText( localeConfiguration.getHostname().toString() );

                (( EditText ) mView.findViewById( R.id.editTextAddrIPCC )).addTextChangedListener( new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        //ConfigurationOld.getCurrentInstance().setHostname(charSequence.toString());
                        localeConfiguration.setHostname(charSequence.toString());
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
                        //ConfigurationOld.getCurrentInstance().setAddress(charSequence.toString());
                        localeConfiguration.setTargetAddress(charSequence.toString());
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
                        //ConfigurationOld.getCurrentInstance().setPort(charSequence.toString());
                        localeConfiguration.setSendPort(charSequence.toString());
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
}


