package com.mirkamolcode.dao;

import com.github.javafaker.Faker;
import com.mirkamolcode.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.IntStream;

public class UserFakerDAS implements UserDAO {
    @Override
    public List<User> selectAllUsers() {
        Faker faker = new Faker();
        return IntStream.range(0, 20)
                .mapToObj(i -> {
                    System.out.println(i);
                    return new User(UUID.randomUUID(), faker.name().fullName());
                })
                .toList();
    }

    @Override
    public Optional<User> getUserById(UUID userId) {
        return selectAllUsers().stream()
                .filter(user -> user.getId().equals(userId))
                .findFirst();
    }
}
