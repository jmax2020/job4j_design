package ru.job4j.serialization.json;

import ru.job4j.serialization.java.Contact;

public class MainXml {
    public static void main(String[] args) {
        final Man man = new Man(true, "Max",
                new Contact(555, "777-77-77"),
                new int[] {2004, 2009, 2013});

        String xmlMan =
                "<?xml version=\"1.1\" encoding=\"UTF-8\" ?>\n"
                        + "<man sex=\"true\" name=\"Max\">\n"
                        + "    <contact phone=\"777-77-77\"/>\n"
                        + "    <yearsOfEducation>\n"
                        + "        <yearOfEducation>2004</status>\n"
                        + "        <yearOfEducation>2009</status>\n"
                        + "        <yearOfEducation>2013</status>\n"
                        + "    </yearsOfEducation>\n"
                        + "</person>";
        System.out.println(xmlMan);
    }
}
