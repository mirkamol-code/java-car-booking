package com.mirkamolcode.dao;

import com.mirkamolcode.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class UserArrayDAO implements UserDAO {
    private static  List<User> users = new ArrayList<>(
            Arrays.asList(
                    new User(UUID.randomUUID(), "Joe"),
                    new User(UUID.randomUUID(), "Stan"),
                    new User(UUID.randomUUID(), "Ollayor"),
                    new User(UUID.randomUUID(), "Salah")
            )
    );

    @Override
    public List<User> selectAllUsers() {
        return users;
    }

    @Override
    public User getUserById(UUID userId) {
        for (User user : selectAllUsers()) {
            if (user.getId().equals(userId)) {
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
