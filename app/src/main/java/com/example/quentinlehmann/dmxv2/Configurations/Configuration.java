package com.example.quentinlehmann.dmxv2.Configurations;

import com.example.quentinlehmann.dmxv2.BaseModel;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Représente la configuration de l'application.
 * Cette classe hérite de BaseModel et donc propose les fonctionnalitées de cette dernière.
 */
public class Configuration extends BaseModel{

    /**
     * Chaîne de caractères contenant le nom du champs gérant le nom d'hôte distant.
     * Sert lors de le réception des événements de changements d'états du champs en question.
     */
    public static final String HOSTNAME = "hostname";
    /**
     * Chaîne de caractères contenant le nom du chmaps gérant le port d'envoi de l'application.
     * Sert lors de la réception des événements de changements d'états du champs en question.
     */
    public static final String SEND_PORT = "sendPort";
    /**
     * Chaîne de caractères contenant le nom du champs gérant le port de réception de l'application.
     * Sert lors de la réception des événements de changements d'états du champs en question.
     */
    public static final String RECEIVE_PORT = "receivePort";
    /**
     * Chaîne de caractères contenant le nom du champs gérant l'adresse de la source cible.
     * Sert lors de la réception des événements de changements d'états du champs en question
     */
    public static final String TARGET_ADDRESS = "targetAddress";

    /**
     * Représente le nom d'hôte distant
     */
    private InetAddress hostname;
    /**
     * Représente le port d'envoi de cette configuration
     */
    private int sendPort;
    /**
     * Représente le port de réception de cette configuration
     */
    private int receivePort;
    /**
     * Représente la source cible de cette configuration
     */
    private int targetAddress;

    /**
     * Représente la configuration globale de l'application
     */
    private static Configuration instance;

    /**
     * Renvoie la Configuration globale de l'application
     * @return globalConfiguration
     */
    public static Configuration getInstance () {
        if (instance == null) instance = new Configuration();
        return instance;
    }

    /**
     * Initialise une nouvelle instance de la classe Configuration
     */
    public Configuration () {
        try {
            hostname = InetAddress.getByName("127.0.0.1");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        sendPort = 5000;
        targetAddress = 1;
        receivePort = 0;

    }

    /**
     * Initialise une nouvelle instance de Configuration avec les valeurs d'une autre instance
     * @param configuration
     */
    public Configuration (Configuration configuration) {
        hostname = configuration.hostname;
        sendPort = configuration.sendPort;
        receivePort = configuration.receivePort;
        targetAddress = configuration.targetAddress;
    }

    /**
     * Renvoie le nom d'hôte distant (adresse IP)
     * @return
     */
    public InetAddress getHostname() {
        return hostname;
    }

    /**
     * Renseigne le nom d'hôte distant (adresse IP)
     * @param hostname
     */
    public void setHostname(InetAddress hostname) {
        if (hostname == this.hostname) return;

        this.hostname = hostname;
        NotifyPropertyChanged(HOSTNAME);
    }

    /**
     * Renseigne le port d'envoie de l'application
     * @return sendPort
     */
    public int getSendPort() {
        return sendPort;
    }

    /**
     * Renseigne le port d'envoi de l'application
     * @param sendPort
     */
    public void setSendPort(int sendPort) {
        if (sendPort == this.sendPort) return;
        this.sendPort = sendPort;
        NotifyPropertyChanged(SEND_PORT);
    }

    /**
     * Renvoie le port de reception de l'application
     * @return receivePort
     */
    public int getReceivePort() {
        return receivePort;
    }

    /**
     * Renseigne le port de reception de l'application
     * @param receivePort
     */
    public void setReceivePort(int receivePort) {
        if (receivePort == this.receivePort) return;
        this.receivePort = receivePort;
        NotifyPropertyChanged(RECEIVE_PORT);
    }

    /**
     * Renvoie l'adresse de la source à contrôler
     * @return targetAddress
     */
    public int getTargetAddress() {
        return targetAddress;
    }

    /**
     * Renseigne l'adresse de la source à controler
     * @param targetAddress
     */
    public void setTargetAddress(int targetAddress) {
        if (targetAddress == this.targetAddress) return;
        this.targetAddress = targetAddress;
        NotifyPropertyChanged(TARGET_ADDRESS);
    }

    /**
     * Renseigne le nom d'hôte (adresse IP) distant
     * @param hostname
     */
    public void setHostname (String hostname) {
        try {
            setHostname(InetAddress.getByName(hostname));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Renseigne le port d'envoi de l'application
     * @param sendPort
     */
    public void setSendPort (String sendPort) {
        try {
            setSendPort(Integer.parseInt(sendPort));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Renseigne le port de réception de l'application
     * @param receivePort
     */
    public void setReceivePort (String receivePort) {
        try {
            setReceivePort(Integer.parseInt(receivePort));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Renseigne l'adresse de la source à contrôler
     * @param targetAddress
     */
    public void setTargetAddress (String targetAddress) {
        try {
            setTargetAddress(Integer.parseInt(targetAddress));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Retourne l'instance sous forme de chaîne de caractères
     * @return String
     */
    @Override
    public String toString() {
        return String.format("Hostname: %s; SendPort: %d; TargetAddress: %d;", hostname, sendPort, targetAddress);
    }

    /**
     * Applique une configuration locale à la configuration globale de l'application
     * @param configuration
     */
    public void ApplyConfiguration (Configuration configuration) {
        setHostname(configuration.hostname);
        setSendPort(configuration.sendPort);
        setReceivePort(configuration.receivePort);
        setTargetAddress(configuration.targetAddress);
    }

    /**
     * Cherche la configuration dans les fichiers de l'application,
     * Si il n'existe pas de configuration, le fichier sera créé avec
     * la configuration par default.
     */
    public void Initialize () {
        // TODO: implements
    }

    /**
     * Ecrit la configuration actuelle dans les fichiers de l'application
     */
    public void Write () {
        // TODO: implements
    }
}
