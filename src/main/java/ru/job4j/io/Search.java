package ru.job4j.io;

import jdk.jshell.EvalException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        validateArgs(args);
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
        if (args.length < 2 || args[0].length() == 0 || args[1].length() == 0) {
            throw new IllegalArgumentException("Can't find arguments!");
        }
    }
}