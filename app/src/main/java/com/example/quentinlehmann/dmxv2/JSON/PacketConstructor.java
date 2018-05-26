package com.example.quentinlehmann.dmxv2.JSON;

import com.example.quentinlehmann.dmxv2.ColorPacket;
import com.example.quentinlehmann.dmxv2.ColorWrapper;
import com.example.quentinlehmann.dmxv2.Json;

/**
 * Prépare les paquets avant l'envoi au serveur
 */
public class PacketConstructor {
    /**
     * Construit la trame JSON correspondant à un changement de couleur à partir d'une instance de ColorWrapper
     * @param colorWrapper
     * @return json
     */
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
