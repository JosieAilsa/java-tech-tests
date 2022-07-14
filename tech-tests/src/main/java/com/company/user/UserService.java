package com.company.user;

import com.company.Book;
import com.company.exceptions.UserNotFoundException;
import com.company.frontend.Colour;
import com.company.frontend.ConsoleDisplay;

import java.util.ArrayList;
import java.util.Arrays;

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

    public void writeCurrentUsers(){
        userRepository.updateUsers("/Users/Josie/java-tech-tests/tech-tests/data/users_output.json");
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
    public boolean loanBookToCurrentUser(Book requestedBook){
        int bookId = requestedBook.getId();
        ArrayList<Integer> currentUserIds = currentUser.getLoanedIds();
        if(currentUserIds.add(bookId)){
            return true;
        };
        return false;
    }
    public boolean returnCurrentUserBook(Book requestedBook){
        int bookId = requestedBook.getId();
        ArrayList<Integer> currentUserIds = currentUser.getLoanedIds();
        if(currentUserIds.remove(bookId) == bookId){
            return true;
        };
        return false;
    };


    @Override
    public boolean logIn(String userName, String password) throws UserNotFoundException {
           try {
               this.currentUser = userRepository.findUser(userName, password);
               this.currentUser.setIsLoggedIn(true);
               return true;
           } catch (UserNotFoundException unfe){
               System.out.println(Colour.red(unfe.getMessage()));
               ConsoleDisplay.errorMessaage("Your log in details look wrong. Try again");
               throw new UserNotFoundException();
           }
    }

    @Override
    public void logOut() {
            currentUser.setIsLoggedIn(false);
            userRepository.updateUsers("/Users/Josie/java-tech-tests/tech-tests/data/users_output.json");
    }

    public void createUser(ArrayList<String> createUserDetails){
        String firstName = createUserDetails.get(0);
        String lastName = createUserDetails.get(1);
        String username = createUserDetails.get(2);
        String password = createUserDetails.get(3);
        this.currentUser =  userRepository.createUser(firstName,lastName,username,password);
    }

    @Override
    public String toString() {
        return "UserService{" +
                "currentUser=" + currentUser +
                '}';
    }
}

