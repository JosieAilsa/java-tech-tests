package com.company.user;

import java.util.ArrayList;

public class AdminUser extends User{

    public AdminUser(String userName, int id, String firstName, String lastName, String password) {
        super(userName, id, firstName, lastName, password);
    }

    public AdminUser(String userName, int id, String firstName, String lastName, String password, ArrayList<Integer> currentIDBooks) {
        super(userName, id, firstName, lastName, password, currentIDBooks);
    }





}
