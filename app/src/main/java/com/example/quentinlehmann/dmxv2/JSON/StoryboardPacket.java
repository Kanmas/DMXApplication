package com.example.quentinlehmann.dmxv2.JSON;

import com.example.quentinlehmann.dmxv2.Common.StoryboardElement;

public class StoryboardPacket {

    public StoryboardPacketWrapper storyboard = new StoryboardPacketWrapper();

    public class StoryboardPacketWrapper {
        public StoryboardElement[] elements;
    }
}
