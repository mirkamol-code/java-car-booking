package com.mirkamolcode.service;

import com.mirkamolcode.dao.UserDAO;
import com.mirkamolcode.model.User;

public class UserService {
    private UserDAO userDAO;

    public UserService() {
        this.userDAO = new UserDAO();
    }

    public void getAllUsers(){
        for (User user : userDAO.selectAllUsers()) {
            System.out.println(user);
        }
    }

    public User getUserById(String uuid){
        return userDAO.selectUserById(uuid);
    }
}
