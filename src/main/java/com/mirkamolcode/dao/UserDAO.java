package com.mirkamolcode.dao;

import com.mirkamolcode.model.User;
import com.mirkamolcode.model.enums.ResponseMessage;

import java.util.UUID;

public class UserDAO {
    private static User[] users;

    static {
        users = new User[]{
                new User("Ali"),
                new User("Jamila"),
                new User("Ensieh"),
                new User("Yusuf")
        };
    }

    public User[] selectAllUsers() {
        return users;
    }

    public User selectUserById(String userId) {
        try {
            UUID inputId = UUID.fromString(userId);
            for (User user : users) {
                if (user.getId().equals(inputId)) {
                    return user;
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println(ResponseMessage.INVALID_OPTION.getMessage());
        }
        return null;
    }

}

