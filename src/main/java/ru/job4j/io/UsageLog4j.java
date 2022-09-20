package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Max Programmist";
        int age = 35;
        float weight = 99.6f;
        double height = 191.5;
        boolean man = true;
        long countCar = 1;
        byte education = 1;
        short countChildren = 2;
        char symbol = 'M';

        LOG.debug("User info name : {}, age : {}, weight : {}, height : {}, "
                + "man : {}, countCar : {}, education : {}, countChildren : {},"
                + "countChildren : {} ", name, age, weight, height, man, countCar, education,
                countChildren, symbol);
    }
}
