package com.revature.project0.services;

import com.revature.project0.models.User;
import com.revature.project0.repo.UserRepository;
import com.sun.jdi.request.InvalidRequestStateException;

import javax.naming.AuthenticationException;
import java.util.*;

import static com.revature.project0.AppDriver.app;

public class UserService {

    private UserRepository userRepo;

    public UserService(UserRepository repo) {
//        System.out.println("[LOG] - instantiating " + this.getClass().getName());
        userRepo = repo;
    }

    public User authenticate(String username, String password)  {

        Optional<User> _authUser = (userRepo.findUserByCredentials(username, password));
        //if the user isn't present in the persistence layer, throw an exception. Otherwise set the current user to the provided user credentials' user.
        if (!_authUser.isPresent()) {

            System.err.println("invalid user");
        } else {
            app.setCurrentUser(_authUser.get());

        }

        return _authUser.get();
    }


    // register customer
    public void register(User newUser) {

        if (!isUserValid(newUser)) {
            app.invalidateCurrentSession();
            throw new InvalidRequestStateException("Invalid user field values provided during registration!");
        }

        Optional<User> _existingUser = userRepo.findUserByUsername(newUser.getUsername());
        if (_existingUser.isPresent()) {
            throw new RuntimeException("Provided username is already in use!");
        }

        userRepo.save(newUser);
//        System.out.println(newUser);
        app.setCurrentUser(newUser);

    }



//    public Set<User> getAllUsers() {
//        return new HashSet<>();
//    }
//
//    public User getUserByID(int id) {
//        return null;
//    }
//
//    public User getUserByUsername(String username) {
//        return null;
//    }
//
//    public boolean deleteUserById(int id) {
//        return false;
//    }
//
//    public boolean update(User updatedUser) {
//        return false;
//    }

    // Validates that the given user and its fields are valid.
    public boolean isUserValid(User user) {
        if (user == null) return false;
        if (user.getFirstName() == null || user.getFirstName().trim().equals("")) return false;
        if (user.getLastName() == null || user.getLastName().trim().equals("")) return false;
        if (user.getUsername() == null || user.getUsername().trim().equals("")) return false;
        if (user.getPassword() == null || user.getPassword().trim().equals("")) return false;
        return true;
    }

}
