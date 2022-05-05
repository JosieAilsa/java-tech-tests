package com.company;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;

public abstract class ArrayToJson {

    public static void getJsonLibrary(ArrayList<Book> currentBooks) {
        JSONArray jsArray = new JSONArray();
        for (int i = 0; i <currentBooks.size(); i++) {
            JSONObject jsonObject = new JSONObject();
            Book currentBook = currentBooks.get(i);
            jsonObject.put("genre", currentBook.getGenre());
            jsonObject.put("subGenre", currentBook.getSubGenre());
            jsonObject.put("publisher", currentBook.getPublisher());
            jsonObject.put("title", currentBook.getTitle());
            jsonObject.put("author", currentBook.getAuthor());
            jsonObject.put("isLoaned", currentBook.isLoaned());
            jsonObject.put("id", currentBook.getId());
            jsArray.add(jsonObject);
        }
        try {
            FileWriter file = new FileWriter("/Users/Josie/java-tech-tests/tech-tests/data/library_output.json");
            file.write(jsArray.toJSONString());
            file.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("JSON file created: "+jsArray);
    }
}
