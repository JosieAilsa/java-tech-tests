package com.company.User;

import com.company.Exceptions.UserNotFoundException;

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
            return true;
        } catch(UserNotFoundException e){
            return false;
        }

    }

    @Override
    public boolean logOut() {
        return false;
    }
    public void updateUserBookList(Book book, int userId){
    }

}
