package ru.job4j.serialization.json;

import ru.job4j.serialization.java.Contact;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

public class MainXml {
    public static void main(String[] args) throws JAXBException, IOException {
        final Man man = new Man(true, "Max",
                new Contact(555, "777-77-77"),
                new int[] {2004, 2009, 2013});

        String xmlMan =
                "<?xml version=\"1.1\" encoding=\"UTF-8\" ?>\n"
                        + "<man sex=\"true\" name=\"Max\">\n"
                        + "    <contact phone=\"777-77-77\"/>\n"
                        + "    <yearsOfEducation>\n"
                        + "        <yearOfEducation>2004</yearOfEducation>\n"
                        + "        <yearOfEducation>2009</yearOfEducation>\n"
                        + "        <yearOfEducation>2013</yearOfEducation>\n"
                        + "    </yearsOfEducation>\n"
                        + "</person>";
        System.out.println(xmlMan);

        JAXBContext context = JAXBContext.newInstance(Man.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String manJAXB;
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(man, writer);
            manJAXB = writer.getBuffer().toString();
            System.out.println(System.lineSeparator());
            System.out.println(manJAXB);
        }
        Unmarshaller unmanshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(manJAXB)) {
            Man rsl = (Man) unmanshaller.unmarshal(reader);
            System.out.println(rsl);
        }


    }
}
