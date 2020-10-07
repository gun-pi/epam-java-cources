package com.epam.university.java.core.task040;

public class Task040Impl implements Task040 {
    /**
     * Count the score of Ten-Pin Bowling game.
     * Tip: read the rules of game
     *
     * @param str representing a player's ten frames. Frames separated by " ".
     *            Each frame is two characters (shots) - digits or 'X' or '/'.
     *            'X' - means "strike", '/' - means spare.
     * @return total score of player.
     */
    @Override
    public int countScore(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException();
        }

        String[] frames = str.split(" ");

        int result = 0;

        for (int i = 0; i < frames.length; i++) {
            if (i == frames.length - 1 && frames[i].length() == 3) {
                result += 10;
                if (frames[i].charAt(1) == 'X') {
                    result += 10;
                } else {
                    result += Character.getNumericValue(frames[i].charAt(1));
                }
                if (frames[i].charAt(2) == 'X') {
                    result += 10;
                } else {
                    result += Character.getNumericValue(frames[i].charAt(1));
                }

                return result;
            }
            result += getScoreForStage(frames, i);
        }

        return result;
    }

    /**
     * Get score for a stage.
     *
     * @param frames frames
     * @param i index
     * @return result
     */
    public int getScoreForStage(String[] frames, int i) {
        int result = 0;
        if (frames[i].charAt(0) == 'X') {
            result += 10;
            if ((i + 1) != frames.length && frames[i + 1].charAt(0) == 'X') {
                result += 10;
                if (frames[i + 1].length() == 3 && frames[i + 1].charAt(1) == 'X') {
                    result += 10;
                }
                if ((i + 2) != frames.length && frames[i + 2].charAt(0) == 'X') {
                    result += 10;
                }
            } else {
                if (frames[i + 1].charAt(1) == '/') {
                    result += 10;
                } else {
                    result += Character.getNumericValue(frames[i + 1].charAt(0))
                            + Character.getNumericValue(frames[i + 1].charAt(1));
                }
            }
        } else if ((i + 1) != frames.length && frames[i].charAt(1) == '/') {
            result += 10;
            if (frames[i + 1].charAt(0) == 'X') {
                result += 10;
            } else {
                result += Character.getNumericValue(frames[i + 1].charAt(0));
            }

        } else {
            result += Character.getNumericValue(frames[i].charAt(0))
                    + Character.getNumericValue(frames[i].charAt(1));
        }

        return result;
    }
}
