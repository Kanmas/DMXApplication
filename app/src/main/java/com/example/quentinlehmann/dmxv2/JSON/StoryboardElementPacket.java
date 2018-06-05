package com.example.quentinlehmann.dmxv2.JSON;

import com.example.quentinlehmann.dmxv2.Common.StoryboardElement;
import com.example.quentinlehmann.dmxv2.Configurations.Configuration;

/**
 * Created by quentin.lehmann on 05/06/2018.
 */

public class StoryboardElementPacket {
    public int red;
    public int green;
    public int blue;
    public int time;
    public String target;
    public int targetAddress;

    public StoryboardElementPacket (StoryboardElement element) {
        red = element.getRed();
        blue = element.getBlue();
        green = element.getGreen();
        time = (int)Math.round(element.getTime() * 1000);
        target = "BARRELED";
        targetAddress = Configuration.getInstance().getTargetAddress();
    }
}
