package com.epam.university.java.core.task063;

public class Task063Impl implements Task063 {
    /**
     * Calculate digital root of given number.
     *
     * @param number given number for calculation
     * @return digital root
     * @throws IllegalArgumentException if number not provided
     */
    @Override
    public Integer calcDigitalRoot(Integer number) {
        if (number == null) {
            throw new IllegalArgumentException();
        }

        while (true) {
            if (String.valueOf(number).length() == 1) {
                break;
            }
            int result = 0;
            for (String digit : String.valueOf(number).split("")) {
                result += Integer.parseInt(digit);
            }
            number = result;
        }

        return number;
    }
}
