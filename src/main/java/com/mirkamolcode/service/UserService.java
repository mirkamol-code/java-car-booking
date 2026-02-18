package com.mirkamolcode.service;

import com.mirkamolcode.dao.UserDAO;
import com.mirkamolcode.model.User;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import static com.mirkamolcode.model.enums.ResponseMessage.NO_USERS;
import static com.mirkamolcode.model.enums.ResponseMessage.UNKNOWN_USER;

public class UserService {
    private final UserDAO userDAO;

    public UserService(UserDAO users) {
        this.userDAO = users;
    }

    public List<User> getAllUsers() {
        return userDAO.selectAllUsers();
    }

    public User getUserById(UUID userId) {
        return userDAO.getUserById(userId)
                .orElseThrow(() -> new NoSuchElementException(UNKNOWN_USER.getMessage()));
    }

}
