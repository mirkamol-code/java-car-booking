package com.mirkamolcode.dao;

import com.mirkamolcode.model.User;

import java.util.*;

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
    public Optional<User> getUserById(UUID userId) {
        return selectAllUsers().stream()
                .filter(user -> user.getId().equals(userId))
                .findFirst();
    }
}
