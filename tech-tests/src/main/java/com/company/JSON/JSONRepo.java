package com.company.JSON;

import java.io.*;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.util.ArrayList;

 abstract class JSONRepo {

     /**
      * Reads JSON from a given absolute file path
      * @param path File path of JSON Array
      * @return An ArrayList of Objects from JSON
      */

     static ArrayList<Object> readListFromJSON(String path) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        //Use JSONObject for simple JSON and JSONArray for array of JSON.
        JSONArray data = (JSONArray) parser.parse(
                new FileReader(path));
        //Cast to ArrayList of type object
        return new ArrayList<Object>(data);
    }


     /**
      * Writes to JSON when given a JSONArray to given absolute file path
      * @param jsonArray JSONArray to write
      * @param path path where to write JSON array
      * @return void
      */
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

