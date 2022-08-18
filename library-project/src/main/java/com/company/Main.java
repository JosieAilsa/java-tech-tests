package com.company;

import com.company.frontend.ConsoleDisplay;
import com.company.library.LibraryController;
import com.company.library.LibraryService;
import com.company.user.AdminService;
import com.company.user.User;
import com.company.user.UserService;
import com.company.utils.ObjectUtils;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        UserService userService = null;
        LibraryService libraryService = null;
        AdminService adminService = null;
        try {
            userService = new UserService();
            libraryService = new LibraryService();
            adminService = new AdminService();
        } catch (IOException e) {
            e.printStackTrace();
        }
        LibraryController libraryController = new LibraryController(userService, libraryService, adminService);
        libraryController.start();
    }
}
