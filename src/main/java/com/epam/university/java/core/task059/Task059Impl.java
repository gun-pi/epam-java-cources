package com.epam.university.java.core.task059;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Task059Impl implements Task059 {
    /**
     * Method for searching a <code>substring</code>.
     *
     * @param path      start <code>path</code> for searching.
     * @param substring searching <code>substring</code>.
     * @return list of paths.
     */
    @Override
    public List<String> find(String path, String substring) {
        if (path == null || substring == null) {
            throw new IllegalArgumentException();
        }

        path = path.substring(1);

        List<String> result = new ArrayList<>();
        List<Path> files = null;
        try {
            files = Files.walk(Paths.get(path))
                    .filter(Files::isRegularFile)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Path each : files) {
            List<String> lines = null;
            try {
                lines = Files.readAllLines(each);
            } catch (IOException e) {
                e.printStackTrace();
            }
            for (String string : lines) {
                if (string.contains(substring)) {
                    result.add(each.toString());
                }
            }
        }

        return result;

    }
}
