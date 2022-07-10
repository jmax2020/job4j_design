package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader in = new BufferedReader(new FileReader(path))) {
            List<String> list = in.lines()
                    .filter(e -> !e.contains("#") && e.length() > 0).toList();
            for (String element : list) {
                String[] parts = element.split("=");
                if (!(parts.length == 2) || "".equals(parts[0]) || "".equals(parts[1])) {
                    throw new IllegalArgumentException();
                }
                values.put(parts[0], parts[1]);
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public String value(String key) {
        return values.get(key);
    }

    public static void main(String[] args) {
        Config conf = new Config("conf.txt");
        conf.load();
    }
}
