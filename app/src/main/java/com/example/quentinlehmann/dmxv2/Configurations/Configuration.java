package com.example.quentinlehmann.dmxv2.Configurations;

import com.example.quentinlehmann.dmxv2.BaseModel;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Configuration extends BaseModel{

    public static final String HOSTNAME = "hostname";
    public static final String SEND_PORT = "sendPort";
    public static final String RECEIVE_PORT = "receivePort";
    public static final String TARGET_ADDRESS = "targetAddress";

    private InetAddress hostname;
    private int sendPort;
    private int receivePort;
    private int targetAddress;

    private static Configuration instance;

    public static Configuration getInstance () {
        if (instance == null) instance = new Configuration();
        return instance;
    }

    public Configuration () {
        try {
            hostname = InetAddress.getByName("127.0.0.1");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        sendPort = 0;
        targetAddress = 0;
        receivePort = 0;

    }

    public Configuration (Configuration configuration) {
        hostname = configuration.hostname;
        sendPort = configuration.sendPort;
        receivePort = configuration.receivePort;
        targetAddress = configuration.targetAddress;
    }

    public InetAddress getHostname() {
        return hostname;
    }

    public void setHostname(InetAddress hostname) {
        if (hostname == this.hostname) return;

        this.hostname = hostname;
        NotifyPropertyChanged(HOSTNAME);
    }

    public int getSendPort() {
        return sendPort;
    }

    public void setSendPort(int sendPort) {
        if (sendPort == this.sendPort) return;
        this.sendPort = sendPort;
        NotifyPropertyChanged(SEND_PORT);
    }

    public int getReceivePort() {
        return receivePort;
    }

    public void setReceivePort(int receivePort) {
        if (receivePort == this.receivePort) return;
        this.receivePort = receivePort;
        NotifyPropertyChanged(RECEIVE_PORT);
    }

    public int getTargetAddress() {
        return targetAddress;
    }

    public void setTargetAddress(int targetAddress) {
        if (targetAddress == this.targetAddress) return;
        this.targetAddress = targetAddress;
        NotifyPropertyChanged(TARGET_ADDRESS);
    }

    public void setHostname (String hostname) {
        try {
            setHostname(InetAddress.getByName(hostname));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setSendPort (String sendPort) {
        try {
            setSendPort(Integer.parseInt(sendPort));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setReceivePort (String receivePort) {
        try {
            setReceivePort(Integer.parseInt(receivePort));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setTargetAddress (String targetAddress) {
        try {
            setTargetAddress(Integer.parseInt(targetAddress));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return String.format("Hostname: %s; SendPort: %d; TargetAddress: %d;", hostname, sendPort, targetAddress);
    }

    public void ApplyConfiguration (Configuration configuration) {
        setHostname(configuration.hostname);
        setSendPort(configuration.sendPort);
        setReceivePort(configuration.receivePort);
        setTargetAddress(configuration.targetAddress);
    }
}
