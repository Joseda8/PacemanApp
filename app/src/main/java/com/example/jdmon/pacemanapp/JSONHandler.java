package com.example.jdmon.pacemanapp;


import org.json.JSONException;
import org.json.JSONObject;

public class JSONHandler {

    public static String set_coords(float x, float y) throws JSONException {

        JSONObject json_coordenadas = new JSONObject();

        json_coordenadas.put("x",x);
        json_coordenadas.put("y",y);

        return json_coordenadas.toString();
    }


}