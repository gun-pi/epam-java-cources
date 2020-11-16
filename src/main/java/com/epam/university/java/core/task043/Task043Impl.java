package com.epam.university.java.core.task043;

public class Task043Impl implements Task043 {
    public static char[] englishAlphabet = { 'A', 'B', 'C', 'D', 'E', 'F', 'G',
            'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U',
            'V', 'W', 'X', 'Y', 'Z', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            '0', ',', '.', '?', ' ' };

    public static String[] morseAlphabet = { ".-", "-...", "-.-.", "-..", ".", "..-.",
            "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-",
            ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--..", ".----", "..---",
            "...--", "....-", ".....", "-....", "--...", "---..", "----.",
            "-----", "--..--", ".-.-.-", "..--..", "  " };

    /**
     * Encode source string with Morse Code.
     *
     * @param sourceString source string
     * @return coded string
     */
    @Override
    public String encode(String sourceString) {
        if (sourceString == null) {
            throw new IllegalArgumentException();
        }

        StringBuilder result = new StringBuilder();
        char[] characters = sourceString.toCharArray();

        for (char character : characters) {
            for (int j = 0; j < englishAlphabet.length; j++) {
                if (englishAlphabet[j] == character) {
                    if (englishAlphabet[j] == ' ') {
                        result.append(morseAlphabet[j]);
                    } else {
                        result.append(morseAlphabet[j]).append(" ");
                    }
                }
            }
        }

        return result.toString().trim();
    }

    /**
     * Decode source string with Morse Code.
     *
     * @param sourceString source string
     * @return decoded string
     */
    @Override
    public String decode(String sourceString) {
        if (sourceString == null) {
            throw new IllegalArgumentException();
        }

        StringBuilder result = new StringBuilder();
        String[] wordsFromSourceString = sourceString.split("   ");

        for (String s : wordsFromSourceString) {
            String[] charactersFromSourceString = s.split(" ");
            for (String value : charactersFromSourceString) {
                for (int j = 0; j < morseAlphabet.length; j++) {
                    if (morseAlphabet[j].equals(value)) {
                        result.append(englishAlphabet[j]);
                    }
                }
            }
            result.append(" ");
        }

        return result.toString().trim();
    }
}
