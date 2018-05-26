package com.example.quentinlehmann.dmxv2;

import android.graphics.Color;

public class ColorWrapper extends BaseModel {

    public static final String RED = "red";
    public static final String GREEN = "green";
    public static final String BLUE = "blue";

    private int red;
    private int blue;
    private int green;

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        if (red == this.red)
            return;

        this.red = red;
        NotifyPropertyChanged("red");
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        if (blue == this.blue)
            return;
        this.blue = blue;
        NotifyPropertyChanged("blue");
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        if (green == this.green) return;

        this.green = green;
        NotifyPropertyChanged("green");
    }

    public int getRedBalance () {
        return Color.rgb(red, 0, 0);
    }

    public int getGreenBalance () {
        return Color.rgb(0, green, 0);
    }

    public int getBlueBalance () {
        return Color.rgb(0, 0, blue);
    }

    public int getBlendedBalance () {
        return Color.rgb(red, green, blue);
    }

}
