package com.company.user;

public interface AuthService {

    public boolean logIn(String userName, String password);
    public void logOut();

}
