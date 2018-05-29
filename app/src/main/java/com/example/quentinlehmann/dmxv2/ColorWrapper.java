package com.example.quentinlehmann.dmxv2;

import android.graphics.Color;

public class ColorWrapper extends BaseModel {

    /**
     * Chaîne de caractères contenant le nom du champs gérant la couleur rouge
     */
    public static final String RED = "red";
    /**
     * Chaîne de caractères contenant le nom du champs gérant la couleur verte
     */
    public static final String GREEN = "green";
    /**
     * Chaîne de caractères contenant le nom du champs gérant la couleur bleu
     */
    public static final String BLUE = "blue";

    /**
     * Composante rouge
     */
    private int red;
    /**
     * Composante bleu
     */
    private int blue;
    /**
     * Composante verte
     */
    private int green;

    /**
     * Renvoie la composante rouge sous forme d'entier
     * @return red
     */
    public int getRed() {
        return red;
    }

    /**
     * Renseigne la valeur pour la composante rouge
     * @param red
     */
    public void setRed(int red) {
        if (red == this.red)
            return;

        this.red = red;
        NotifyPropertyChanged(RED);
    }

    /**
     * Renvoie la composante bleu
     * @return blue
     */
    public int getBlue() {
        return blue;
    }

    /**
     * Renseigne la valeur pour la composante bleu
     * @param blue
     */
    public void setBlue(int blue) {
        if (blue == this.blue)
            return;
        this.blue = blue;
        NotifyPropertyChanged(BLUE);
    }

    /**
     * Renvoie la valeur pour la composante verte
     * @return green
     */
    public int getGreen() {
        return green;
    }

    /**
     * Renseigne la valeur de la composante verte
     * @param green
     */
    public void setGreen(int green) {
        if (green == this.green) return;

        this.green = green;
        NotifyPropertyChanged(GREEN);
    }

    /**
     * Renvoie la composante rouge sous forme de couleur
     * @return redBalance
     */
    public int getRedBalance () {
        return Color.rgb(red, 0, 0);
    }

    /**
     * Renvoie la composante verte sous forme de couleur
     * @return blueBalance
     */
    public int getGreenBalance () {
        return Color.rgb(0, green, 0);
    }

    /**
     * Renvoie la composante bleu sous forme de couleur
     * @return greenBalance
     */
    public int getBlueBalance () {
        return Color.rgb(0, 0, blue);
    }

    /**
     * Renvoie la couleur résultante des balances
     * @return blendedColor
     */
    public int getBlendedBalance () {
        return Color.rgb(red, green, blue);
    }

}
