package com.epam.university.java.core.task035;

import com.epam.university.java.core.task034.Person;
import com.epam.university.java.core.task034.PersonImpl;
import com.epam.university.java.core.task034.PhoneNumber;
import com.epam.university.java.core.task034.PhoneNumberImpl;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.Collection;

public class Task035Impl implements Task035 {
    /**
     * Read json string with Jackson object mapper.
     *
     * @param mapper     mapper instance
     * @param jsonString json string
     * @return parsed data
     */
    @Override
    public Person readWithJackson(ObjectMapper mapper, String jsonString) {
        if (mapper == null || jsonString == null) {
            throw new IllegalArgumentException();
        }

        Person person = null;
        try {
            person = mapper
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                    .readValue(jsonString, PersonImpl.class);

            JsonNode phones = mapper.readTree(jsonString).get("phones");
            Collection<PhoneNumber> phoneNumbers = new ArrayList<>(phones.size());

            for (JsonNode string : phones) {
                phoneNumbers.add(new PhoneNumberImpl(string.textValue()));
            }

            person.setPhoneNumbers(phoneNumbers);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return person;
    }

    /**
     * Read json string with Gson mapper.
     *
     * @param builder    gson builder
     * @param jsonString json string
     * @return parsed data
     */
    @Override
    public Person readWithGson(GsonBuilder builder, String jsonString) {
        if (builder == null || jsonString == null) {
            throw new IllegalArgumentException();
        }

        Gson gson = builder.create();
        Person person = gson.fromJson(jsonString, PersonImpl.class);

        JsonArray jsonArray = new Gson().fromJson(jsonString, JsonObject.class)
                .getAsJsonArray("phones");

        Collection<PhoneNumber> phoneNumbers = new ArrayList<>(jsonArray.size());

        for (JsonElement each : jsonArray) {
            phoneNumbers.add(new PhoneNumberImpl(each.getAsString()));
        }

        person.setPhoneNumbers(phoneNumbers);
        return person;
    }
}
