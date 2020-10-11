package com.epam.university.java.core.task045;

public class Task045Impl implements Task045 {
    /***
     * <p>
     * Write a method that reverses all the words of input text.
     * All non-letter symbols should stay on the same places.
     * Use Latin alphabet for test only.
     * </p>
     *
     * @param input string to convert
     * @return String - the result of the anagram
     */
    @Override
    public String doAnagram(String input) {
        if (input == null) {
            throw new IllegalArgumentException();
        }
        if (input.equals(" ") || input.equals("")
                || input.length() == 1) {
            return input;
        }

        String[] splittedInput = input.split(" ");
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < splittedInput.length; i++) {
            String symbols = splittedInput[i].replaceAll("[a-zA-Z]"," ");
            StringBuilder symbolsStringBuilder = new StringBuilder(symbols);
            String letters = splittedInput[i].replaceAll("[^a-zA-Z]","");
            StringBuilder lettersStringBuilder = new StringBuilder(letters).reverse();
            for (int j = 0; j < symbols.length(); j++) {
                if (symbols.charAt(j) == ' ') {
                    symbolsStringBuilder.replace(j, j + 1, lettersStringBuilder.substring(0, 1));
                    lettersStringBuilder.deleteCharAt(0);
                }
            }
            output.append(symbolsStringBuilder).append(" ");
        }

        return output.toString().trim();
    }
}
