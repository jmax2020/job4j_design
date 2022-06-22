package ru.job4j.map;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void testHashCode() {
        User max = new User("Max", 2, 190);
        User bob = new User("Bob", 1, 175);
        User max2 = new User("Max", 2, 190);
        assertFalse(max.hashCode() == bob.hashCode());
        assertTrue(max.hashCode() == max2.hashCode());
    }

    @Test
    public void testEquals() {
        User max = new User("Max", 2, 190);
        User bob = new User("Bob", 1, 175);
        User max2 = new User("Max", 2, 190);
        assertFalse(max.equals(bob));
        assertTrue(max.equals(max2));
    }
}