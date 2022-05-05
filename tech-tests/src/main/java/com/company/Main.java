package com.company;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
	// write your code here
        Scanner scanner = new Scanner(System.in);
        System.out.println("Request book:");
        String bookTitle = scanner.nextLine();
        com.company.LibraryService libraryService = new com.company.LibraryService();
        if(libraryService.loanBook(bookTitle)){
            System.out.println("Book loaned");
        } else {
            System.out.println("Error");
        };
        libraryService.writeCurrentLibrary();
    }
}
