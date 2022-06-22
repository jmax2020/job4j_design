package ru.job4j.map;

import java.util.*;

public class User {
    private String name;
    private int children;
    private int height;

    @Override
    public int hashCode() {
        int rsl;
        rsl = 31 * name.hashCode();
        rsl = 31 * rsl + children;
        rsl = 31 * rsl + height;
        return rsl;
    }

    public User(String name, int children, int height) {
        this.name = name;
        this.children = children;
        this.height = height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return children == user.children && Objects.equals(name, user.name) && height == user.height;
    }
}
