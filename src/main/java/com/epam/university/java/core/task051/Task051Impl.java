package com.epam.university.java.core.task051;

public class Task051Impl implements Task051 {
    /**
     * <p>
     * Write a method that replaces definite article "the" in the sentence
     * with articles "an" or "a". Remember that if the next word begins
     * with a vowel, use "an". In the case of a consonant, use "a".
     * </p>
     *
     * <p>
     * Example: source collection: the dog and the envelope
     * method return: a dog and an envelope
     * replaceThe("the dog and the envelope") ➞ ""
     * </p>
     *
     * @param source source string to replace
     * @return fixed sentence
     */
    @Override
    public String replace(String source) {
        if (source == null || source.trim().equals("")
                || source.equals("the") || source.contains("THE")) {
            throw new IllegalArgumentException();
        }

        String[] sourceArray = source.split(" ");
        for (int i = 0; i < sourceArray.length; i++) {
            if (sourceArray[i].equals("the")) {
                sourceArray [i] = String.valueOf(sourceArray[i + 1].charAt(0))
                        .matches("[aeyoui]") ? "an" : "a";
            }
        }

        return String.join(" ", sourceArray);
    }
}
