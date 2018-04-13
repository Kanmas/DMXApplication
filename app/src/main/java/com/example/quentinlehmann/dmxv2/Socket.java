package com.example.quentinlehmann.dmxv2;

import java.net.Inet4Address;

/**
 * Created by quentin.lehmann on 13/04/2018.
 */

public class Socket {

    private static Socket instance;

    public static Socket getInstance() {
        if (instance == null) instance = new Socket();
        return instance;
    }

    private Socket () {

    }

    private Inet4Address hostname;
    private int port;

    public Inet4Address getHostname() {
        return hostname;
    }

    public void setHostname(Inet4Address hostname) {
        this.hostname = hostname;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
