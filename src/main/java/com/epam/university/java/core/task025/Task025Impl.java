package com.epam.university.java.core.task025;

public class Task025Impl implements Task025 {
    /**
     * Martian exploration ship has been broken and sent several SOS messages back to Earth.
     * Some letters of the SOS message are altered by cosmic radiation during transmission.
     * Given the signal received by Earth as a string, determine how many letters of SOS
     * has been changed by radiation.
     *
     * <p>
     * Example: source SOSOASOB, result is 2
     * </p>
     *
     * @param sourceMessage received message
     * @return amount of altered letters
     */
    @Override
    public int getAmountOfAlteredLetters(String sourceMessage) {
        if (sourceMessage == null) {
            throw new IllegalArgumentException();
        }
        if (sourceMessage.equals("")) {
            return 0;
        }

        String sos = "SOS";
        int k = 0;
        int counter = 0;

        for (int i = 0; i < sourceMessage.length(); i++) {
            k = k == 3 ? 0 : k;
            if (!String.format("%c", sourceMessage.charAt(i)).equals(
                    String.format("%c", sos.charAt(k)))) {
                counter++;
            }
            k++;
        }

        return counter;
    }
}
