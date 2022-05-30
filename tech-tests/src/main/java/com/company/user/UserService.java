package com.company.user;

import com.company.exceptions.UserNotFoundException;
import com.company.frontend.Colour;
import com.company.frontend.ConsoleDisplay;

import java.util.ArrayList;

public class UserService implements AuthService {

    private UserRepository userRepository = new UserRepository();
    private User currentUser;


    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }


    @Override
    public boolean logIn(String userName, String password) throws UserNotFoundException {
           try {
               userRepository.findUser(userName, password);
               currentUser.setIsLoggedIn(true);
               return true;
           }catch (UserNotFoundException unfe){
               System.out.println(Colour.red(unfe.getMessage()));
               ConsoleDisplay.errorMessaage("Your log in details look wrong");
               throw new UserNotFoundException();
           }

    }

    @Override
    public void logOut() {
            currentUser.setIsLoggedIn(false);
    }

    public void createUser(ArrayList<String> createUserDetails){
        String firstName = createUserDetails.get(0);
        String lastName = createUserDetails.get(1);
        String username = createUserDetails.get(2);
        String password = createUserDetails.get(3);
        userRepository.createUser(firstName,lastName,username,password);
    }

}
