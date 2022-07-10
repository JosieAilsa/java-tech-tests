package com.company.user;

import com.company.Book;
import com.company.frontend.Colour;

import java.util.ArrayList;




public class User {
    private String username;
    private int id;
    private boolean isLoggedIn;
    private String firstName;
    private String lastName;
    private String password;
    private ArrayList<Book> currentLoanedBooks;
    private ArrayList<Integer> loanedIds;

    //Constructor for when we want to create a new user
    public User(String userName, int id, String firstName, String lastName, String password) {
        this.username = userName;
        this.id = id;
        this.isLoggedIn = false;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.currentLoanedBooks = new ArrayList<Book>();
        this.loanedIds = new ArrayList<Integer>();
    }

//    //Constructor for reading from JSON
//    public User(String userName, int id, String firstName, String lastName, String password, ArrayList<Book> currentLoanedBooks) {
//        this.username = userName;
//        this.id = id;
//        this.isLoggedIn = false;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.password = password;
//        this.currentLoanedBooks = currentLoanedBooks;
//        this.loanedIds  = new ArrayList<Integer>();
//    }

    public User(String userName, int id, String firstName, String lastName, String password, ArrayList<Integer> currentIDBooks) {
        this.username = userName;
        this.id = id;
        this.isLoggedIn = false;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.loanedIds = currentIDBooks;
    }

    public void addBookToUserLoanList( Integer id){
        this.loanedIds.add(id);
    }

    public void setUsername(String userName) {
        this.username = userName;
    }
    public String getUsername() {
        return username;
    }

    public boolean getIsLoggedIn() {
        return isLoggedIn;
    }

    public void setIsLoggedIn(boolean loggedIn) {
        this.isLoggedIn = loggedIn;
    }

    public int getId() {
        return id;
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

    public ArrayList<Book> getCurrentLoanedBooks() {
        return currentLoanedBooks;
    }

    public void setCurrentLoanedBooks(ArrayList<Book> currentLoanedBooks) {
        this.currentLoanedBooks = currentLoanedBooks;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    public ArrayList<Integer> getLoanedIds() {
        return loanedIds;
    }

    public void setLoanedIds(ArrayList<Integer> loanedIds) {
        this.loanedIds = loanedIds;
    }


    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", id=" + id +
                ", isLoggedIn=" + isLoggedIn +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", currentLoanedBooks=" + currentLoanedBooks +
                '}';
    }
}
