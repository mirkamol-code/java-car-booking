package com.mirkamolcode.service;

import com.mirkamolcode.dao.UserDAO;
import com.mirkamolcode.model.User;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.UUID;

public class UserService implements UserDAO {
    private static User[] users;
    private int capacity = 100;

    public UserService() {
        users = new User[capacity];
    }

    @Override
    public User[] getAllUsers(File file) {
        return users;
    }

    public void getUsersFromFileToArray(File file){
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            resizeIfNeeded(file);
            String userLine;
            while ((userLine = bufferedReader.readLine()) != null) {
                UUID userId = UUID.fromString(userLine.substring(0, 36));
                String userName = userLine.substring(38);
                User user = new User(userId, userName);

                int next = getNextAvailableSlot();
                users[next] = user;
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void resizeIfNeeded(File file) throws IOException {
        int countedLine = countLine(file);

        if (users.length < countedLine) {
            this.capacity = users.length + countedLine;
            User[] newArray = new User[this.capacity];
            System.arraycopy(users, 0, newArray, 0, users.length);
            users = newArray;
        }
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

    private int getNextAvailableSlot() {
        for (int i = 0; i < users.length; i++) {
            if (users[i] == null) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public User getUserById(UUID userId) {
        for (User user : users) {
            if ( user.getId().equals(userId)) {
                return user;
            }
        }
        return null;
    }


}
