package com.epam.university.java.core.task062;

import java.io.Serializable;

public class SingletonObjectImpl implements Serializable, SingletonObject {
    private static SingletonObjectImpl instance = new SingletonObjectImpl();

    private SingletonObjectImpl() {
    }

    /**
     * Return or create (if not created yet) a singleton instance.
     *
     * @return singleton instance
     */
    public static SingletonObjectImpl getInstance() {
        return instance;
    }
}
