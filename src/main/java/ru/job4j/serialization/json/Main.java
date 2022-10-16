package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONObject;
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
        System.out.println("================================");

        String jsonMan3 = new JSONObject(man).toString();
        System.out.println(jsonMan3);
        System.out.println("================================");

        JSONObject jsonMan4 = new JSONObject();
        jsonMan4.put("sex", man.isSex());
        jsonMan4.put("name", man.getName());
        JSONObject jContact = new JSONObject("{\"zipCode\":\"999\",\"phone\":\"555-55-55\"}");
        jsonMan4.put("contact", jContact);
        jsonMan4.put("yearsOfEducation", new JSONArray(man.getYearsOfEducation()));
        System.out.println(jsonMan4);
        System.out.println("================================");


    }
}
