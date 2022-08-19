package com.company.library;

import com.company.Book;
import com.company.exceptions.UserNotFoundException;
import com.company.frontend.Colour;
import com.company.frontend.ConsoleDisplay;
import com.company.user.AdminService;
import com.company.user.UserService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

public class LibraryController {
    private UserService userService;
    private LibraryService libraryService;
    private AdminService adminService;

    // create a constructor
    public LibraryController(UserService userService, LibraryService libraryService, AdminService adminService) {
        this.userService = userService;
        this.libraryService = libraryService;
        this.adminService = adminService;
    }

    public void start() {
        handleUserLogIn();
        if(userService.isCurrentUserAdmin()){
            handleAdminMainMenu();
        } else{
            handleMainMenu();
        };

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
        System.out.println(Colour.red("Please request a different book"));
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

    private void handleMainMenu () {
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
                if (userInput.toLowerCase(Locale.ROOT).equals("y")){
                    System.out.println("Goodbye!");
                    option = 5;
                }
            }
        } while (option != 5);
    }

    private void handleAdminMainMenu(){
        int option;
        do {
            System.out.println(Colour.white("Main menu"));
            option = ConsoleDisplay.getAdminMainMenuOptions();
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
                continue;
            }
            if (option == 4) {
                showCurrentLibrary();
                continue;
            }
            if (option == 6) {
                userInput = ConsoleDisplay.getInputFromMessage("Are you sure you would like to log out? Y or N");
                if (userInput.toLowerCase(Locale.ROOT).equals("y")){
                    System.out.println("Goodbye!");
                    option = 5;
                }
            }
        } while (option != 6);
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

    private void showCurrentLibrary(){

        System.out.println(Colour.blue((adminService.getCurrentLibraryReport(libraryService.getCurrentBookList()))));
    }

    private String  handleShowUserLoanedBooks(){
        StringBuilder titleList = new StringBuilder();
        ArrayList<Integer> currentUserLoans = userService.getCurrentUser().getLoanedIds();
        for(int i = 0; i < currentUserLoans.size(); i++) {
            Book currentBook = libraryService.findBook(currentUserLoans.get(i));
            String currentTitle = currentBook.getTitle();
            if (currentUserLoans.size() == 1 && i == 0) {
                titleList.append(currentTitle);
            } else if (i == currentUserLoans.size() - 2) {
                titleList.append(currentTitle);
            } else if (i == currentUserLoans.size() - 1) {
                titleList.append(" and ").append(currentTitle);
            } else {
                titleList.append(currentTitle).append(", ");
            }
        }
        return titleList.toString().isEmpty()? "You've got no books on loan at the moment!": titleList.toString();
    }



}
