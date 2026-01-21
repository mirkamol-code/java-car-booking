package com.mirkamolcode.service;

import com.mirkamolcode.dao.UserDAO;
import com.mirkamolcode.model.User;

import java.util.UUID;

public class UserService {
    private UserDAO userDAO;

    public UserService() {
        this.userDAO = new UserDAO();
    }

    public User[] getAllUsers(){
        return userDAO.selectAllUsers();
    }

    public User getUserById(UUID uuid){
        return userDAO.selectUserById(uuid);
    }

    public boolean isUserExist(UUID uuid){
        return userDAO.selectUserById(uuid) != null;
    }
}
