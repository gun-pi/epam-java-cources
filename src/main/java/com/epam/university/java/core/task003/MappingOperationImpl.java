package com.epam.university.java.core.task003;

public class MappingOperationImpl implements MappingOperation {
    /**
     * Convert source string in accordance with conditions.
     *
     * @param source source string
     * @return converted string
     * @throws IllegalArgumentException if source string not provided
     */
    @Override
    public String map(String source) {
        StringBuilder sourceStrBdr = new StringBuilder(source);
        sourceStrBdr.reverse();
        return sourceStrBdr.toString();
    }
}
