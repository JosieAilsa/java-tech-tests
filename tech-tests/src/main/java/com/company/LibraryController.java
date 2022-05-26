package com.company;

import com.company.frontend.Colour;
import com.company.frontend.ConsoleDisplay;
import com.company.user.User;
import com.company.user.UserService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class LibraryController {
    private UserService userService;
    private LibraryService libraryService;

    public void start(){
        try {
            UserService userService = new UserService();
            LibraryService libraryService = new LibraryService();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<String> logInDetails = ConsoleDisplay.start();
       //If it's log in
       if(logInDetails.size() == 2){
               String username = logInDetails.get(0);
               String password = logInDetails.get(1);

           boolean isLogInSuccess = false;
           do{
              isLogInSuccess = userService.logIn(username, password);
              if(isLogInSuccess){
                  System.out.println(Colour.green(" Hi, " +  userService.getCurrentUser().getFirstName() + ". Welcome back!"));
              }
           } while (!isLogInSuccess);
       }
        //If it's a create account
       if(logInDetails.size() > 1){
           String firstName = logInDetails.get(0);
           String lastName = logInDetails.get(1);
           String username = logInDetails.get(2);
           String password = logInDetails.get(3);

       }

    }
}
