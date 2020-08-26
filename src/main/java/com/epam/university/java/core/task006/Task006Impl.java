package com.epam.university.java.core.task006;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.lang.Number;

public class Task006Impl implements Task006 {
    /**
     * Calculate resistance by collection of measurements using Least Square method.
     *
     * @param measurements collection of measurements
     * @return value of resistance
     * @throws IllegalArgumentException if measurements not provided
     */

    @Override
    public double resistance(Collection<Measurement> measurements) {
        if (measurements == null) {
            throw new IllegalArgumentException();
        }
        if (measurements.isEmpty()) {
            return 0;
        }

        double mx = 0;
        double my = 0;
        double mxx = 0;
        double mxy = 0;
        for (Measurement eachElementFromMeasurements : measurements) {
            mx += eachElementFromMeasurements.getAmperage();
            my += eachElementFromMeasurements.getVoltage();
            mxx += eachElementFromMeasurements.getAmperage()
                    * eachElementFromMeasurements.getAmperage();
            mxy += eachElementFromMeasurements.getAmperage()
                    * eachElementFromMeasurements.getVoltage();
        }
        mx /= measurements.size();
        my /= measurements.size();
        mxx /= measurements.size();
        mxy /= measurements.size();

        double resistance = (mxy - my * mx) / (mxx - mx * mx);

        if (resistance != resistance) {
            return 0;
        } else {
            return roundAvoid(resistance, 3);
        }
    }

    public static double roundAvoid(double value, int places) {
        double scale = Math.pow(10, places);
        return Math.round(value * scale) / scale;
    }
}
