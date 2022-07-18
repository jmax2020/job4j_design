package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        walkFile("./");
    }

    public static void walkFile(String catalog) throws IOException {
        Files.walkFileTree(Path.of(catalog), new DuplicatesVisitor());
    }
}
