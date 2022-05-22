package com.company.JSON;

import com.company.Book;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public abstract class LibraryJSONRepo extends JSONRepo {
    //writing books to Json library
    public static JSONArray createJSONArrayOfBooks(ArrayList<Book> currentBooks) {
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
        return jsArray;
    }
    public static void writeJSONArrayOfBooks(ArrayList<Book> currentBooks){
        JSONArray currentBooksJSON = createJSONArrayOfBooks(currentBooks);
        writeJSONArray(currentBooksJSON, "/Users/Josie/java-tech-tests/tech-tests/data/books_data.csv");
    }

    public static ArrayList<Book> readJSONLibrary(String path) throws IOException {
        ArrayList<Book> listOfBooks = new ArrayList<Book>();
        try {
            ArrayList<Object> listdata = readListFromJSON(path);
            //Loop through each item in ArrayList and create primitives to add to Book constructor
            for (int i = 0; i < listdata.size(); i++) {
                //Cast the current object to a map
                Map currentBook = (Map) listdata.get(i);
                listOfBooks.add(readBook(currentBook));
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            throw new IOException("No file found");
        }
        return listOfBooks;

    }
    public static Book readBook (Map currentBook) {
        //Create a JSON object to add all the values to
        JSONObject currentJSONObject = new JSONObject(currentBook);
        //Grab each of the keys from the object
        String title = (String) currentJSONObject.get("title");
        String author = (String) currentJSONObject.get("author");
        Long longString = (Long) currentJSONObject.get("id");
        int id = longString.intValue();
        String genre = (String) currentJSONObject.get("genre");
        String subGenre = (String) currentJSONObject.get("subGenre");
        String publisher = (String) currentJSONObject.get("publisher");
        boolean isLoaned = (boolean) currentJSONObject.get("isLoaned");
        return new Book(id, title, author, genre, subGenre, publisher, isLoaned);
    }
}
