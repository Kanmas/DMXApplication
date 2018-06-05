package com.example.quentinlehmann.dmxv2.Common;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by quentin.lehmann on 25/05/2018.
 */

public class StoryboardElement extends ColorWrapper {

    /**
     * L'instance en cours de modification dans l'interface
     */
    private static StoryboardElement currentInstance;

    /**
     * Position de l'élément dans sa storyboard
     */
    private int position;

    /**
     * Durée de cet élément
     */
    private double time;

    /**
     * Cible de cet élément
     */
    private int targetAddress = 1;

    /**
     * Type de cible de cet élément
     */
    private String target = "BARRELED";

    /**
     * Initialise une nouvelle instance de StoryboardElement avec les couleurs en paramètre
     * @param red composante rouge
     * @param blue composante blue
     * @param green composante verte
     * @param time la durée
     */
    public StoryboardElement (int red, int blue, int green, double time) {
        super(red, green, blue);
        this.time = time;
    }

    public StoryboardElement (StoryboardElement element) {
        super(element.getRed(), element.getGreen(), element.getBlue());
        time = element.getTime();
    }

    /**
     * Renvoie le type de cible de cet élément
     * @return targetType
     */
    public String getTarget() {
        return target;
    }

    /**
     * Renseigne le type de cible de cette élément
     * @param targetType le type de cible
     */
    public void setTarget (String targetType) {
        this.target = targetType;
    }

    /**
     * Renvoie la durée de cet élément
     * @return time
     */
    public double getTime() {
        return time;
    }

    /**
     * Renseigne la durée de cet élément
     * @param time durée de l'élément
     */
    public void setTime(double time) {
        this.time = time;
    }

    /**
     * Renvoie l'adresse ciblé par cet élément
     * @return targetAddress
     */
    public int getTargetAddress() {
        return targetAddress;
    }

    /**
     * Renseigne l'adresse ciblé par cet élément
     * @param targetAddress l'adresse ciblé
     */
    public void setTargetAddress(int targetAddress) {
        this.targetAddress = targetAddress;
    }

    /**
     * Renvoie une liste d'éléments pour des tests
     * @param count nombre d'élément
     * @return fakeList
     */
    public static ArrayList<StoryboardElement> fake (int count) {
        ArrayList<StoryboardElement> fake = new ArrayList<>(  );

        Random random = new Random(  );
        for (int i = 0; i < count; i++) {
            fake.add( new StoryboardElement(random.nextInt(255),random.nextInt(255),random.nextInt(255), Math.round(random.nextDouble() * 100) ) );
        }

        return fake;
    }

    /**
     * Renvoie la position de cet élément dans sa storyboard
     * @return position
     */
    public int getPosition() {
        return position;
    }

    /**
     * Renseigne la position de cet élément dans sa storyboard
     * @param position la position de cet élément dans sa storyboard
     */
    public void setPosition(int position) {
        this.position = position;
    }

    /**
     * Renvoie l'élément en cours de modification
     * @return currentInstance
     */
    public static StoryboardElement getCurrentInstance() {
        return currentInstance;
    }

    /**
     * Renseigne l'élément en cours de modification
     * @param currentInstance l'élément en cours de modification
     */
    public static void setCurrentInstance(StoryboardElement currentInstance) {
        StoryboardElement.currentInstance = currentInstance;
    }
}
