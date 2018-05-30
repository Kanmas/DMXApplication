package com.example.quentinlehmann.dmxv2;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Inet4Address;


public class ConfigurationOld extends BaseModel {

    public String[] getTargetType () {
        return new String[] {"Projecteur", "Lyre"};
    }

    private String type;
    private String address;
    private String hostname;
    private String port;

    private File mFile = null;
    private static ConfigurationOld currentInstance;
    private static void setCurrentInstance (ConfigurationOld ConfigurationOld) {
        ConfigurationOld.currentInstance = ConfigurationOld;
    }
    public static ConfigurationOld getCurrentInstance () {
        if (currentInstance == null) currentInstance = new ConfigurationOld();
        return currentInstance;
    }

    public ConfigurationOld ()
    {

    }

    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            //Toast.makeText( Changer_Couleur.getCurrentInstance(), "memoire externe dispo", Toast.LENGTH_LONG ).show();
            return true;

        }
        return false;
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

    public void SauvegarderCC () throws IOException {

        try{
        FileOutputStream outputStream = Changer_Couleur.getCurrentInstance().openFileOutput( "ParamètreCouleur.json", Context.MODE_PRIVATE );
        outputStream.write( Json.getInstance().Serialize( this ).getBytes() );
        if (outputStream!=null)
            outputStream.close();
        Toast.makeText(Changer_Couleur.getCurrentInstance() ,  "Sauvegarder", Toast.LENGTH_LONG).show();}
        catch (Exception e){
            Toast.makeText( Bienvenue.getCurrentInstance(), e.toString(), Toast.LENGTH_LONG ).show();
        }
    }

    public void SauvegarderSB () throws IOException {

        try{
            FileOutputStream outputStream = ParametreNewSb.getCurrentInstance().openFileOutput( "ParamètreCouleurSB.json", Context.MODE_PRIVATE );
            outputStream.write( Json.getInstance().Serialize( this ).getBytes() );
            if (outputStream!=null)
                outputStream.close();
            Toast.makeText(ParametreNewSb.getCurrentInstance() ,  "Sauvegarder", Toast.LENGTH_LONG).show();}
        catch (Exception e){
            Toast.makeText( ParametreNewSb.getCurrentInstance(), e.toString(), Toast.LENGTH_LONG ).show();
        }
    }
}

