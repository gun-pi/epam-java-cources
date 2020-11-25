package com.epam.university.java.core.task071;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Task071Impl implements Task071 {
    /**
     * Nice to meet you at Task071.
     * In this task you play a role of a very nice person.
     * Whose friend is extremely tired of routine job.
     * You are a very nice programmer and a really helpful friend.
     * That's why you decided to make a small present for your friend.
     * So now your problem to solve is how to implement a program
     * that will find periods between two dates in time units a user needs.
     * Format of date is: YYYY-mm-dd
     * Tip: bank system is not the easiest thing to understand
     * so your friend gave some templates of dates and queries.
     * (Look right in the test examples)
     *
     * @param typeOfPeriod a string that says which
     *                     period of time a user needs
     *                     (e.g. "WEEK", "MONTH")
     * @param dates        two dates between which
     *                     you need to find period
     * @return a list of date introducing period type
     */
    @Override
    public List<String> datesBetween(String typeOfPeriod, String dates) {
        if (typeOfPeriod == null || dates == null) {
            throw new IllegalArgumentException();
        }

        LocalDate from = LocalDate.parse(dates.substring(0, 10));
        LocalDate to = LocalDate.parse(dates.substring(11));
        switch (typeOfPeriod) {
            case "WEEK":
                return periodBetweenWeeks(from, to);
            case "MONTH":
                return periodBetweenMonths(from, to);
            case "QUARTER":
                return periodBetweenQuarters(from, to);
            case "YEAR":
                return periodBetweenYears(from, to);
            default:
                return null;
        }
    }

    /**
     * Method that finds dates between in quarters.
     *
     * @param from starting date
     * @param to   the last date of a period
     * @return a list of periods in quarters
     */
    @Override
    public List<String> periodBetweenQuarters(LocalDate from, LocalDate to) {
        int currentYear = from.getYear();
        List<String> result = new ArrayList<>();
        LocalDate currentDate = LocalDate.of(
                from.getYear(),
                from.getMonth(),
                from.getDayOfMonth() + 1
        );
        LocalDate bound = LocalDate.of(
                from.getYear(),
                from.getMonth(),
                from.getDayOfMonth()
        );

        while (true) {
            if (currentDate.isEqual(LocalDate.of(currentYear, 10, 1))) {
                result.add(bound.toString() + " " + currentDate.minusDays(1).toString());
                bound = LocalDate.of(
                        currentDate.getYear(),
                        currentDate.getMonth(),
                        currentDate.getDayOfMonth()
                );
            }
            if (currentDate.isEqual(LocalDate.of(currentYear + 1, 10, 1))) {
                result.add(bound.toString() + " " + currentDate.minusDays(1).toString());
                bound = LocalDate.of(
                        currentDate.getYear(),
                        currentDate.getMonth(),
                        currentDate.getDayOfMonth()
                );
            }
            if (currentDate.isEqual(LocalDate.of(currentYear + 1, 1, 1))) {
                result.add(bound.toString() + " " + currentDate.minusDays(1).toString());
                bound = LocalDate.of(
                        currentDate.getYear(),
                        currentDate.getMonth(),
                        currentDate.getDayOfMonth()
                );
            }
            if (currentDate.isEqual(LocalDate.of(currentYear + 1, 4, 1))) {
                result.add(bound.toString() + " " + currentDate.minusDays(1).toString());
                bound = LocalDate.of(
                        currentDate.getYear(),
                        currentDate.getMonth(),
                        currentDate.getDayOfMonth()
                );
            }
            if (currentDate.isEqual(LocalDate.of(currentYear + 1, 7, 1))) {
                result.add(bound.toString() + " " + currentDate.minusDays(1).toString());
                bound = LocalDate.of(
                        currentDate.getYear(),
                        currentDate.getMonth(),
                        currentDate.getDayOfMonth()
                );
            }
            if (currentDate.isEqual(to)) {
                result.add(bound.toString() + " " + currentDate.toString());
                break;
            }
            currentDate = currentDate.plusDays(1);
        }

        return result;
    }

    /**
     * Method that finds dates between in months.
     *
     * @param from starting date
     * @param to   the last date of a period
     * @return a list of periods in months
     */
    @Override
    public List<String> periodBetweenMonths(LocalDate from, LocalDate to) {
        List<String> result = new ArrayList<>();
        LocalDate currentDate = LocalDate.of(
                from.getYear(),
                from.getMonth(),
                from.getDayOfMonth() + 1
        );
        LocalDate bound = LocalDate.of(
                from.getYear(),
                from.getMonth(),
                from.getDayOfMonth()
        );

        while (true) {
            if (currentDate.getDayOfMonth() == 1) {
                result.add(bound.toString() + " " + currentDate.toString());
                bound = LocalDate.of(
                        currentDate.getYear(),
                        currentDate.getMonth(),
                        1
                );
            }
            if (currentDate.isEqual(to)) {
                result.add(bound.toString() + " " + currentDate.toString());
                break;
            }
            currentDate = currentDate.plusDays(1);
        }

        return result;
    }

    /**
     * Method that finds dates between in years.
     *
     * @param from starting date
     * @param to   the last date of a period
     * @return a list of periods in years
     */
    @Override
    public List<String> periodBetweenYears(LocalDate from, LocalDate to) {
        int currentYear = from.getYear();
        List<String> result = new ArrayList<>();
        LocalDate currentDate = LocalDate.of(
                from.getYear(),
                from.getMonth(),
                from.getDayOfMonth() + 1
        );
        LocalDate bound = LocalDate.of(
                from.getYear(),
                from.getMonth(),
                from.getDayOfMonth()
        );

        while (true) {
            if (currentDate.isEqual(LocalDate.of(currentYear, 12, 31))) {
                result.add(bound.toString() + " " + currentDate.toString());
                currentYear++;
                bound = LocalDate.of(currentYear, 1, 1).minusDays(1);
            }
            if (currentDate.isEqual(to)) {
                result.add(bound.toString() + " " + currentDate.toString());
                break;
            }
            currentDate = currentDate.plusDays(1);
        }

        return result;
    }

    /**
     * Method that finds dates between in weeks.
     *
     * @param from starting date
     * @param to   the last date of a period
     * @return a list of periods in weeks
     */
    @Override
    public List<String> periodBetweenWeeks(LocalDate from, LocalDate to) {
        List<String> result = new ArrayList<>();
        LocalDate currentDate = LocalDate.of(
                from.getYear(),
                from.getMonth(),
                from.getDayOfMonth() + 1
        );
        LocalDate bound = LocalDate.of(from.getYear(), from.getMonth(), from.getDayOfMonth());

        while (true) {
            if (currentDate.getDayOfWeek().equals(DayOfWeek.MONDAY)) {
                result.add(bound.toString() + " " + currentDate.minusDays(1).toString());
                bound = LocalDate.of(
                        currentDate.getYear(),
                        currentDate.getMonth(),
                        currentDate.getDayOfMonth()
                );
            }

            if (currentDate.isEqual(to)) {
                result.add(bound.toString() + " " + currentDate.toString());
                break;
            }
            currentDate = currentDate.plusDays(1);
        }

        return result;
    }
}
