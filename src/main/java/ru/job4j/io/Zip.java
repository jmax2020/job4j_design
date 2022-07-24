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

    private void validate(String[] args) {
        if (args.length < 3) {
            throw new IllegalArgumentException("You must to enter 3 parameters!");
        }
        boolean existD = false;
        boolean existE = false;
        boolean existO = false;
        for (String arg : args) {
            if (arg.contains("d=")) {
                String[] tails = arg.split("=", 2);
                if (!Files.exists(Path.of(tails[1]))) {
                    throw new IllegalArgumentException("Incorrect argument d!");
                }
                existD = true;
            }
            if (arg.contains("e=")) {
                if (!arg.contains("e=.") || !(arg.split("\\.").length == 2)) {
                    throw new IllegalArgumentException("Incorrect argument e!");
                }
                existE = true;
            }
            if (arg.contains("o=")) {
                String[] tails = arg.split("=", 2);
                    Path destination = Path.of(tails[1]).toAbsolutePath().getParent();
                    if (!Files.exists(destination)) {
                    throw new IllegalArgumentException("Incorrect argument o!");
                }
                existO = true;
            }
        }
        if (!existD) {
            throw new IllegalArgumentException("The source destination is not specified!");
        }
        if (!existE) {
            throw  new IllegalArgumentException("Exception file extensions are not specified!");
        }
        if (!existO) {
            throw new IllegalArgumentException("The target is not specified!");
        }
    }

    public static void main(String[] args) throws IOException {
        Zip zip = new Zip();
        zip.validate(args);
        ArgsName argsName = ArgsName.of(args);
        List<Path> list = Search.search(Path.of(argsName.get("d")), a -> !a.toString().endsWith(argsName.get("e")));
        zip.packFiles(list, new File(argsName.get("o")));
    }
}
