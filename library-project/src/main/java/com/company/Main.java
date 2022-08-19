package com.company;
import com.company.library.LibraryController;
import com.company.library.LibraryService;
import com.company.user.AdminService;
import com.company.user.UserService;

import java.io.IOException;

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
