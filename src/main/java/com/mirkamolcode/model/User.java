package com.mirkamolcode.model;

import java.util.UUID;

public class User {
    private final UUID uuid = UUID.randomUUID();
    private String name;

    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "uuid=" + uuid +
                ", name='" + name + '\'' +
                '}';
    }
}
