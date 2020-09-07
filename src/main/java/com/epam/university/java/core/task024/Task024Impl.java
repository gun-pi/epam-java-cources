package com.epam.university.java.core.task024;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class Task024Impl implements Task024 {
    /**
     * Given a string with camel case word, you should separate this string
     * into collection of words.
     *
     * <p>
     * Example: source string is saveChangesInTheEditor, result is
     * [save, changes, in, the, editor]
     * </p>
     *
     * @param source source string
     * @return collection of words
     */
    @Override
    public Collection<String> getWordsCount(String source) {
        if (source == null) {
            throw new IllegalArgumentException();
        }

        if (source.equals("")) {
            return Collections.emptyList();
        }
        LinkedList<String> list = splitCamelCaseString(source);

        for (int i = 0; i < list.size(); i++) {
            String each = list.remove(i).toLowerCase();
            list.add(i, each);
        }

        return list;
    }

    /**
     * Split a given string.
     */
    public static LinkedList<String> splitCamelCaseString(String s) {
        if (s == null) {
            throw new IllegalArgumentException();
        }

        LinkedList<String> result = new LinkedList<String>();
        for (String w : s.split(
                "(?<!(^|[A-ZА-ЯÜẞÖÄ]))(?=[A-ZА-ЯÜẞÖÄ])|(?<!^)(?=[A-ZА-ЯÜẞÖÄ][a-zа-яäöü])")) {
            result.add(w);
        }
        return result;
    }
}
