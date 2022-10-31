package ru.job4j.searchfile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.io.ArgsName;
import ru.job4j.io.Search;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class SearchFilesByCriteria {

    private static final Logger LOG = LoggerFactory.getLogger(SearchFilesByCriteria.class.getName());
    public static List<Path> findFiles(String typeFind, String mask, String source) {
        List<Path> rsl = new ArrayList<>();
        Path resource = Paths.get(source);
        if ("name".equals(typeFind)) {
            try {
                rsl = Search.search(resource, e -> mask.equals(e.getFileName().toString()));

            } catch (IOException e) {
                LOG.error("KOSYAK s parametrom name", e);
            }
        }
        if ("mask".equals(typeFind) || "regex".equals(typeFind)) {
            try {
                PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:" + mask);
                rsl = Search.search(resource, e -> matcher.matches(e.getFileName()));
            } catch (IOException e) {
                LOG.error("KOSYAK s parametrom mask", e);
            }
        }
        return rsl;
    }

    public static void writeToFile(List<Path> listFiles, String destination) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(destination))) {
            for (Path el : listFiles) {
                bw.write(el.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            LOG.error("KOSYAK with writeToFile", e);
        }
    }
    private static void validate(ArgsName argsName) {
        String err;
        if (!Files.exists(Path.of(argsName.get("d")))) {
            err = "Incorrect argument d!";
            LOG.error(err, new IllegalArgumentException(err));
        }
        String typeSearch = argsName.get("t");
        if (!"name".equals(typeSearch) && !"mask".equals(typeSearch) && !"regex".equals(typeSearch)) {
            err = "Incorrect argument t! Must contain 'name' or 'mask' or 'regex'!";
            LOG.error(err, new IllegalArgumentException(err));
        }
        String argO = argsName.get("o");
        if (argO.startsWith(".") || argO.split("\\.").length > 2) {
            err = "Incorrect argument o!";
            LOG.error(err, new IllegalArgumentException(err));
        }
    }

    public static void main(String[] args) throws IOException {
        ArgsName argsname =  ArgsName.of(args);
        SearchFilesByCriteria.validate(argsname);
        List<Path> listFiles = SearchFilesByCriteria.findFiles(argsname.get("t"), argsname.get("n"), argsname.get("d"));
        SearchFilesByCriteria.writeToFile(listFiles, argsname.get("o"));
    }
}
