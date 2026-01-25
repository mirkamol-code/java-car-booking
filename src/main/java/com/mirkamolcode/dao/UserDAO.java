package com.mirkamolcode.dao;

import com.mirkamolcode.model.User;

import java.io.File;
import java.util.UUID;

public interface UserDAO {

    User[] selectAllUsers();

    User getUserById(UUID userId);
    boolean isUserExist(UUID id);
}

