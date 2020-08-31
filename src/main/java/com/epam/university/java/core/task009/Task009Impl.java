package com.epam.university.java.core.task009;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Task009Impl implements Task009 {
    /**
     * Source file contains words which separated with spaces.
     * <p>
     * You need to output all different words.
     * Same word in upper and lower case should be counted as equal.
     * </p>
     * <p>
     * Tip: you can use Set for it.
     * </p>
     *
     * @param sourceFile source file
     * @return collection of different words
     */
    @Override
    public Collection<String> countWords(File sourceFile) {
        if (sourceFile == null) {
            throw new IllegalArgumentException();
        }
        Set<String> setOfWords = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
        int index = 0;
        try {
            Scanner scanner = new Scanner(sourceFile);
            while (scanner.hasNext()) {
                String str = scanner.next();
                str = str.trim();
                str = str.replaceAll("[^A-Za-z]", "");
                System.out.println(str);
                if (str.equals("")) {
                    continue;
                }
                setOfWords.add(str);
            }

        } catch (Exception e) {
            System.out.println("Do something!");
        }
        return setOfWords;
    }
}
