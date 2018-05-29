package com.example.quentinlehmann.dmxv2;

import com.google.gson.annotations.SerializedName;

/**
 * Created by quentin.lehmann on 09/05/2018.
 */

public class ColorPacket {
    public ColorPacketWrapper couleur = new ColorPacketWrapper();

    public ColorPacketWrapper getCouleur() {
        return couleur;
    }

    public void setCouleur(ColorPacketWrapper couleur) {
        this.couleur = couleur;
    }

    public class ColorPacketWrapper
    {

        public String target = "PROJO";
        public int targetAddress;
        public int red;
        public int blue;
        public int green;

        public int getBlue() {
            return blue;
        }

        public String getTarget() {
            return target;
        }

        public void setTarget(String target) {
            this.target = target;
        }

        public int getTargetAddress() {
            return targetAddress;
        }

        public void setTargetAddress(int targetAddress) {
            this.targetAddress = targetAddress;
        }

        public int getRed() {
            return red;
        }

        public void setRed(int red) {
            this.red = red;
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
    }
}

