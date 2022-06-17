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
            JSONObject jsonObject = new JSONObject();
            User user = users.get(i);
            jsonObject.put("id", user.getId());
            jsonObject.put("firstName", user.getFirstName());
            jsonObject.put("lastName", user.getLastName());
            jsonObject.put("username", user.getUsername());
            jsonObject.put("password", user.getPassword());
            jsonObject.put("isLoggedIn", user.getIsLoggedIn());

//            ArrayList<Book> currentUserBookList = user.getCurrentLoanedBooks();
//            JSONArray currentBookListJSON = LibraryJSONRepo.createJSONArrayOfBooks(currentUserBookList);
//            jsonObject.put("currentLoanedBooks", currentBookListJSON.toJSONString());
//            jsArray.add(jsonObject);
            ArrayList<Integer> currentUserBooks = user.getLoanedIds();
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
//                ArrayList<Book> currentUserBooks = new ArrayList<Book>();
//                String arrayOfBooks = (String) currentJSONObject.get("currentLoanedBooks");
//                JSONParser parser = new JSONParser();
//                try {
//                    Object object = (Object) parser.parse(arrayOfBooks);
//                    JSONArray jsonArray = (JSONArray) object;
//                    for(int j=0; j<jsonArray.size();j++) {
//                        Map currentMap = (Map) jsonArray.get(i);
//                        Book currentbook = readBook(currentMap);
//                        currentUserBooks.add(currentbook);
//                    }
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }

//                User newUser = new User(username,id,firstName,lastName,password,currentUserBooks);
                //Try to use
                //Create array for bookids
                ArrayList<Integer> currentUserIdBooks = new ArrayList<Integer>();
                JSONArray arrayOfIds = (JSONArray) currentJSONObject.get("loanedIds");
                for(int j = 0;j < arrayOfIds.size(); j ++ ){
                    Long currentVal = (Long) arrayOfIds.get(i);
                    currentUserIdBooks.add(Math.toIntExact(currentVal));
                    System.out.println(arrayOfIds.get(i));
                }
//                for (String arrayOfId : (Iterable<String>) arrayOfIds) {
//                    currentUserIdBooks.add(Integer.parseInt(arrayOfId));
//                }
                User newUser = new User(username,id,firstName,lastName,password,currentUserIdBooks);
                users.add(newUser);
            }
            // Add the object to the json arr
        return users;
}


}