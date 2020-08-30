package com.epam.university.java.core.task023;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task023Impl implements Task023 {
    /**
     * Given string that presents cell phone number. You should use regular expression
     * to extract operator code.
     *
     * <p>
     * Example: given phone number +7(912)345-67-89, operator code is 912
     * </p>
     *
     * @param phoneString cell phone number string
     * @return operator code string
     */
    @Override
    public String extract(String phoneString) {
        Pattern p = Pattern.compile(
                "(\\+7|8)\\s?[\\(-]?(\\d{3})[\\)-]?\\s?\\d{3}-?\\s?\\d{2}-?\\s?\\d{2}");
        Matcher m = p.matcher(phoneString);
        try {
            m.find();
            return m.group(2);
        } catch (IllegalStateException e) {
            throw new IllegalArgumentException();
        }
    }
}
