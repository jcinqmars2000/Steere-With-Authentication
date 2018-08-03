package com.steereengineering.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.steereengineering.model.PhoneList;
import java.util.List;

public class JsonConverter {
    
    private final Gson gson;
    
    public JsonConverter() {
        
        gson = new GsonBuilder().create();
    }

    public String convertToJson(List<PhoneList> phonelist) {
        
        JsonArray jarray = gson.toJsonTree(phonelist).getAsJsonArray();
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("phonelist", jarray);

        return jsonObject.toString();
    }
}