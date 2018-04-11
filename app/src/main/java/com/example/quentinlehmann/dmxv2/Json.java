package com.example.quentinlehmann.dmxv2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;

/**
 * Created by quentin.lehmann on 27/03/2018.
 */

public class Json {

    private GsonBuilder builder;
    private Gson gson;

    private static Json instance;

    public static Json getInstance() {
        if (instance == null) instance = new Json();
        return instance;
    }

    private Json () {
        builder = new GsonBuilder();
        gson = builder.create();
    }

    public String Serialize(Object o){
        return gson.toJson(o);
    }

    public Object Deserialize (String str, Type type) {
        return gson.fromJson(str, type);
    }
}
