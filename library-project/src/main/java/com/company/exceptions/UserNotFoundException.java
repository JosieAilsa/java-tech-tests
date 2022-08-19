package com.company.exceptions;

import java.io.FileNotFoundException;

public class UserNotFoundException extends FileNotFoundException {
    public UserNotFoundException(){
        super("No user exsits with that username or password!");
    }
}