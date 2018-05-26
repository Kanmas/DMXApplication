package com.example.quentinlehmann.dmxv2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;

/**
 * Gère la sérialisation JSON
 */
public class Json {

    /**
     * Objet servant à générer les objets que créé les JSON
     */
    private GsonBuilder builder;
    /**
     * Sert à sérialiser les objets en trame JSON
     */
    private Gson gson;

    /**
     * Instance statique pour le singleton
     */
    private static Json instance;

    /**
     * Renvoie l'instance de Json
     * @return jsonHandler
     */
    public static Json getInstance() {
        if (instance == null) instance = new Json();
        return instance;
    }

    /**
     * Initilise une nouvelle instance de Json. Le constructeur est privé pour restreindre le nombre d'instances à 1.
     */
    private Json () {
        builder = new GsonBuilder();
        gson = builder.create();
    }

    /**
     * Sérialise un objet en Json
     * @param o
     * @return json
     */
    public String Serialize(Object o){
        return gson.toJson(o);
    }

    /**
     * Desérialise une trame Json
     * @param str
     * @param type
     * @return o
     */
    public Object Deserialize (String str, Type type) {
        return gson.fromJson(str, type);
    }
}
