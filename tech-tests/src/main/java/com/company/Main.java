package com.company;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
	// write your code here
        Scanner scanner = new Scanner(System.in);
        System.out.println("Request book:");
        String bookTitle = scanner.nextLine();
        LibraryService libraryService = new LibraryService();
        if(libraryService.loanBook(bookTitle)){
            System.out.println("Book loaned");
        } else {
            System.out.println("Error");
        };


    }
}
