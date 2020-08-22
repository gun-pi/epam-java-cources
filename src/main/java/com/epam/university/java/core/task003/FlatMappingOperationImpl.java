package com.epam.university.java.core.task003;

import java.util.Arrays;
import java.util.Collections;

public class FlatMappingOperationImpl implements FlatMappingOperation {
    /**

     * Convert source string to array of result strings.
     *
     * @param source source string
     * @return array of converted strings or empty array if source not provided
     */
    @Override
    public String[] flatMap(String source) {
        String[] arrayFromSource = source.split(",");
        for (int i = 0; i < arrayFromSource.length; i++) {
            arrayFromSource[i] = arrayFromSource[i].trim();
        }

        Arrays.sort(arrayFromSource, Collections.reverseOrder());

        return arrayFromSource;
    }
}
