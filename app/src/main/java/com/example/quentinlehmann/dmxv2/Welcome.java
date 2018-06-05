package com.example.quentinlehmann.dmxv2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.quentinlehmann.dmxv2.Configurations.Configuration;
import com.example.quentinlehmann.dmxv2.JSON.Json;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;

/**
 * Activité de démarrage de l'application
 */
public class Welcome extends AppCompatActivity {

    public static final String CONFIGURATION_FILE_PATH = "configuration.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_welcome);

        initilizeConfiguration();

    }

    /**
     * Test si la configuration est présente dans les fichiers de l'application
     */
    public void initilizeConfiguration () {
        File file = new File(getFilesDir(), CONFIGURATION_FILE_PATH);

        if (file.exists()) { // cas ou la configuration existe déjà
            StringBuilder text = new StringBuilder();
            try {
                ////Toast.makeText( this, "La configuration existe", Toast.LENGTH_SHORT ).show();
                // création d'un lecteur de tampon avec le contenu du fichier
                BufferedReader br = new BufferedReader(new FileReader(file));
                // chaîne de caractère tampon
                String line;
                // pour chaque ligne dans le fichier on ajoute la ligne au StringBuilder
                while ((line = br.readLine()) != null) {
                    text.append(line);
                    text.append('\n');
                }
                // ferme le lecteur de tampon
                br.close();

                // convertie le StringBuilder en String
                String json = text.toString();
                // Deserialize la configuration
                Configuration savedConfiguration = (Configuration) Json.getInstance().Deserialize(json, Configuration.class);

                // Applique la configuration à la configuration globale
                Configuration.getInstance().ApplyConfiguration(savedConfiguration);
                // Debug
                //Toast.makeText(this, "1: " + savedConfiguration.toString(), Toast.LENGTH_LONG).show();
                //Toast.makeText(this, "2: "+ Configuration.getInstance().toString(), Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                e.printStackTrace();
                //Toast.makeText(this, "Error config", Toast.LENGTH_LONG).show();
            }

        } else { // cas ou le fichier de config n'existe pas
            // on récupère la configuration de base de l'application
            Configuration defaultConfiguration = Configuration.getDefaultConfiguration();

            // on créer un objet permettant l'écriture de fichier
            FileOutputStream outputStream;

            try {
                // on récupère l'objet avec le chemin du fichier de configuration
                outputStream = openFileOutput(CONFIGURATION_FILE_PATH, MODE_PRIVATE);

                // Sérialize la configuration de base
                String json = Json.getInstance().Serialize(defaultConfiguration);
                // Ecrit la configuration dans un fichier
                outputStream.write(json.getBytes());
                // ferme l'objet que sert à l'écriture
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();

                //Toast.makeText( this, "le else", Toast.LENGTH_SHORT ).show();
            }

            // Assigne la configuration par défaut à la configuration globale
            Configuration.getInstance().ApplyConfiguration(defaultConfiguration);
            ////Toast.makeText(this, "Not Exists", Toast.LENGTH_LONG).show();
        }
    }


    public void Page_Changer_Couleur(View view) {
        startActivity( new Intent( this, ChangeColor.class ) );
    }
    public void GererSB(View view){
        startActivity( new Intent( this, HandleStoryboard.class));
    }

    public void ParametreGlobaux (View view){
        startActivity( new Intent( this, GlobalsSettings.class) );
    }
}
