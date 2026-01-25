package com.mirkamolcode.service;

import com.mirkamolcode.dao.UserDAO;
import com.mirkamolcode.model.User;

import java.util.UUID;

public class UserService {
    private final UserDAO userDAO;

    public UserService(UserDAO users) {
        this.userDAO = users;
    }

    public User[] getAllUsers() {
        return userDAO.selectAllUsers();
    }

    public void printAllUsers() {
        for (User user : getAllUsers()) {
            if (user != null) {
                System.out.println(user);
            }
        }
    }

    public User getUserById(UUID userId) {
        return userDAO.getUserById(userId);
    }

    public boolean isUserPresent(UUID userId) {
        return userDAO.isUserExist(userId);
    }

}
