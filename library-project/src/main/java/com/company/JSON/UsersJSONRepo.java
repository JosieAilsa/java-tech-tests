package com.company.JSON;

import com.company.user.AdminUser;
import com.company.user.User;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
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
            if(user instanceof AdminUser){
                jsonObject.put("isAdmin", true);
            }
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
                User newUser;
                boolean isAdmin = checkIfJSONUserIsAdmin(currentJSONObject);
                if(isAdmin){
                    newUser = new AdminUser(username,id,firstName,lastName,password,currentUserIdBooks);
                } else {
                    newUser = new User(username,id,firstName,lastName,password,currentUserIdBooks);
                }
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

private static boolean checkIfJSONUserIsAdmin(JSONObject currentUser){
    try{
        boolean isAdmin = (boolean) currentUser.get("isAdmin");
        return isAdmin;
    }catch(NullPointerException npe){
        return false;
    }
}

}