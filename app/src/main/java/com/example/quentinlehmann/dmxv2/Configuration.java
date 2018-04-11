package com.example.quentinlehmann.dmxv2;

import android.content.Context;
import android.view.Display;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class Configuration extends BaseModel {

    public String[] getTargetType () {
        return new String[] {"Projecteur", "Lyre"};
    }

    private String type;
    private String address;
    private String hostname;
    private String port;

    public Configuration ()
    {

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

    public void Sauvegarder () throws IOException {
        
        FileOutputStream outputStream = ParametreGlobaux.getCurrentInstance().openFileOutput( "Sauvegarder.json", Context.MODE_PRIVATE );
        outputStream.write( Json.getInstance().Serialize( this ).getBytes() );
        if (outputStream!=null)
            outputStream.close();
        Toast.makeText(ParametreGlobaux.getCurrentInstance() ,  "Sauvegarder", Toast.LENGTH_LONG).show();
    }

    public class Person {
        private String nom;
        private int age;

        public void setAge(int age) {
            this.age = age;
        }

        public int getAge() {
            return age;
        }

        public String getNom() {
            return nom;
        }

        public void setNom(String nom) {
            this.nom = nom;
        }

        @Override
        public String toString() {
            return "Nom: " + this.getNom() + "; Age: " + this.getAge();
        }
    }

}
