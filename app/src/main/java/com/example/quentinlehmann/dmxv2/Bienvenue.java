package com.example.quentinlehmann.dmxv2;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.quentinlehmann.dmxv2.Configurations.Configuration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;

public class Bienvenue extends AppCompatActivity {

    public static final String CONFIGURATION_FILE_PATH = "configuration.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_bienvenue );

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
                Configuration savedConfiguration = (Configuration)Json.getInstance().Deserialize(json, Configuration.class);

                // Applique la configuration à la configuration globale
                Configuration.getInstance().ApplyConfiguration(savedConfiguration);
                // Debug
                Toast.makeText(this, savedConfiguration.toString(), Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "Error config", Toast.LENGTH_LONG).show();
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
            }

            // Assigne la configuration par défaut à la configuration globale
            Configuration.getInstance().ApplyConfiguration(defaultConfiguration);
            Toast.makeText(this, "Not Exists", Toast.LENGTH_LONG).show();
        }
    }


    public void Page_Changer_Couleur(View view) {
        startActivity( new Intent( this, Changer_Couleur.class ) );
    }
    public void GererSB(View view){
        startActivity( new Intent( this, GererSB.class));
    }

    public void ParametreGlobaux (View view){
        startActivity( new Intent( this, ParametreGlobaux.class) );
    }
}
