package ru.job4j.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    Map<FileProperty, Path> mapFiles = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProp = new FileProperty(Files.size(file), file.getFileName().toString());
        if (mapFiles.containsKey(fileProp)) {
            System.out.println(mapFiles.get(fileProp).toAbsolutePath());
            System.out.println(file.toAbsolutePath());
        } else {
            mapFiles.put(new FileProperty(Files.size(file), file.getFileName().toString()), file);
        }
        return super.visitFile(file, attrs);
    }
}
