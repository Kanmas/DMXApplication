package com.example.quentinlehmann.dmxv2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONObject;

/**
 * Created by quentin.lehmann on 27/03/2018.
 */

public class Json {

    public static String Serialize(Object o){
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();

            return gson.toJson( o );
    }
}
