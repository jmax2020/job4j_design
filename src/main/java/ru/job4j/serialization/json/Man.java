package ru.job4j.serialization.json;

import ru.job4j.serialization.java.Contact;

import javax.xml.bind.annotation.*;
import java.util.Arrays;

@XmlRootElement(name = "man")
@XmlAccessorType(XmlAccessType.FIELD)
public class Man {
    @XmlAttribute
    private boolean sex;
    @XmlAttribute
    private String  name;

    private Contact contact;
    @XmlElementWrapper(name = "yearsOfEducation")
    @XmlElement(name = "yearOfEducation")
    private int[]  yearsOfEducation;


    public Man() { }

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
