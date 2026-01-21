package com.mirkamolcode.model;

import java.util.UUID;

public class User {
    private final UUID id = UUID.randomUUID();
    private String name;

    public User(String name) {
        this.name = name;
    }

    public User() {
    }

    public UUID getId() {
        return id;
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
                "uuid=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
