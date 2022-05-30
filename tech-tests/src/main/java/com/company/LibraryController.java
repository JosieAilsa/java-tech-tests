package com.company;

import com.company.exceptions.UserNotFoundException;
import com.company.frontend.Colour;
import com.company.frontend.ConsoleDisplay;
import com.company.user.User;
import com.company.user.UserRepository;
import com.company.user.UserService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class LibraryController {
    private UserService userService;
    private UserRepository userRepository;
    private LibraryService libraryService;

    public void start(){
        try {
            this.userService = new UserService();
            this.libraryService = new LibraryService();
        } catch (IOException e) {
            e.printStackTrace();
        }
        handleUserLogIn();
    }

    public void handleUserLogIn(){
        ArrayList<String> logInDetails = ConsoleDisplay.start();
        //If it's log in
        if(logInDetails.size() == 2){
            handleLogIn(logInDetails);
        }
        //If it's a create account
        if(logInDetails.size() > 3){
            userService.createUser(logInDetails);
        }
    }

    private void handleLogIn(ArrayList<String> logInDetails){
        String username = logInDetails.get(0);
        String password = logInDetails.get(1);

        boolean isLogInSuccess = false;
        do{

            try{
                isLogInSuccess = userService.logIn(username, password);
                ConsoleDisplay.welcomeMessage(userService.getCurrentUser().getFirstName());
            }catch(UserNotFoundException unfe){
                handleUserLogIn();
            }
        } while (!isLogInSuccess);
    }

}
