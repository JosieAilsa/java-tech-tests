package com.company.user;

import com.company.Book;

import java.util.ArrayList;
import java.util.Formatter;

public interface Admin {

    public Formatter getCurrentLibraryReport(ArrayList<Book> currentBookList);
    public void generateCSVReport();

}
