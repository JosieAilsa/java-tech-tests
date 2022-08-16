package com.company.user;

import com.company.exceptions.UserNotFoundException;
import com.company.JSON.UsersJSONRepo;
import com.company.utils.FileUtils;

import java.util.ArrayList;

public class UserRepository {

    private ArrayList<User> allUsers;
    private String usersFilePath;

    public UserRepository() {
        this.usersFilePath = FileUtils.getRelativeFilePath() + "/src/data/users_output.json";
        try {
            this.allUsers = UsersJSONRepo.readJSONUsers(this.usersFilePath);
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

    public User createUser(String firstName,String lastName,String userName,String password){
        User currentUser = null;
        if(allUsers.size() == 0){
            currentUser = new User(userName,0,firstName,lastName,password);
        } else {
            //Use the user number as the id
            int newId = allUsers.size();
            //Create a new user
            currentUser = new User(userName,newId,firstName,lastName,password);
        }
        this.allUsers.add(currentUser);
        return currentUser;
    }

    User findUser (String username, String password) throws UserNotFoundException {
        for(User user: allUsers) {
            if(user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        throw new UserNotFoundException();
    }
    public void updateUsers(){
        UsersJSONRepo.createJSONUsers(this.allUsers,this.usersFilePath);
    }
}
