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

    public void start() {
        try {
            this.userService = new UserService();
            this.libraryService = new LibraryService();
        } catch (IOException e) {
            e.printStackTrace();
        }
        handleUserLogIn();
        int option;
        do {
            System.out.println(Colour.white("Main menu"));
            option = ConsoleDisplay.getMainMeuOption();
            String userInput = "";
            if (option == 1) {
                userInput = ConsoleDisplay.getInputFromMessage("Which book would you like to loan?");
            }
            if (option == 2) {
                userInput = ConsoleDisplay.getInputFromMessage("Which book would you like to return?");
            }
            if (option == 3) {
                System.out.println(Colour.yellow("The books you currently have on load are:"));
            }
            if (option == 4) {
                userInput = ConsoleDisplay.getInputFromMessage("Are you sure you would like to log ou? Y or N");
            }
        } while (option != 5);
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

    private void handleLogIn (ArrayList<String> logInDetails){
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
    private void handleLoanBook (String title){
        Book requestedBook = libraryService.loanBook(title);
        if(requestedBook != null){
            User currentUser = userService.getCurrentUser();
            ArrayList<Book> currentBooks = currentUser.getCurrentLoanedBooks();
            currentBooks.add(requestedBook);
            currentUser.setCurrentLoanedBooks(currentBooks);
        }
    }

}
