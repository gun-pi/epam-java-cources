package com.epam.university.java.core.task030;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;

public class Task030Impl implements  Task030 {
    /**
     * Get amount of days between two dates.
     *
     * @param dateStart first date
     * @param dateEnd   second date
     * @return amount of days
     */
    @Override
    public int daysBetweenDates(LocalDate dateStart, LocalDate dateEnd) {
        if (dateStart == null || dateEnd == null) {
            throw  new IllegalArgumentException();
        }
        return Period.between(dateStart, dateEnd).getDays();
    }

    /**
     * Add designated amount of days to <code>dateStart</code>.
     *
     * @param dateStart date to adjust
     * @param daysToAdd amount of days to add
     * @return adjusted date
     */
    @Override
    public LocalDate adjustDays(LocalDate dateStart, int daysToAdd) {
        if (dateStart == null) {
            throw new IllegalArgumentException();
        }
        return dateStart.plusDays(daysToAdd);
    }

    /**
     * Get amount of seconds between two instants.
     *
     * @param instantStart first instant
     * @param instantEnd   second instant
     * @return amount of seconds
     */
    @Override
    public long distanceBetween(Instant instantStart, Instant instantEnd) {
        if (instantStart == null || instantEnd == null) {
            throw new IllegalArgumentException();
        }
        return Duration.between(instantStart, instantEnd).toSeconds();
    }

    /**
     * Get day of week of the given date.
     *
     * @param localDate date to check
     * @return day of week
     */
    @Override
    public DayOfWeek getDayOfWeek(LocalDate localDate) {
        if (localDate == null) {
            throw new IllegalArgumentException();
        }
        return localDate.getDayOfWeek();
    }

    /**
     * Calculate date of the nearest weekend start.
     *
     * @param localDate date to start
     * @return weekend start date
     */
    @Override
    public LocalDate getNextWeekend(LocalDate localDate) {
        if (localDate == null) {
            throw new IllegalArgumentException();
        }
        return localDate.with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
    }

    /**
     * Get local time from given string.
     *
     * @param timeString string with time
     * @return local time
     */
    @Override
    public LocalTime getLocalTime(String timeString) {
        if (timeString == null || timeString.equals("")) {
            throw new IllegalArgumentException();
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mma");
        return LocalTime.parse(timeString, formatter);
    }
}
