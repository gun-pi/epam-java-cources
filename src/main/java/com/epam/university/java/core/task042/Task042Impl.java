package com.epam.university.java.core.task042;

import java.time.LocalTime;
import java.util.List;

public class Task042Impl implements Task042 {
    /**
     * <p>
     * Alexander is a businessman. He wants to implement an automatic system informing
     * that he's currently unavailable and the time when he's going to get back to the
     * office. Alexander works from 09:00 to 18:00.
     * </p>
     * <p>
     * There are three implementations of BookingResponse interface that describes
     * Alexander's availability.
     * </p>
     * <p>
     * Given a list with a schedule for a given day. List's elements consist start and
     * finish time of a given appointment in "hh:mm-hh:mm" 24-h format. And given a current
     * time in "hh:mm" 24-h format. If no appointments are scheduled for current time,
     * the function should return AvailableResponse instance. If Alexander is in the middle
     * of an appointment at current time, the function should return TimeProposalResponse
     * instance with the time he's going to be available. If Alexandr has no free time today,
     * the function should return BusyResponse instance.
     * </p>
     * <p>
     * Example: schedule ["09:30-10:15", "12:20-15:50"], currentTime "11:00",
     * result is AvailableResponse instance
     * Example: schedule ["09:30-10:15", "12:20-15:50"], currentTime "10:00",
     * result is TimeProposalResponse instance with proposed time "10:15"
     * Example: schedule ["09:30-10:15", "12:20-19:00"], currentTime "13:00",
     * result is BusyResponse instance
     * </p>
     *
     * @param schedule    list with a schedule for a given day
     * @param currentTime current time
     * @return availability
     */
    @Override
    public BookingResponse checkAvailability(List<String> schedule, String currentTime) {
        if (schedule == null || currentTime == null) {
            throw new IllegalArgumentException();
        }

        final LocalTime morningTime = LocalTime.parse("09:00");
        final LocalTime eveningTime = LocalTime.parse("18:00");

        LocalTime currentLocalTime = LocalTime.parse(currentTime.substring(0,5));

        LocalTime temp = currentLocalTime.compareTo(LocalTime.parse("09:00")) >= 0
                ? currentLocalTime : morningTime;

        if (schedule.size() == 0) {
            if (temp.compareTo(eveningTime) < 0 && temp.compareTo(currentLocalTime) == 0) {
                return new AvailableResponse();
            } else if (temp.compareTo(eveningTime) < 0) {
                TimeProposalResponse tempClass = new TimeProposalResponse();
                tempClass.setProposedTime(temp.toString());
                return tempClass;
            } else {
                return new BusyResponse();
            }
        }

        for (String each :schedule) {
            LocalTime start = LocalTime.parse(each.substring(0,5));
            LocalTime end = LocalTime.parse(each.substring(6,11));

            if (temp.compareTo(end) < 0 && temp.compareTo(start) >= 0) {
                temp = end;
            }
        }

        if (temp.compareTo(currentLocalTime) == 0 && temp.compareTo(eveningTime) < 0) {
            return new AvailableResponse();
        } else if (temp.compareTo(eveningTime) < 0) {
            TimeProposalResponse tempClass = new TimeProposalResponse();
            tempClass.setProposedTime(temp.toString());
            return tempClass;
        } else {
            return new BusyResponse();
        }
    }
}
