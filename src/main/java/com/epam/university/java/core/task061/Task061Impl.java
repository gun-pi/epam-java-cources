package com.epam.university.java.core.task061;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Task061Impl implements Task061 {
    /**
     * Convert roman number to arabic.
     *
     * @param number arabic number
     * @return roman number
     * @throws IllegalArgumentException if number is smaller than 1 or greater than 3999
     */
    @Override
    public String convertToRoman(int number) {
        if ((number <= 0) || (number > 4000)) {
            throw new IllegalArgumentException();
        }

        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

        int i = 0;
        StringBuilder sb = new StringBuilder();

        while ((number > 0) && (i < romanNumerals.size())) {
            RomanNumeral currentSymbol = romanNumerals.get(i);
            if (currentSymbol.getValue() <= number) {
                sb.append(currentSymbol.name());
                number -= currentSymbol.getValue();
            } else {
                i++;
            }
        }

        return sb.toString();
    }

    /**
     * Convert arabic number to roman.
     *
     * @param number roman number
     * @return arabic number
     * @throws IllegalArgumentException if number contains non-valid symbols
     */
    @Override
    public int convertToArabic(String number) {
        if (number == null || !number.matches("[IVXLCMD]+")) {
            throw new IllegalArgumentException();
        }

        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

        int i = 0;
        int result = 0;

        while ((number.length() > 0) && (i < romanNumerals.size())) {
            RomanNumeral symbol = romanNumerals.get(i);
            if (number.startsWith(symbol.name())) {
                result += symbol.getValue();
                number = number.substring(symbol.name().length());
            } else {
                i++;
            }
        }

        return result;
    }
}


