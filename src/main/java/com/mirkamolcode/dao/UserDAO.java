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

    public User selectUserById(UUID userId) {
        for (User user : users) {
            if (user.getId().equals(userId)) {
                return user;
            }
        }
        return null;
    }

}

