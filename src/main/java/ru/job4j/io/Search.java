package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        validateArgs(args);
        args[0] = "test";
        Path start = Paths.get(args[0]);
        List<Path> list =  search(start, p -> p.toFile().getName().endsWith(args[1]));
        list.forEach(System.out::println);
    }
    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    private static void validateArgs(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("You must enter 2 arguments!");
        }
        Path path = Path.of(args[0]);
        if (!Files.exists(path) || !Files.isDirectory(path)) {
            throw new IllegalArgumentException("Illegal argument #1! You must enter the destination");
        }
        if (!args[1].contains(".")) {
            throw new IllegalArgumentException("Illegal argument #2! You must enter the file extension");
        }
    }
}