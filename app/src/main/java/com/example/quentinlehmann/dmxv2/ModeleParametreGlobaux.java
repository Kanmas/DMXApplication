package com.example.quentinlehmann.dmxv2;

import android.arch.lifecycle.Lifecycle;
import android.widget.Toast;

import java.net.Inet4Address;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by quentin.lehmann on 28/03/2018.
 */

public class ModeleParametreGlobaux {

    private String type;
    private String adress;
    private Inet4Address hostname;
    private int port;

    public List<IEvent> subs = new LinkedList<IEvent>(  );

    private void NotifyPropertyChanged (String name)
    {
        for (IEvent sub : subs)
        {
            sub.onPropertyChange( name );
        }
    }

    // IEvent onPropertyChanged (type)

    public void Sauvegarde ()
    {

    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        if (this.type != type) {
            this.type = type;
            NotifyPropertyChanged( "Type" );
        }
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        if (this.adress != adress) {
            this.adress = adress;
            NotifyPropertyChanged( "Adress" );
        }
    }

    public Inet4Address getHostname() {
        return hostname;
    }

    public void setHostname(Inet4Address hostname) {
        if (this.hostname != hostname) {
            this.hostname = hostname;
            NotifyPropertyChanged( "Hostname" );
        }
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        if (this.port != port) {
            this.port = port;
            NotifyPropertyChanged( "Port" );
        }
    }

    public interface IEvent
    {

        void onPropertyChange (String nom);
    }

}
