package com.example.quentinlehmann.dmxv2;

/**
 * Created by quentin.lehmann on 25/05/2018.
 */

public class StoryboardElement {

    private int red;
    private int blue;
    private int green;
    private double time;
    private int targetAddress;
    private String target = "PROJO";
    public String getTarget() {
        return target;
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
}
