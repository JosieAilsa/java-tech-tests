package com.company.JSON;

import java.io.*;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.util.ArrayList;

 abstract class JSONRepo {

    //Reads JSON and returns an ArrayList of Objects
        static ArrayList<Object> readListFromJSON(String path) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        //Use JSONObject for simple JSON and JSONArray for array of JSON.
        JSONArray data = (JSONArray) parser.parse(
                new FileReader(path));
        //Cast to ArrayList of type object
        return new ArrayList<Object>(data);
    }


    //Private method for writing either Books/Users to JSON
    static void writeJSONArray(JSONArray jsonArray, String path){
        try {
            FileWriter file = new FileWriter(path);
            file.write(jsonArray.toJSONString());
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("JSON file created: " + jsonArray);
    }
}

