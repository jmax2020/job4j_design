package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
        String[] filter = argsName.get("filter").split(",");
        List<Integer> select = new LinkedList<>();
        List<String> rsl = new LinkedList<>();
        try (Scanner scan = new Scanner(Path.of(argsName.get("path")))) {
            String stringLine;
            boolean itFirstLine = true;
            while (scan.hasNext()) {
                stringLine = scan.nextLine();
                StringJoiner joiner = new StringJoiner(argsName.get("delimiter"));
                String[] columns = stringLine.split(argsName.get("delimiter"));
                if (itFirstLine) {
                    for (String filtrum : filter) {
                        for (int numberColumn = 0; numberColumn < columns.length; numberColumn++) {
                            if (columns[numberColumn].equals(filtrum)) {
                                select.add(numberColumn);
                                joiner.add(columns[numberColumn]);
                            }
                        }
                    }
                    itFirstLine = false;
                    rsl.add(joiner.toString());
                    continue;
                }
                for (int numb : select) {
                    joiner.add(columns[numb]);
                }
                rsl.add(joiner.toString());
            }
            writeOUT(rsl, argsName.get("out"));
        }
    }

    private static void writeOUT(List<String> rsl, String target) {
        if ("stdout".equals(target)) {
            rsl.forEach(System.out::println);
        } else {
            try (BufferedWriter writer = new BufferedWriter(new PrintWriter(target))) {
                StringJoiner joiner = new StringJoiner(System.lineSeparator());
                for (String el : rsl) {
                    joiner.add(el);
                }
                writer.append(joiner.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void validate(ArgsName argsName) {
        Path source = Path.of(argsName.get("path"));
        if (!Files.exists(source) || !Files.isDirectory(source)) {
            throw new IllegalArgumentException("Illegal argument #1! You must enter the path");
        }
        if ("".equals(argsName.get("delimiter"))) {
            throw new IllegalArgumentException("Illegal argument #2! You must enter the delimiter");
        }
        Path dest = Path.of(argsName.get("out"));
        if (!"stdout".equals(argsName.get("out")) || !Files.exists(dest) || !Files.isDirectory(dest)) {
            throw new IllegalArgumentException("Illegal argument #3! You must enter the out");
        }
        if ("".equals(argsName.get("filter"))) {
            throw new IllegalArgumentException("Illegal argument #4! You must enter the filter");
        }
    }

    public static void main(String[] args) throws Exception {
        if (args.length < 4) {
            throw new IllegalArgumentException("You must enter 4 arguments!");
        }
        ArgsName argsName = ArgsName.of(args);
        validate(argsName);
        handle(argsName);
    }
}
