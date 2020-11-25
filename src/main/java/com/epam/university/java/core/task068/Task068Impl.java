package com.epam.university.java.core.task068;

public class Task068Impl implements Task068 {
    /**
     * In the Task you have to determine the winner
     * after the third move of Noughts in the Tic-tac-toe game.
     * As input data you have the field represented as a three String.
     * Each String is representing the line (from the top to the bottom order).
     * O - is the Nought.
     * X - is the Cross.
     * # - is the empty cell.
     *
     * @param firstLine  is the top line of the field.
     * @param secondLine is the middle line of the field.
     * @param thirdLine  is the bottom line of the field.
     * @return String "Crosses win" or "Noughts win" or "Draw" depending on the result of the game
     */
    @Override
    public String determineTheResultOfGame(String firstLine, String secondLine, String thirdLine) {
        String allLines = firstLine + secondLine + thirdLine;

        if (firstLine == null || secondLine == null || thirdLine == null) {
            throw new IllegalArgumentException();
        }
        if (firstLine.matches("[^XO#]+") || secondLine.matches("[^XO#]+")
                || thirdLine.matches("[^XO#]+")) {
            throw new IllegalArgumentException();
        }
        if (countChars(allLines, 'X') != countChars(allLines, 'O')) {
            throw new IllegalArgumentException();
        }

        if (countChars(allLines, 'X') == countChars(allLines, 'O')) {
            if (
                    isWinningLine(String.format("%c%c%c",
                            allLines.charAt(0),
                            allLines.charAt(3),
                            allLines.charAt(6)), 'X')
            ) {
                return "Crosses win";
            }

            if (
                    isWinningLine(String.format("%c%c%c",
                            allLines.charAt(2),
                            allLines.charAt(5),
                            allLines.charAt(8)), 'O')
            ) {
                return "Noughts win";
            }
        }
        return "Draw";
    }

    private int countChars(String string, char c) {
        int counter = 0;
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == c) {
                counter++;
            }
        }
        return counter;
    }

    private boolean isWinningLine(String string, char c) {
        return countChars(string, c) == 2
                && countChars(string, '#') == 1;
    }
}
