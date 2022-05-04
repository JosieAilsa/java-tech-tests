package com.company;

import java.util.ArrayList;

public class User<Books> {
    public String userName;

    private int id;
    private String firstName;
    private String lastName;
    private String password;
    private ArrayList<Books> currentLoanedBooks = null;

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Books> getCurrentLoanedBooks() {
        return currentLoanedBooks;
    }

    public void setCurrentLoanedBooks(ArrayList<Books> currentLoanedBooks) {
        this.currentLoanedBooks = currentLoanedBooks;
    }
}
