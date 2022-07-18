package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        walkFile("./");
    }

    public static void walkFile(String catalog) throws IOException {
        DuplicatesVisitor visitor = new DuplicatesVisitor();
        Files.walkFileTree(Path.of(catalog), visitor);
        visitor.arrayDuplicates.forEach(System.out::println);
    }
}
