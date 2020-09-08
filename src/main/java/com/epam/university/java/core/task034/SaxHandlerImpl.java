package com.epam.university.java.core.task034;

import org.xml.sax.Attributes;
import java.util.ArrayList;
import java.util.Collection;

public class SaxHandlerImpl extends SaxHandler {
    private Person person;
    private final Collection<PhoneNumber> phoneNumbers = new ArrayList<>();
    private String string;

    public Person getPerson() {
        return person;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (qName.equals("person")) {
            person = new PersonImpl();
            person.setId(Integer.parseInt(attributes.getValue("id")));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        switch (qName) {
            case "first-name":
                person.setFirstName(string);
                break;
            case "last-name":
                person.setLastName(string);
                break;
            case "person-phone":
                PhoneNumber phoneNumber = new PhoneNumberImpl(string);
                phoneNumbers.add(phoneNumber);
                break;
            case "person-phones":
                person.setPhoneNumbers(phoneNumbers);
                break;
            default:
                break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        string = String.valueOf(ch, start, length);
    }
}
