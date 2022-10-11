package ru.job4j.serialization.json;

import ru.job4j.serialization.java.Contact;

import java.util.Arrays;

public class Man {
    private final boolean sex;
    private final String  name;
    private final Contact contact;
    private final int[]  yearsOfEducation;

    public Man(boolean sex, String name, Contact contact, int[] yearsOfEducation) {
        this.sex = sex;
        this.name = name;
        this.contact = contact;
        this.yearsOfEducation = yearsOfEducation;
    }

    @Override
    public String toString() {
        return "Man{"
                + "sex=" + sex
                + ", name='" + name + '\''
                + ", contact=" + contact
                + ", yearsOfEducation=" + Arrays.toString(yearsOfEducation)
                + '}';
    }


}
