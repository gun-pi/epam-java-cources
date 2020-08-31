package com.epam.university.java.core.task010;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Task010Impl implements Task010 {
    /**
     * Given a textual file, you should count frequency of words in this file.
     *
     * @param source source file
     * @return map word to frequency of it
     */
    @Override
    public Map<String, Integer> countWordNumbers(File source) {
        if (source == null) {
            throw new IllegalArgumentException();
        }
        Map<String, Integer> mapOfWords = new HashMap<>();
        try {
            Scanner scanner = new Scanner(source);
            while (scanner.hasNext()) {
                String str = scanner.next();
                str = str.trim();
                str = str.toLowerCase();
                str = str.replaceAll("[^A-Za-z]", "");

                Integer count = mapOfWords.get(str);
                if (count == null) {
                    count = 0;
                }
                mapOfWords.put(str, ++count);
            }

        } catch (Exception e) {
            System.out.println("Do something!");
        }
        return mapOfWords;
    }
}
