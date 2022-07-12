package ru.job4j.io;

import java.io.*;

public class Analizy {

    public void unavailable(String source, String target) {
        try (BufferedReader in = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            String line = in.readLine();
            boolean bad = false;
            while (line != null) {
                String[] mass = line.split(" ");
                if ("400".equals(mass[0]) || "500".equals(mass[0])) {
                    if (!bad) {
                        bad = true;
                        out.print(mass[1] + ";");
                    }
                } else {
                    if (bad) {
                        bad = false;
                       out.print(mass[1] + ";" + System.lineSeparator());
                    }
                }
                line = in.readLine();
            }
        }  catch (IOException e) {
            e.printStackTrace();
        }

    }
        public static void main(String[] args) {
        Analizy analiz = new Analizy();
        analiz.unavailable("server.log", "unavailable.csv");
    }
}
