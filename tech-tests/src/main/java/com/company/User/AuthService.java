package com.company.User;

public interface AuthService {

    public boolean logIn(String userName, String password);
    public boolean logOut();

}
