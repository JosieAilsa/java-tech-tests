package com.company;

import java.util.ArrayList;




public class User implements AuthService {
    private String userName;
    private int id;
    private boolean isLoggedIn;
    private String firstName;
    private String lastName;
    private String password;
    private ArrayList<Book> currentLoanedBooks;

    public User(String userName, int id, String firstName, String lastName, String password) {
        this.userName = userName;
        this.id = id;
        this.isLoggedIn = false;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.currentLoanedBooks = new ArrayList<Book>();
    }


    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserName() {
        return userName;
    }

    public boolean getIsLoggedIn() {
        return isLoggedIn;
    }

    public void setIsLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
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

    @Override
    public boolean logIn(String userName, String password) {
        if(userName.equals(this.userName) && password.equals(this.password)){
            setLoggedIn(true);
            return true;
        }
        return false;
    }

    @Override
    public boolean logOut() {
        if(this.isLoggedIn){
            setLoggedIn(false);
            return true;
        }
        return false;
    }
}
