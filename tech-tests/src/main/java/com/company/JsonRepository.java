package com.company;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class JsonRepository {

    public static void createJsonLibrary(ArrayList<Book> currentBooks) {
        JSONArray jsArray = new JSONArray();
        for (int i = 0; i < currentBooks.size(); i++) {
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
        System.out.println("JSON file created: " + jsArray);
    }

    public static ArrayList<Book> readJson() throws IOException {
        ArrayList<Book> listOfBooks = new ArrayList<Book>();
        try {
            JSONParser parser = new JSONParser();
            //Use JSONObject for simple JSON and JSONArray for array of JSON.
            JSONArray data = (JSONArray) parser.parse(
                    new FileReader("/Users/Josie/java-tech-tests/tech-tests/data/library_output.json"));//path to the JSON file.
            //Creating an empty ArrayList of type of Book
            ArrayList<Object> listdata = new ArrayList<Object>();
            //Check if not  null
            if (listdata != null) {
                listdata.addAll(data);
            }
            //Book list to return
            for (int i = 0; i < listdata.size(); i++) {
                //Cast the current object to a map
                Map current = (Map) listdata.get(i);
                JSONObject currentJsonObject = new JSONObject(current);
                String title = (String) currentJsonObject.get("title");
                String author = (String) currentJsonObject.get("author");
                Long longString = (Long) currentJsonObject.get("id");
                int id = longString.intValue();
                String genre = (String) currentJsonObject.get("genre");
                String subGenre = (String) currentJsonObject.get("subGenre");
                String publisher = (String) currentJsonObject.get("publisher");
                boolean isLoaned = (boolean) currentJsonObject.get("isLoaned");
                Book newBook = new Book(id, title, author, genre, subGenre, publisher, isLoaned);
                listOfBooks.add(newBook);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return listOfBooks;

//        ObjectMapper objectMapper = new ObjectMapper();
//        List<Book> bookList = objectMapper.readValue(
//                new File("/Users/Josie/java-tech-tests/tech-tests/data/library_output.json"),
//                new TypeReference <List<Book>> (){});
//
//        bookList.forEach(x -> System.out.println(x.toString()));


    }
}

