package com.company.user;

import com.company.exceptions.UserNotFoundException;
import com.company.JSON.UsersJSONRepo;

import java.util.ArrayList;

public class UserRepository {
//TODO: Create user
    //1.  Read from JSON file to see if users exist
    //2. If not create new user with id as 1 add to allUsers ArrayList
    //3. Create a write to JSON method for after a new user is created, meaning we have an ongoing list of users
    //4. Log in method will loop through allUsers to check .userName and .password matches

    private ArrayList<User> allUsers;

    public UserRepository() {
        try {
            this.allUsers = UsersJSONRepo.readJSONUsers("/Users/Josie/java-tech-tests/tech-tests/data/users_output.json");
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

    public void createUser(String userName, String lastName, String firstName, String password){
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
    }

    User findUser (String userName, String password) throws UserNotFoundException {
        for(User user: allUsers) {
            if(user.getUserName().equals(userName) && user.getPassword().equals(password)) {
                return user;
            }
        }
        throw new UserNotFoundException();
    }
}
