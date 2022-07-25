package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(
                new FileOutputStream(target.getAbsolutePath())))) {
            for (Path path : sources) {
                String address = path.toAbsolutePath().toString();
                System.out.println(address);
                zip.putNextEntry(new ZipEntry(address));
                if (!Files.isDirectory(path)) {
                    try (BufferedInputStream out = new BufferedInputStream(
                            new FileInputStream(address))) {
                        zip.write(out.readAllBytes());
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(
                new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(
                    new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void validate(ArgsName argsName) {
        if (!Files.exists(Path.of(argsName.get("d")))) {
            throw new IllegalArgumentException("Incorrect argument d!");
        }
        String argE = argsName.get("e");
        if (!argE.startsWith(".") || argE.split("\\.").length > 2) {
            throw new IllegalArgumentException("Incorrect argument e!");
        }
        Path destination = Path.of(argsName.get("o")).toAbsolutePath().getParent();
        if (!Files.exists(destination)) {
            throw new IllegalArgumentException("Incorrect argument o!");
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length < 3) {
            throw new IllegalArgumentException("You must to enter 3 parameters!");
        }
        Zip zip = new Zip();
        ArgsName argsName = ArgsName.of(args);
        zip.validate(argsName);
        List<Path> list = Search.search(Path.of(argsName.get("d")), a -> !a.toString().endsWith(argsName.get("e")));
        zip.packFiles(list, new File(argsName.get("o")));
    }
}
