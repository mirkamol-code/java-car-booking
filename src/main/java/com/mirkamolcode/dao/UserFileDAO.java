package com.mirkamolcode.dao;

import com.mirkamolcode.model.User;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

public class UserFileDAO implements UserDAO {
    private static final URI FILE_PATH;
    static {
        try {
            FILE_PATH = Objects.requireNonNull(UserFileDAO.class
                            .getClassLoader()
                            .getResource("users.csv"))
                    .toURI();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }


    private static final File file = new File(FILE_PATH);

    @Override
    public List<User> selectAllUsers() {
        return getUsersFromFileToList();
    }

    @Override
    public Optional<User> getUserById(UUID id) {
        return selectAllUsers().stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }

    private List<User> getUsersFromFileToList() {
        List<User> users = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String userLine;
            while ((userLine = bufferedReader.readLine()) != null) {
                UUID userId = UUID.fromString(userLine.substring(0, 36));
                String userName = userLine.substring(38);
                User user = new User(userId, userName);

                users.add(user);
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        return users;
    }
}
