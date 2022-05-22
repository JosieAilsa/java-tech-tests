package com.company;

import com.company.User.User;

import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
	// write your code here
        com.company.LibraryService libraryService = new com.company.LibraryService();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Library. Would you like to create a new account");
        String createAccountAnswer = scanner.nextLine().toLowerCase(Locale.ROOT);
        if(createAccountAnswer.contains("yes")){
            System.out.println("Set username:");
            String userName= scanner.nextLine();
            System.out.println("Create a username:");
            String password = scanner.nextLine();
            System.out.println("Set your first name:");
            String firstName = scanner.nextLine();
            System.out.println("Set your last name:");
            String lastName = scanner.nextLine();
            User newUser = new User()
        }


        System.out.println("Request book:");
        String bookTitle = scanner.nextLine();
        if(libraryService.loanBook(bookTitle)){
            System.out.println("Book loaned");
        } else {
            System.out.println("Sorry this book is already out on loan :( ");
        };
    }
}
