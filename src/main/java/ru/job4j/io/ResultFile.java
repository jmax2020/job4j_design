package ru.job4j.io;

import java.io.FileOutputStream;

public class ResultFile {

    public static String matrix(int size) {
        StringBuilder buff = new StringBuilder();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                buff.append((i + 1) * (j + 1));
                buff.append('\t');
            }
            buff.append(System.lineSeparator());
        }
        return buff.toString();
    }

    public static void main(String[] args) {
        String mass = matrix(10);
        System.out.println(mass);
        try (FileOutputStream out = new FileOutputStream("test_result.txt")) {
            out.write(mass.getBytes());
            out.write(System.lineSeparator().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
