package com.example.quentinlehmann.dmxv2.JSON;

import com.example.quentinlehmann.dmxv2.Common.ColorWrapper;
import com.example.quentinlehmann.dmxv2.Common.Storyboard;
import com.example.quentinlehmann.dmxv2.Common.StoryboardElement;
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
        StoryboardPacket packet = new StoryboardPacket();
        //packet.storyboard.elements = storyboard.getStoryboardElements().toArray(new StoryboardElement[storyboard.getStoryboardElements().size()]);
        packet.storyboard.elements = new StoryboardElementPacket[storyboard.getStoryboardElements().size()];

        for (int i = 0; i < storyboard.getStoryboardElements().size(); i++) {
            packet.storyboard.elements[i] = new StoryboardElementPacket( storyboard.getStoryboardElements().get( i ) );

        }
        /*
        for (StoryboardElement e : packet.storyboard.elements) {
            e.setTargetAddress( 1 );
            e.setTarget( "BARRELED" );
            double time = e.getTime() * 1000;
            e.setTime( time );
        }*/
        packet.storyboard.name = storyboard.getName();
        return Json.getInstance().Serialize( packet );
    }
}
