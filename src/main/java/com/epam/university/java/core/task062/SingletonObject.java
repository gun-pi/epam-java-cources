package com.epam.university.java.core.task062;

import java.io.Serializable;

public class SingletonObject implements Serializable {
    private static final SingletonObject INSTANCE = new SingletonObject();

    private SingletonObject() {
    }

    /**
     * Return or create (if not created yet) a singleton instance.
     *
     * @return singleton instance
     */
    public static SingletonObject getInstance() {
        return INSTANCE;
    }
}
