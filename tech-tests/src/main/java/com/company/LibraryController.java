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
                handleRequestBook();
                continue;
            }
            if (option == 2) {
                handleReturnBook();
                continue;
            }
            if (option == 3) {
                System.out.println(Colour.blue(handleShowUserLoanedBooks()));
            }
            if (option == 4) {
                userInput = ConsoleDisplay.getInputFromMessage("Are you sure you would like to log out? Y or N");
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
    private void handleRequestBook(){
        String userInput = ConsoleDisplay.requestBookTitle("Which book would you like to loan");
        Book requestedBook = libraryService.loanBook(userInput);
        if(requestedBook != null){
            boolean isSuccessfulLoan = userService.loanBookToCurrentUser(requestedBook);
           if(isSuccessfulLoan) {
               System.out.println(Colour.blue("Successfully loaned " + userInput));
               libraryService.writeCurrentLibrary();
               userService.writeCurrentUsers();
               return;
           }

        }
        System.out.println(Colour.red("Looks like that book can't be loaned or isn't stocked, try again!"));

    }

    private void handleReturnBook(){
        String userInput = ConsoleDisplay.requestBookTitle("Which book would you like to return");
        //Check if the book exists in the library
        Book bookforReturning = libraryService.returnBook(userInput);
        if(bookforReturning != null){
            boolean isSuccessfulReturn = userService.returnCurrentUserBook(bookforReturning);
            if(isSuccessfulReturn){
                System.out.println(Colour.blue("Successfully returned " + userInput));
                return;
            };
            System.out.println(Colour.red("Whoops something went wrong please try again..."));
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
    private String  handleShowUserLoanedBooks(){
        StringBuilder titleList = new StringBuilder();
        ArrayList<Integer> currentUserLoans = userService.getCurrentUser().getLoanedIds();
        for(int i = 0; i < currentUserLoans.size(); i++){
            Book currentBook = libraryService.findBook(i+1);
            String currentTitle = currentBook.getTitle();
            ///If at first item and only one in the list add as is
            if(currentUserLoans.size() == 1 && i == 0){
                titleList.append(currentTitle);
            }else if(i == currentUserLoans.size()-2){
                titleList.append(currentTitle);
            } else if(i == currentUserLoans.size()-1){
                titleList.append(" and ").append(currentTitle);
            } else {
                titleList.append(currentTitle).append(", ");
            }
        }
        return titleList.toString();
    }

}
