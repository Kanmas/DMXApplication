package com.example.quentinlehmann.dmxv2;

/**
 * Created by quentin.lehmann on 25/05/2018.
 */

public class PacketFragment {

    public boolean isFragmented;
    public int index;
    public int packetCount;
    public String data;

    public boolean isFragmented() {
        return isFragmented;
    }

    public void setFragmented(boolean fragmented) {
        isFragmented = fragmented;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getPacketCount() {
        return packetCount;
    }

    public void setPacketCount(int packetCount) {
        this.packetCount = packetCount;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

}
