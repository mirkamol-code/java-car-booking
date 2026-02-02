package com.mirkamolcode.dao;

import com.mirkamolcode.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserDAO {

    List<User> selectAllUsers();

    Optional<User> getUserById(UUID userId);
}

