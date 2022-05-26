package com.company.JSON;

import com.company.Book;
import com.company.user.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public abstract class UsersJSONRepo extends JSONRepo {
    public static void createJSONUsers(ArrayList<User> users, String path) {
        JSONArray jsArray = new JSONArray();
        for (int i = 0; i < users.size(); i++) {
            JSONObject jsonObject = new JSONObject();
            User user = users.get(i);
            jsonObject.put("id", user.getId());
            jsonObject.put("firstName", user.getFirstName());
            jsonObject.put("lastName", user.getLastName());
            jsonObject.put("userName", user.getUserName());
            jsonObject.put("password", user.getPassword());
            jsonObject.put("isLoggedIn", user.getIsLoggedIn());
            ArrayList<Book> currentUserBookList = user.getCurrentLoanedBooks();
            JSONArray currentBookListJSON = LibraryJSONRepo.createJSONArrayOfBooks(currentUserBookList);
            jsonObject.put("currentLoanedBooks", currentBookListJSON.toJSONString());
        }
        writeJSONArray(jsArray, path);
    }

    public static ArrayList<User> readJSONUsers(String path) throws IOException, ParseException {
        //Create the array list to return
        ArrayList<User> users = new ArrayList<User>();
        try {
            ArrayList<Object> listdata = readListFromJSON(path);
            //Loop through each item in ArrayList and create primitives to add to Book constructor
            for (int i = 0; i < listdata.size(); i++) {
                //Cast the current object to a map
                Map current = (Map) listdata.get(i);
                JSONObject currentJSONObject = new JSONObject(current);
                String firstName = (String) currentJSONObject.get("firstName");
                String lastName = (String) currentJSONObject.get("lastName");
                Long longString = (Long) currentJSONObject.get("id");
                int id = longString.intValue();
                String username = (String) currentJSONObject.get("username");
                String password = (String) currentJSONObject.get("password");
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return users;
}
}