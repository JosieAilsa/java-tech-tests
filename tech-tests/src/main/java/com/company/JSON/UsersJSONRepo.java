package com.company.JSON;

import com.company.Book;
import com.company.user.User;
import com.fasterxml.jackson.core.JsonParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import static com.company.JSON.LibraryJSONRepo.readBook;

public abstract class UsersJSONRepo extends JSONRepo {
    public static void createJSONUsers(ArrayList<User> users, String path) {
        JSONArray jsArray = new JSONArray();
        for (int i = 0; i < users.size(); i++) {
            //Create obj for each iser
            JSONObject jsonObject = new JSONObject();
            User user = users.get(i);
            jsonObject.put("id", user.getId());
            jsonObject.put("firstName", user.getFirstName());
            jsonObject.put("lastName", user.getLastName());
            jsonObject.put("username", user.getUsername());
            jsonObject.put("password", user.getPassword());
            jsonObject.put("isLoggedIn", user.getIsLoggedIn());
            ArrayList<Integer> currentUserBooks = user.getLoanedIds();
            //Create JSON array to add all book to
            JSONArray bookIds = new JSONArray();
            bookIds.addAll(currentUserBooks);
            jsonObject.put("loanedIds", bookIds);
            jsArray.add(jsonObject);

        }
        writeJSONArray(jsArray, path);
    }

    public static ArrayList<User> readJSONUsers(String path) throws IOException, ParseException {
        //Create the array list to return
        ArrayList<User> users = new ArrayList<User>();
            ArrayList<Object> listdata = readListFromJSON(path);
            for (int i = 0; i < listdata.size(); i++) {
                //Cast the current object to a map to access for values
                Map current = (Map) listdata.get(i);
                JSONObject currentJSONObject = new JSONObject(current);
                String firstName = (String) currentJSONObject.get("firstName");
                String lastName = (String) currentJSONObject.get("lastName");
                Long longString = (Long) currentJSONObject.get("id");
                int id = longString.intValue();
                String username = (String) currentJSONObject.get("username");
                String password = (String) currentJSONObject.get("password");
                JSONArray arrayOfIds = (JSONArray) currentJSONObject.get("loanedIds");
                ArrayList<Integer> currentUserIdBooks = getUserBooksIDs(arrayOfIds);
                User newUser = new User(username,id,firstName,lastName,password,currentUserIdBooks);
                users.add(newUser);
            }
        return users;
}
private static ArrayList<Integer> getUserBooksIDs(JSONArray booksFromJSONArray){
        ArrayList<Integer> currentUserIdBooks = new ArrayList<Integer>();
        for(int i = 0;i < booksFromJSONArray.size(); i ++ ){
        Long currentID = (Long) booksFromJSONArray.get(i);
        currentUserIdBooks.add(currentID.intValue());
    }
        return currentUserIdBooks;
}


}