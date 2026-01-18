package com.mirkamolcode.dao;

import com.mirkamolcode.model.User;

import java.util.UUID;

public class UserDAO {
    private static User[] users;
    private static int nextAvailableSlot = 0;
    private static final int CAPACITY = 1;

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

    public User selectUserById(String uuid) {
        UUID inputId = UUID.fromString(uuid);
        for (User user : users) {
            if (user.getUuid() == inputId) {
                return user;
            }
        }
        return null;
    }
}

