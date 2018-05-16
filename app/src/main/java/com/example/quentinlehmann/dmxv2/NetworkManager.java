package com.example.quentinlehmann.dmxv2;

import android.os.NetworkOnMainThreadException;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.channels.IllegalBlockingModeException;


/**
 * Created by quentin.lehmann on 09/05/2018.
 */

public class NetworkManager {
    // Singleton pattern
    private static NetworkManager instance;

    public static NetworkManager getInstance() {
        if (instance == null) instance = new NetworkManager();
        return instance;
    }
    // end Singleton pattern

    // Fields
    private DatagramSocket socket;
    // end Fields

    private NetworkManager ()
    {
        try {
            socket = new DatagramSocket(3000);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public void Send (final String data, final InetAddress receiverAddress, final int port)
    {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                byte[] dataBytes = data.getBytes();
                DatagramPacket packet = new DatagramPacket(dataBytes, 0, dataBytes.length, receiverAddress, port);

                try {
                    socket.send(packet);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (IllegalBlockingModeException e) {
                    e.printStackTrace();
                } catch (NetworkOnMainThreadException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

}
