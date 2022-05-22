package com.company.Exceptions;

import java.util.NoSuchElementException;

public class UserNotFoundException extends NoSuchElementException {
    public UserNotFoundException(){
        super("No user exsits with that user name or password!");
    }
}
