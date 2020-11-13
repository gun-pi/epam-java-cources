package com.epam.university.java.core.task060;

public class CacheFactoryImpl implements CacheFactory {
    /**
     * Create new cache instance.
     *
     * @param size of new cache
     * @return new cache instance
     */
    @Override
    public Cache newInstance(int size) {
        return new CacheImpl(size);
    }
}
