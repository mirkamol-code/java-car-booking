package com.mirkamolcode.dao;

import com.mirkamolcode.model.User;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

public interface UserDAO {
    String FILE_PATH = "src/main/java/com/mirkamolcode/users.csv";

    User[] getAllUsers(File file);

    User getUserById(UUID userId);

    default boolean isUserExist(UUID uuid){
        return getUserById(uuid) != null;
    }

    default void writeUsersToFile(File file){
        try {
            FileWriter fileWriter = new FileWriter(file);
            PrintWriter writer = new PrintWriter(fileWriter);
            writer.println("7e4b9220-a47a-45a7-a33b-7182ee0dc30e, Leila");
            writer.println("0236e9db-8c46-45a1-8fef-718d12e271f3, Bond");
            writer.println("43bf7ab5-1f20-4693-a4f0-7319a7926d66, Ali");
            writer.println("1fda7774-b948-42fa-ad35-7eb1a7248e35, Samira");

            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.getMessage();
        }
    }
}

