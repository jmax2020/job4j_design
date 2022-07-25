package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("No such argument " + key + "!");
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        validate(args);
        for (String arg : args) {
            String[] parts = arg.substring(1).split("=", 2);
            values.put(parts[0], parts[1]);
        }
    }

    private void validate(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Can't find arguments!");
        }
        for (String arg : args) {
            if (!arg.contains("=")) {
                throw new IllegalArgumentException("Incorrect arguments format ! Arguments must contain '='!");
            }
            if (!arg.startsWith("-")) {
                throw new IllegalArgumentException("Incorrect arguments format! Arguments must begin with '-'!");
            }
            if (arg.startsWith("-=")) {
                throw new IllegalArgumentException("Incorrect arguments format! Arguments must contain key!");
            }
            if (arg.endsWith("=") && arg.split("=").length == 1) {
                throw new IllegalArgumentException("Incorrect arguments format! Arguments must contain value!");
            }
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
