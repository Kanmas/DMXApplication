package com.example.quentinlehmann.dmxv2.JSON;

import com.example.quentinlehmann.dmxv2.ColorPacket;
import com.example.quentinlehmann.dmxv2.ColorWrapper;
import com.example.quentinlehmann.dmxv2.Json;

// cette classe sert à transformer les classes du modèle en json que le servuer comprends
public class PacketConstructor {
    public static String constructColorPacket (ColorWrapper colorWrapper) {
        ColorPacket packet = new ColorPacket();
        packet.couleur.blue = colorWrapper.getBlue();
        packet.couleur.green = colorWrapper.getBlue();
        packet.couleur.green = colorWrapper.getGreen();
        packet.couleur.white = 255;
        packet.couleur.targetAddress = 1;
        packet.couleur.target = "PROJO";
        return Json.getInstance().Serialize(packet);
    }
}
