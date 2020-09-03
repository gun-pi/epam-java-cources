package com.epam.university.java.core.task037;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.Callable;

public class Task037Impl implements Task037 {
    /**
     * Implement wall watches using concurrency.
     *
     * @param ticker produces "tick" string
     * @param tacker produces "tack" string
     * @return collection of tick-tack's
     */
    @Override
    public Collection<String> switcher(Callable<String> ticker, Callable<String> tacker) {
        Thread t = Thread.currentThread();
        Collection<String> collection = new ArrayList<>();

        try {
            for (int i = 0; i < 5; i++) {
                collection.add(ticker.call());
                t.sleep(1000);
                collection.add(tacker.call());
                t.sleep(1000);
            }
        } catch (Exception e) {
            System.out.println("Exception was here");
        }
        return collection;
    }
}
