package com.example.quentinlehmann.dmxv2;

import android.os.NetworkOnMainThreadException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.channels.IllegalBlockingModeException;
import java.util.ArrayList;
import java.util.List;


/**
 * Sert à gérer les communications avec le serveur
 */
public class NetworkManager {

    /**
     * Représente la taille maximum d'un paquet
     */
    public int mtu = 380;

    /**
     * Instance pour le singleton
     */
    private static NetworkManager instance;

    /**
     * Renvoie l'instance de NetworkManager
     * @return networkManager
     */
    public static NetworkManager getInstance() {
        if (instance == null) instance = new NetworkManager();
        return instance;
    }

    /**
     * Canal de communication
     */
    private DatagramSocket socket;

    /**
     * Initialise une nouvelle instance de la classe NetworkManager. Constructeur privé pour restreindre le nombre d'instance.
     */
    private NetworkManager ()
    {
        try {
            socket = new DatagramSocket(3000);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    /**
     * Envoie un message à l'hôte renseigné sur le port renseigné
     * @param data
     * @param receiverAddress
     * @param port
     */
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

    /**
     * Envoie un message à l'hôte renseigné sur le port renseigné. Si le message dépasse la taille maximal d'un paquet, il sera fragmenté.
     * @param message
     * @param receiverAddress
     * @param port
     */
    public void SendFragment (String message, InetAddress receiverAddress, int port){
        int mLength = message.length();
        int packetCount = mLength/mtu;
        int reste = mLength%mtu;

        ArrayList<PacketFragment> fragments = new ArrayList<PacketFragment>(  );

        if (packetCount > 1 || packetCount == 1 && reste != 0){
            for (int i = 0; i < packetCount; i++){
                int start = mtu*i;
                int end = start + mtu;
                String data = message.substring( start, end );
                PacketFragment packetFragment = new PacketFragment();
                packetFragment.setData( data );
                packetFragment.setFragmented( true );
                packetFragment.setIndex( i );
                fragments.add( packetFragment );
            }

            // reste
            packetCount = (reste != 0)? packetCount + 1 : packetCount;
            if (reste != 0)
            {
                PacketFragment packetFragment = new PacketFragment();
                packetFragment.setIndex( packetCount - 1 );
                packetFragment.setFragmented( true );
                packetFragment.setData( message.substring( (packetCount - 1) * mtu , (packetCount - 1 ) * mtu + reste  ));
                fragments.add(packetFragment);
            }

            for (int i = 0; i < packetCount; i++)
            {
                fragments.get( i ).setPacketCount( packetCount );
                String j = Json.getInstance().Serialize( fragments.get( i ) );
                this.Send( j, receiverAddress, port );
            }
        }
        else
        {
            PacketFragment packetFragment = new PacketFragment();
            packetFragment.setFragmented( false );
            packetFragment.setIndex( -1 );
            packetFragment.setPacketCount( -1 );
            packetFragment.setData( message );
            String j = Json.getInstance().Serialize( packetFragment );
            this.Send( j, receiverAddress, port );
        }
    }
}
