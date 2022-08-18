package com.company.user;

import com.company.Book;
import com.company.frontend.ConsoleDisplay;

import java.util.ArrayList;
import java.util.Formatter;

public class AdminService implements Admin  {
    @Override
    public Formatter getAllBooksOnLoan(ArrayList<Book> currentBookList) {
        return ConsoleDisplay.createTwoColumnBookTable(currentBookList);
    }

    @Override
    public void getCurrentLibraryReport() {

    }

    @Override
    public void generateCSVReport() {

    }


}
