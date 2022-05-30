package com.company.user;

import com.company.exceptions.UserNotFoundException;

public interface AuthService {

    public boolean logIn(String userName, String password) throws UserNotFoundException;
    public void logOut();

}
