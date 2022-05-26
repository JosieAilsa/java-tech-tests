package com.company.user;

import com.company.exceptions.UserNotFoundException;
import com.company.frontend.Colour;

public class UserService implements AuthService {

    private UserRepository userRepository = new UserRepository();
    private User currentUser;

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    @Override
    public boolean logIn(String userName, String password){
        try {
            this.currentUser = userRepository.findUser(userName, password);
            currentUser.setIsLoggedIn(true);
            return true;
        } catch(UserNotFoundException e){
            System.out.println(Colour.red(e.getMessage()));
            return false;
        }

    }

    @Override
    public void logOut() {
        try {
            currentUser.setIsLoggedIn(false);
        } catch(UserNotFoundException e){
        }
    }

}
