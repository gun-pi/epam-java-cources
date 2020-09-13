package com.epam.university.java.core.task026;

public class Task026Impl implements Task026 {
    /**
     * Encrypt source string with Caesar Cipher shifting by <code>shift</code> value.
     *
     * @param sourceString source string
     * @param shift        shift value
     * @return encrypted string
     */
    public static final String lowerCaseAlphabet = "abcdefghijklmnopqrstuvwxyz";
    public static final String upperCaseAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";


    @Override
    public String encrypt(String sourceString, int shift) {
        if (sourceString == null) {
            throw new IllegalArgumentException();
        }

        String[] lowerCaseAlphabetArray = lowerCaseAlphabet.split("");
        String[] upperCaseAlphabetArray = upperCaseAlphabet.split("");
        String[] sourceStringArray = sourceString.split("");

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < sourceStringArray.length; i++) {
            if (sourceStringArray[i].matches("[)'\\s!?.,-]")) {
                result.append(sourceStringArray[i]);
                continue;
            }
            for (int j = 0; j < lowerCaseAlphabetArray.length; j++) {
                if (sourceStringArray[i].equals(lowerCaseAlphabetArray[j])) {
                    int index = j + shift;
                    while (index > 25) {
                        index -= 26;
                    }
                    result.append(lowerCaseAlphabetArray[index]);
                    break;
                } else if (sourceStringArray[i].equals(upperCaseAlphabetArray[j])) {
                    int index = j + shift;
                    while (index > 25) {
                        index -= 26;
                    }
                    result.append(upperCaseAlphabetArray[index]);
                    break;
                }
            }
        }

        return result.toString();
    }

    /**
     * Decrypt protected string by unshifting it by <code>shift</code> value.
     *
     * @param encryptedString encrypted string
     * @param shift           shift value
     * @return decrypted string
     */
    @Override
    public String decrypt(String encryptedString, int shift) {
        if (encryptedString == null) {
            throw new IllegalArgumentException();
        }

        String[] lowerCaseAlphabet = Task026Impl.lowerCaseAlphabet.split("");
        String[] upperCaseAlphabet = Task026Impl.upperCaseAlphabet.split("");
        String[] ourStringArray = encryptedString.split("");

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < ourStringArray.length; i++) {
            if (ourStringArray[i].matches("[)'\\s!?.,-]")) {
                result.append(ourStringArray[i]);
                continue;
            }
            for (int j = 0; j < lowerCaseAlphabet.length; j++) {
                if (ourStringArray[i].equals(lowerCaseAlphabet[j])) {
                    int index = j - shift;
                    while (index < 0) {
                        index += 26;
                    }
                    result.append(lowerCaseAlphabet[index]);
                    break;
                } else if (ourStringArray[i].equals(upperCaseAlphabet[j])) {
                    int index = j - shift;
                    while (index < 0) {
                        index += 26;
                    }
                    result.append(upperCaseAlphabet[index]);
                    break;
                }
            }
        }

        return result.toString();
    }
}
