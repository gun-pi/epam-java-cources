package com.epam.university.java.core.task055;

public class Task055Impl implements Task055 {
    /**
     * Create Xml processor for file with specified path.
     *
     * @param path file to read.
     * @return ProcessingContext which will be perform processing to the file.
     */
    @Override
    public ProcessingContext createContext(String path) {
        if (path == null || path.isEmpty()) {
            throw new IllegalArgumentException();
        }

        return new ProcessingContextImpl(path);
    }
}
