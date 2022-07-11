package ru.job4j.io;

import org.hamcrest.Matchers;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./conf.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.username"), is("postgres"));
        assertThat(config.value("tratata"), is(Matchers.nullValue()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenError() {
        String path = "./app.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test
    public void whenTwoEqually() {
        String path = "./test.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("key"), is("value=1"));
    }
}