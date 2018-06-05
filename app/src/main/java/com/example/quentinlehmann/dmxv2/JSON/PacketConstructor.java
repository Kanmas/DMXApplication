package com.example.quentinlehmann.dmxv2.JSON;

import com.example.quentinlehmann.dmxv2.Common.ColorWrapper;
import com.example.quentinlehmann.dmxv2.Common.Storyboard;
import com.example.quentinlehmann.dmxv2.Configurations.Configuration;

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
        packet.couleur.red = colorWrapper.getRed();
        packet.couleur.blue = colorWrapper.getBlue();
        packet.couleur.green = colorWrapper.getGreen();
        packet.couleur.targetAddress = Configuration.getInstance().getTargetAddress();
        packet.couleur.target = "BARRELED";
        return Json.getInstance().Serialize(packet);
    }

    public static String constructStoryboardPacket (Storyboard storyboard) {
        //
        return "";
    }
}
