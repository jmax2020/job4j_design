package ru.job4j.generic;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RoleStoreTest {

    @Test
    public void whenAddAndFindNameIsMax() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Max"));
        Role rsl = store.findById("1");
        assertThat(rsl.getName(), is("Max"));
    }

    @Test
    public void whenAddAndFindRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Max"));
        Role rsl = store.findById("2");
        assertNull(rsl);
    }

    @Test
    public void whenAddDuplicateAndFindNameIsMax() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Max"));
        store.add(new Role("1", "Bob"));
        Role rsl = store.findById("1");
        assertThat(rsl.getName(), is("Max"));
    }

    @Test
    public void whenNoReplaceRole() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Max"));
        store.replace("1", new Role("4", "Bob"));
        Role rsl = store.findById("1");
        assertThat(rsl.getName(), is("Bob"));
    }

    @Test
    public void whenNoReplaceRoleThenNoChangeName() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Max"));
        store.replace("2", new Role("2", "Bob"));
        Role rsl = store.findById("1");
        assertThat(rsl.getName(), is("Max"));
    }

    @Test
    public void whenDeleteRoleThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Max"));
        store.delete("1");
        Role rsl = store.findById("1");
        assertNull(rsl);
    }

    @Test
    public void whenNoDeleteRoleThenNameIsMax() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Max"));
        store.delete("2");
        Role rsl = store.findById("1");
        assertThat(rsl.getName(), is("Max"));
    }
}