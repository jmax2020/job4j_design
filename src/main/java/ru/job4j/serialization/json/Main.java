package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.serialization.java.Contact;

public class Main {
    public static void main(String[] args) {
        final Man man = new Man(true, "Max",
                new Contact(555, "777-77-77"),
                new int[] {2004, 2009, 2013});

        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(man));

        final String jsonMan =
                "{"
                        + "\"sex\":false,"
                        + "\"name\":Bob,"
                        + "\"contact\":"
                        + "{"
                        + "\"zipCode\":\"999\","
                        + "\"phone\":\"555-55-55\""
                        + "},"
                        + "\"yearsOfEducation\":"
                        + "[\"2000\",\"2020\"]"
                        + "}";
        final Man jsonMan2 = gson.fromJson(jsonMan, Man.class);
        System.out.println(jsonMan2);
    }
}
