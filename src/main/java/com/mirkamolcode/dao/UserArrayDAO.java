package com.mirkamolcode.dao;

import com.mirkamolcode.model.User;

import java.util.UUID;

public class UserArrayDAO implements UserDAO{
    @Override
    public User[] selectAllUsers() {
        return new User[0];
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
