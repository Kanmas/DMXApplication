package com.example.quentinlehmann.dmxv2;

import java.net.Inet4Address;

/**
 * Created by quentin.lehmann on 28/03/2018.
 */

public class ModeleParametreGlobaux {

    public interface PropertyChangedListener
    {
        void OnPropertyChanged (String propertyName);
    }

    public String[] getTargetType () {
        return new String[] {"Projecteur", "Lyre"};
    }

    private PropertyChangedListener onPropertyChanged;

    private String type;
    private String address;
    private String hostname;
    private String port;

    public ModeleParametreGlobaux ()
    {

    }

    public PropertyChangedListener getOnPropertyChanged() {
        return onPropertyChanged;
    }

    public void setOnPropertyChanged(PropertyChangedListener onPropertyChanged) {
        this.onPropertyChanged = onPropertyChanged;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        if (this.type != type) {
            this.type = type;
            NotifyPropertyChanged("Type");
        }
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (this.address != address) {
            this.address = address;
            NotifyPropertyChanged("Address");
        }
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        if (this.hostname != hostname) {
            this.hostname = hostname;
            NotifyPropertyChanged("Hostname");
        }
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        if (this.port != port) {
            this.port = port;
            NotifyPropertyChanged("Port");
        }
    }

    private void NotifyPropertyChanged (String propertyName) {
        if (onPropertyChanged != null)
            getOnPropertyChanged().OnPropertyChanged(propertyName);
    }

}
