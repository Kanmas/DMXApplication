package com.example.quentinlehmann.dmxv2.Common;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by quentin.lehmann on 25/05/2018.
 */

public class StoryboardElement {

    private int red;
    private int blue;
    private int green;
    private double time;
    private int targetAddress = 1;
    private String target = "BARRELED";

    public String getTarget() {
        return target;
    }

    public StoryboardElement (int red, int blue, int green, double time) {
        this.red = red;
        this.blue = blue;
        this.green = green;
        this.time = time;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public int getTargetAddress() {
        return targetAddress;
    }

    public void setTargetAddress(int targetAddress) {
        this.targetAddress = targetAddress;
    }

    public static ArrayList<StoryboardElement> fake (int max) {
        ArrayList<StoryboardElement> fake = new ArrayList<>(  );

        Random random = new Random(  );
        for (int i = 0; i < max; i++) {
            fake.add( new StoryboardElement(random.nextInt(255),random.nextInt(255),random.nextInt(255), Math.round(random.nextDouble() * 100) ) );
        }

        return fake;
    }
}
