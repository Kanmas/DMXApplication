package com.example.quentinlehmann.dmxv2;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.quentinlehmann.dmxv2.Common.ColorWrapper;
import com.example.quentinlehmann.dmxv2.Configurations.Configuration;
import com.example.quentinlehmann.dmxv2.JSON.PacketConstructor;
import com.example.quentinlehmann.dmxv2.Networking.NetworkManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Activité de changement de couleur simple
 */
public class ChangeColor extends AppCompatActivity {

    public static final String IP_ADDRESS_PATTERN = "^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";

    private boolean isHostnameValid = true;

    /**
     * Renseigne le niveau de rouge au démarrage de l'interface
     */
    private static final int BASE_RED_BALANCE = 127;
    /**
     * Renseigne le niveau de vert au démarrage de l'interface
     */
    private static final int BASE_GREEN_BALANCE = 127;
    /**
     * Renseigne le niveau de bleu au démarrage de l'interface
     */
    private static final int BASE_BLUE_BALANCE = 127;

    /**
     * Représentation de la couleur afficher dans l'interface
     */
    private ColorWrapper colorWrapper = new ColorWrapper();

    /**
     * Layout qui sert à afficher la couleur rouge
     */
    private LinearLayout redLayout;
    /**
     * Layout qui sert à afficher la couleur bleu
     */
    private LinearLayout blueLayout;
    /**
     * Layout affichant la couleur verte
     */
    private LinearLayout greenLayout;
    /**
     * Layout affichant le melange de couleur
     */
    private LinearLayout blendLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_change_color);

        //Toast.makeText(this, "ChangeColor.java", Toast.LENGTH_LONG).show();

        // renseigne les layouts
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

        // intialisation
        colorWrapper.setRed(BASE_RED_BALANCE);
        colorWrapper.setGreen(BASE_GREEN_BALANCE);
        colorWrapper.setBlue(BASE_BLUE_BALANCE);

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
                    //NetworkManager.getInstance().SendFragment(json, InetAddress.getByName(ConfigurationOld.getCurrentInstance().getHostname()), Integer.parseInt(ConfigurationOld.getCurrentInstance().getPort()));
                    NetworkManager.getInstance().SendFragment( json, Configuration.getInstance().getHostname(), Configuration.getInstance().getSendPort() );
                    Toast.makeText(ChangeColor.this, "Envoyer", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(ChangeColor.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        } );

        (findViewById( R.id.btnAnnulerCouleur )).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                colorWrapper.setRed(BASE_RED_BALANCE);
                colorWrapper.setGreen(BASE_GREEN_BALANCE);
                colorWrapper.setBlue(BASE_BLUE_BALANCE);

                ((SeekBar)findViewById( R.id.seekBarRed )).setProgress( BASE_RED_BALANCE );
                ((SeekBar)findViewById( R.id.seekBarBlue )).setProgress( BASE_BLUE_BALANCE );
                ((SeekBar)findViewById( R.id.seekBarGreen )).setProgress( BASE_GREEN_BALANCE );
            }
        } );

        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //ajoute les entrées de menu_test à l'ActionBar
        getMenuInflater().inflate(R.menu.menuh_changer_couleur, menu);
        return true;
    }


    /**
     * Gère le clique de l'action bar
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case R.id.MenuChangerCouleur:
                final Configuration localeConfiguration = new Configuration(Configuration.getInstance());
                AlertDialog.Builder mBuilder = new AlertDialog.Builder( ChangeColor.this );
                final View mView = getLayoutInflater().inflate( R.layout.boite_dialogue_settings, null);
                mBuilder.setView( mView );


                mBuilder.setPositiveButton( "Valider", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Pattern p = Pattern.compile( IP_ADDRESS_PATTERN );
                        Matcher m = p.matcher(localeConfiguration.getHostname().toString());
                        if(isHostnameValid){
                            Toast.makeText(ChangeColor.this, "Sauvegarder", Toast.LENGTH_LONG).show();
                            Configuration.getInstance().ApplyConfiguration(localeConfiguration);
                            Configuration.getInstance().Write(ChangeColor.this);
                        } else {
                            Toast.makeText( ChangeColor.this, "Veuillez entrer une adresse valide", Toast.LENGTH_SHORT ).show();
                        }
                    }
                });

                ((EditText)mView.findViewById( R.id.editextePortDistCC )).setText( String.valueOf(localeConfiguration.getSendPort()) );
                ((EditText)mView.findViewById( R.id.editTextAddrCibleCC )).setText( String.valueOf(localeConfiguration.getTargetAddress()) );
                ((EditText)mView.findViewById( R.id.editTextAddrIPCC )).setText( (String.valueOf(localeConfiguration.getHostname().getHostAddress())) );

                (( EditText ) mView.findViewById( R.id.editTextAddrIPCC )).addTextChangedListener( new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        //ConfigurationOld.getCurrentInstance().setHostname(charSequence.toString());
                        isHostnameValid = localeConfiguration.setHostname(charSequence.toString());
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


