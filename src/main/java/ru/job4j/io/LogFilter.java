package ru.job4j.io;

import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class LogFilter {
    public List<String> filter(String file) {
        List<String> list = null;
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            list = in.lines().collect(Collectors.toList());
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                String[] elements = it.next().split(" ");
                if (!"404".equals(elements[elements.length - 2])) {
                    it.remove();
                }
            }
        } catch (
                FileNotFoundException e) {
            e.printStackTrace();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(file)
                ))) {
            for (String el : log) {
                out.println(el);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("log.txt");
        save(log, "output.txt");
        log.forEach(System.out::println);
    }
}
