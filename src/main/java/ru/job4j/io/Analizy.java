package ru.job4j.io;

import java.io.*;
import java.util.List;

public class Analizy {

    public void unavailable(String source, String target) {
        StringBuilder strBild = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            List<String> list = in.lines().toList();
            boolean bad = false;
            for (String el : list) {
                String[] mass = el.split(" ");
                if ("400".equals(mass[0]) || "500".equals(mass[0])) {
                    if (!bad) {
                        bad = true;
                        strBild.append(mass[1]);
                        strBild.append(";");
                    }
                } else {
                    if (bad) {
                        bad = false;
                        strBild.append(mass[1]);
                        strBild.append(";");
                        strBild.append(System.lineSeparator());
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            out.println(strBild);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
        public static void main(String[] args) {
        Analizy analiz = new Analizy();
        analiz.unavailable("server.log", "unavailable.csv");
    }
}
