package com.company.exceptions;

import java.util.NoSuchElementException;

public class UserNotFoundException extends NoSuchElementException {
    public UserNotFoundException(){
        super("No user exsits with that user name or password!");
    }
}