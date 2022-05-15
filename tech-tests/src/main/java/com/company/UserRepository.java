package com.company;

import com.company.jsonrepos.UsersJSONRepo;

import java.util.ArrayList;

public class UserRepository implements AuthService {
//TODO: Create user
    //1.  Read from JSON file to see if users exist
    //2. If not create new user with id as 1 add to allUsers ArrayList
    //3. Create a write to JSON method for after a new user is created, meaning we have an ongoing list of users
    //4. Log in method will loop through allUsers to check .userName and .password matches

    private ArrayList<User> allUsers;

    public UserRepository() {
        try {
            this.allUsers = UsersJSONRepo.readJSONUsers("");
        }catch (Exception e){
            this.allUsers = new ArrayList<User>();
        }
    }

    public void setAllUsers(ArrayList<User> allUsers) {
        this.allUsers = allUsers;
    }
    public ArrayList<User> getAllUsers() {
        return allUsers;
    }

    @Override
    public boolean logIn(String userName, String password) {
        return false;
    }

    @Override
    public boolean logOut() {
        return false;
    }

    public void createUser(String userName, String lastName, String firstName, String password){
            //Use the user number as the id
            int newId = allUsers.size();
            //Create a new user
            User currentUser = new User(userName,newId,firstName,lastName,password);
            //Add to array of users
            allUsers.add(currentUser);

    }

    private User findUser (String userName, String password){
        for(User user: allUsers) {
            if(user.getUserName().equals(userName) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
}
