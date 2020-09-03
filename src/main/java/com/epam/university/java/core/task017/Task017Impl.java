package com.epam.university.java.core.task017;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class Task017Impl implements Task017 {
    /**
     * Output objects as formatted string.
     *
     * @param args objects to output
     * @return formatted string
     */
    @Override
    public String formatString(Object... args) {
        if (args == null || args.length == 0) {
            throw new IllegalArgumentException();
        }

        for (Object each : args) {
            if (each == null) {
                throw new IllegalArgumentException();
            }
        }

        return String.format("You know %s, %s!", args[0], args[1]);
    }

    /**
     * Output objects as formatted string.
     *
     * @param args objects to output
     * @return formatted string
     */
    @Override
    public String formatNumbers(Object... args) {
        if (args == null || args.length == 0) {
            throw new IllegalArgumentException();
        }

        for (Object each : args) {
            if (each == null) {
                throw new IllegalArgumentException();
            }
        }

        String bin = Double.toHexString(Double.parseDouble(args[0].toString()));
        //bin =  String.format("%016d", Double.parseDouble(bin));
        return String.format("%.1f, %.2f, %+.2f, %s", args[0], args[0], args[0], bin);
    }

    /**
     * Output objects as formatted string.
     *
     * @param args objects to output
     * @return formatted string
     */
    @Override
    public String formatDates(Object... args) {
        if (args == null || args.length == 0) {
            throw new IllegalArgumentException();
        }

        for (Object each : args) {
            if (each == null) {
                throw new IllegalArgumentException();
            }
        }

        final Date targetDate = new Date();
        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.dd.MM");
        System.out.println(dateFormat.format(targetDate));
        return dateFormat.format(targetDate);
    }
}
