package com.mirkamolcode.dao;

import com.mirkamolcode.model.Car;
import com.mirkamolcode.model.User;
import com.mirkamolcode.model.enums.Brand;

import java.util.UUID;

public class UserArrayDAO implements UserDAO{
    private static User[] users;
    static {
        users = new User[]{
                new User(UUID.randomUUID(), "Joe"),
                new User(UUID.randomUUID(), "Stan"),
                new User(UUID.randomUUID(), "Ollayor"),
                new User(UUID.randomUUID(), "Salah"),
        };
    }
    @Override
    public User[] selectAllUsers() {
        return users;
    }

    @Override
    public User getUserById(UUID userId) {
        for (User user : selectAllUsers()) {
            if (user.getId().equals(userId)){
                return user;
            }
        }
        return null;
    }

    @Override
    public boolean isUserExist(UUID id) {
        return getUserById(id) != null;
    }
}
