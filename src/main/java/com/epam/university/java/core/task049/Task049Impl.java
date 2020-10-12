package com.epam.university.java.core.task049;

public class Task049Impl implements Task049 {
    /**
     * Return string with longest common sequence.
     *
     * @param first  string
     * @param second string
     * @return string with longest common sequence.
     * @throws IllegalArgumentException if input parameters are not set or not valid
     */
    @Override
    public String getResultList(String first, String second) {
        if (first == null || second == null
                || first.trim().isEmpty() || second.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }
        if (first.equals(second)) {
            return first;
        }

        String result = "";
        for (int i = 0; i < first.length(); i++) {
            for (int j = i + 1; j <= first.length(); j++) {
                String substringOfFirst = first.substring(i,j);
                if (second.contains(substringOfFirst)
                        && substringOfFirst.length() > result.length()) {
                    result = substringOfFirst;
                }
            }
        }

        return result;
    }
}
