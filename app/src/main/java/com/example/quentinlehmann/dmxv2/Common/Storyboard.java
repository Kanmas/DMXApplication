package com.example.quentinlehmann.dmxv2.Common;

import android.content.Context;

import com.example.quentinlehmann.dmxv2.JSON.Json;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;


/**
 * Classe représentant une storyboard
 */
public class Storyboard {

    /**
     * Le chemin du répertoire où se trouve les storyboards
     */
    public static final String STORYBOARD_PATH = "storyboards";

    /**
     * Les éléments de la storyboard
     */
    private ArrayList<StoryboardElement> storyboardElements;

    /**
     * Le nom de la storyboard
     */
    private String name;

    /**
     * L'instance en cours de modification
     */
    public static Storyboard currentInstance;

    /**
     * Initialise une nouvelle instance de storyboard
     */
    public Storyboard () {
        storyboardElements = new ArrayList<>(  );
    }

    /**
     * Initialise une nouvelle instance de storyboard avec un nom
     * @param name le nom de la storyboard
     */
    public Storyboard (String name) {
        storyboardElements = new ArrayList<>(  );
        this.name = name;
    }

    /**
     * Renvoie la liste d'éléments présent dans cette storyboard
     * @return storyboardElements
     */
    public ArrayList<StoryboardElement> getStoryboardElements() {
        return storyboardElements;
    }

    /**
     * Renseigne la liste d'éléments de la storyboard
     * @param storyboardElements les éléments de la storyboard
     */
    public void setStoryboardElements(ArrayList<StoryboardElement> storyboardElements) {
        this.storyboardElements = storyboardElements;
    }

    /**
     * Renvoie le nom de la storyboard
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Renseigne le nom de la storyboard
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Renvoie la durée de la storyboard
     * @return duration
     */
    public double getDuration () {
        double time = 0.0;

        for (StoryboardElement e : storyboardElements) {
            time += e.getTime();
        }

        return time;
    }

    /**
     * Renvoie le nombre total d'élément de la storyboard
     * @return count
     */
    public double getCount () {
        return storyboardElements.size();
    }

    /**
     * Méthode de test servant à créer une fausse liste de storyboard
     * @param max nombre de storyboard
     * @return fakeStoryboard
     */
    public static ArrayList<Storyboard> fake (int max) {
        ArrayList<Storyboard> fake = new ArrayList<>(  );

        for (int i = 0; i < max; i++) {
            Storyboard storyboard = new Storyboard("Story board " + String.valueOf( i ));
            storyboard.setStoryboardElements(StoryboardElement.fake(10));
            fake.add(storyboard);
        }

        return fake;
    }

    @Override
    public String toString() {
        return name;
    }

    /**
     * Retourne la storybaord en cours de modification
     * @return storyboard
     */
    public static Storyboard getCurrentInstance() {
        return currentInstance;
    }

    /**
     * Renseigne la storyboard en cours de modification
     * @param currentInstance la storyboard en cours de modification
     */
    public static void setCurrentInstance(Storyboard currentInstance) {
        Storyboard.currentInstance = currentInstance;
    }

    /**
     * Ecrit la storyboard dans les répertoires de l'application
     * @param context context
     */
    public void write (Context context) {
        File directory = new File(context.getFilesDir(), STORYBOARD_PATH);
        if (!directory.exists())directory.mkdir();
        File file = new File(directory, name);
        try {
            FileOutputStream stream = new FileOutputStream(file);
            stream.write(Json.getInstance().Serialize(this).getBytes());
            stream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Renvoie toutes les storyboards sauvegardées
     * @param context context
     * @return storyboards
     */
    public static ArrayList<Storyboard> findAll (Context context) {
        File directory = new File(context.getFilesDir(), STORYBOARD_PATH);
        File[] files = directory.listFiles();

        ArrayList<Storyboard> storyboards = new ArrayList<>();
        if (files != null) {

            for (File f : files) {
                try {

                    StringBuffer buffer = new StringBuffer("");

                    FileInputStream stream = new FileInputStream(f);
                    InputStreamReader streamReader = new InputStreamReader(stream);
                    BufferedReader bufferedReader = new BufferedReader(streamReader);

                    String readString;

                    while ((readString = bufferedReader.readLine()) != null) {
                        buffer.append(readString);
                        buffer.append('\n');
                    }
                    bufferedReader.close();
                    streamReader.close();
                    stream.close();

                    storyboards.add((Storyboard) Json.getInstance().Deserialize(buffer.toString(), Storyboard.class));

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return storyboards;
    }

    /**
     * Vérifie si le nom de la storyboard n'est pas déjà prit
     * @param context context
     * @return isValid
     */
    public boolean checkName (Context context) {
        File directory = new File(context.getFilesDir(), STORYBOARD_PATH);
        if (!directory.exists()) directory.mkdir();

        File file = new File(directory, name);
        return !file.exists();
    }
}
