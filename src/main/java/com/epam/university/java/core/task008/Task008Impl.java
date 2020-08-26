package com.epam.university.java.core.task008;

public class Task008Impl implements Task008 {
    /**
     * Given a string with mathematical expression like "(1 + 2) * {[-3] - 4}". You need to check,
     * are all braces correct. Ex:
     * <p>
     * "(1 + 2) * {[-3] - 4}" - correct
     * "(1 + [2) + 3 - 4]" - incorrect
     * In expression can be used the following kinds of braces: {}, (), [].
     * Tip: it's better to implement stack structure.
     * Tip: You also can use Stack class but it's not recommended.
     * </p>
     *
     * @param sourceString string to check
     * @return is braces valid or source string is empty
     */
    @Override
    public boolean isValid(String sourceString) {
        if (sourceString == null) {
            throw new IllegalArgumentException();
        }

        int counter1 = 0; //for ( brace
        int counter2 = 0; //for { brace
        int counter3 = 0; //for [ brace
        String operations = "";
        int positionInOperations = -1;

        for (int i = 0; i < sourceString.length(); i++) {
            String character = String.format("%c", sourceString.charAt(i));
            switch (character) {
                case "(":
                    counter1++;
                    operations += "(";
                    positionInOperations++;
                    break;
                case "{":
                    counter2++;
                    operations +=  "{";
                    positionInOperations++;
                    break;
                case "[":
                    counter3++;
                    operations += "[";
                    positionInOperations++;
                    break;
                case ")":
                    counter1--;
                    if (positionInOperations < 0) {
                        return false;
                    }
                    if (!String.format("%c", operations.charAt(positionInOperations)).equals("(")) {
                        return false;
                    }
                    operations = operations.substring(0, operations.length() - 1);
                    positionInOperations--;
                    break;
                case "}":
                    counter2--;
                    if (positionInOperations < 0) {
                        return false;
                    }
                    if (!String.format("%c", operations.charAt(positionInOperations)).equals("{")) {
                        return false;
                    }
                    operations = operations.substring(0, operations.length() - 1);
                    positionInOperations--;
                    break;
                case "]":
                    counter3--;
                    if (positionInOperations < 0) {
                        return false;
                    }
                    if (!String.format("%c", operations.charAt(positionInOperations)).equals("[")) {
                        return false;
                    }
                    operations = operations.substring(0, operations.length() - 1);
                    positionInOperations--;
                    break;
                default: // do nothing
            }
        }

        return counter1 == 0 && counter2 == 0 && counter3 == 0;
    }
}
