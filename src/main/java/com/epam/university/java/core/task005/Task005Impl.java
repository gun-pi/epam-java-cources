package com.epam.university.java.core.task005;

public class Task005Impl implements Task005 {
    /**
     * Find two numbers, quotient of which will be closest to PI number. Ex. if digit is 1,
     * holder values should be between 1 and 9, if digits is equals to 2, values should
     * be between 10 and 99 and so on.
     * <p>
     * Tip: Math.abs((a / b) - Math.PI) -> min
     * </p>
     *
     * @param digits amount of digits in numbers.
     * @return holder which contains both numbers
     * @throws IllegalArgumentException if digits less or equals to the zero, or more than ten
     */

    @Override
    public PiHolder findPi(int digits) {
        if (digits <= 0 || digits > 10) {
            throw new IllegalArgumentException();
        }
        PiHolderImpl holder = new PiHolderImpl();

        int min = Integer.parseInt("1" + "0".repeat(digits - 1));
        int max = Integer.parseInt("9".repeat(digits));


        double minQuotientDiff = max;
        int resA = 0;
        int resB = 0;

        for (int a = min; a <= max; a++) {
            for (int b = min; b <= max / 2; b++) {
                double quotient = (double)a / (double)b;
                if (Math.abs((quotient) - Math.PI) < minQuotientDiff) {
                    minQuotientDiff = Math.abs((quotient) - Math.PI);
                    resA = a;
                    resB = b;
                }
            }
        }

        holder.setFirst(resA);
        holder.setSecond(resB);
        return holder;
    }
}
