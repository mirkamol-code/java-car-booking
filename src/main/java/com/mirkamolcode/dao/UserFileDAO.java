package com.mirkamolcode.dao;

import com.mirkamolcode.model.User;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.UUID;

public class UserFileDAO implements UserDAO {
    private static final String FILE_PATH = "src/main/java/com/mirkamolcode/users.csv";
    private static final File file = new File(FILE_PATH);

    @Override
    public User[] selectAllUsers() {
        try {
            return getUsersFromFileToArray();
        } catch (IOException e) {
            return new User[0];
        }
    }

    @Override
    public User getUserById(UUID id) {
        for (User user : selectAllUsers()) {
            if (user.getId().equals(id)){
                return user;
            }
        }
        return null;
    }

    @Override
    public boolean isUserExist(UUID id) {
        return getUserById(id) != null;
    }

    private User[] getUsersFromFileToArray() throws IOException {
        var count = countLine(file);
        var users = new User[count];
        var index = 0;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String userLine;
            while ((userLine = bufferedReader.readLine()) != null) {
                UUID userId = UUID.fromString(userLine.substring(0, 36));
                String userName = userLine.substring(38);
                User user = new User(userId, userName);

                users[index++] = user;
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        return users;
    }

    private int countLine(File file) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        int count = 0;
        while (bufferedReader.readLine() != null) {
            count++;
        }
        bufferedReader.close();
        return count;
    }
}
