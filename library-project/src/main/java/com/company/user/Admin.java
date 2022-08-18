package com.company.user;

import com.company.Book;

import java.util.ArrayList;
import java.util.Formatter;

public interface Admin {

    public Formatter getAllBooksOnLoan (ArrayList<Book> currentBookList);
    public void getCurrentLibraryReport();
    public void generateCSVReport();

}
