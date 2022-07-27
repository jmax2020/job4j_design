package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;
    private List<String> botAnswersList = new ArrayList<>();
    private List<String> log = new LinkedList<>();

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        Scanner input = new Scanner(System.in);
        String head = "поболтаем? выберите:";
        log.add(head);
        System.out.println(head);
        String answer = "";
        while (!OUT.equals(answer)) {
            showMenu();
            answer = input.nextLine();
            log.add("answer: " + answer);
            if (CONTINUE.equals(answer)) {
                String botAnswer = getRandomAnswer();
                System.out.println(botAnswer);
                log.add(botAnswer);
            }
            if (STOP.equals(answer)) {
               continue;
            }
            saveLog(log);
        }
    }

    private void showMenu() {
        System.out.println(CONTINUE);
        log.add(CONTINUE);
        System.out.println(STOP);
        log.add(STOP);
        System.out.println(OUT);
        log.add(OUT);
    }

    private String getRandomAnswer() {
        if (botAnswersList.size() == 0) {
            readPhrases();
        }
        Random rand = new Random();
        return botAnswersList.get(rand.nextInt(botAnswersList.size()));
    }

    private void readPhrases() {
        try (BufferedReader buffer = new BufferedReader(new FileReader(botAnswers, Charset.forName("Windows-1251")))) {
            String line = buffer.readLine();
            while (line != null) {
                botAnswersList.add(line);
                line = buffer.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveLog(List<String> log) {
        try (BufferedWriter bufWriter = new BufferedWriter(new PrintWriter(path, Charset.forName("Windows-1251")))) {
            for (String line : log) {
                bufWriter.append(line);
                bufWriter.append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("C:\\projects\\job4j_design\\ChatLog.txt", "C:\\projects\\job4j_design\\BotAnswers.txt");
        cc.run();
    }
}

