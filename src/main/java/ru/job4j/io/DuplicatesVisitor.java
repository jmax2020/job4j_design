package ru.job4j.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    Map<FileProperty, Path> mapFiles = new HashMap<>();
    List<String> arrayDuplicates = new LinkedList<>() {
    };

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProp = new FileProperty(Files.size(file), file.getFileName().toString());
        if (mapFiles.containsKey(fileProp)) {
            arrayDuplicates.add(mapFiles.get(fileProp).toAbsolutePath().toString());
            arrayDuplicates.add(file.toAbsolutePath().toString());
        } else {
            mapFiles.put(new FileProperty(Files.size(file), file.getFileName().toString()), file);
        }
        return super.visitFile(file, attrs);
    }
}
