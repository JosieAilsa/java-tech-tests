package com.company.user;

import com.company.Book;
import com.company.frontend.ConsoleDisplay;

import java.util.ArrayList;
import java.util.Formatter;

public class AdminService implements Admin  {

    @Override
    public Formatter getCurrentLibraryReport(ArrayList<Book> currentBooks) {
        return ConsoleDisplay.createFiveColumnBookTable(currentBooks);
    }

    @Override
    public void generateCSVReport() {

    }


}
