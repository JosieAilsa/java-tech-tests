package com.company;

import com.company.user.User;
import com.company.user.UserService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
//////        // write your code here
//        LibraryController libraryController = new LibraryController();
//        libraryController.start();
        CsvRepository csv = new CsvRepository();
        ArrayList<Book> books = csv.readFromCSV("/Users/Josie/java-tech-tests/tech-tests/data/books_data.csv");
        System.out.println(books.toString());


//        UserService userService = new UserService();
////
//        String[] createUserDetails = {"Josie", "Newman", "josienew", "password"};
//        ArrayList<String> userDetails = new ArrayList(Arrays.asList(createUserDetails));
//        LibraryService libraryService = new LibraryService();
//        libraryService.loanBook("Slaughterhouse Five");
//        Book userBook = libraryService.findBook("Slaughterhouse Five", libraryService.currentBookList);
//        userService.createUser(userDetails);
//        User josie = userService.getCurrentUser();
//        ArrayList<Book> currentBooks = josie.getCurrentLoanedBooks();
//        currentBooks.add(userBook);
//        System.out.println(userBook.getId());
//        josie.addBookToUserLoanList(userBook.getId());
//        josie.setCurrentLoanedBooks(currentBooks);
//        userService.logOut();

    }
}
