package com.epam.university.java.core.task060;

public class Task060Impl implements Task060 {
    /**
     * Create new cache instance.
     *
     * @param size of cache.
     * @return new cache instance.
     */
    @Override
    public Cache createCache(int size) {
        return new CacheFactoryImpl().newInstance(size);
    }

    /**
     * Sets key to value.
     *
     * @param cache is used.
     * @param key   of cache.
     * @param value of cache.
     */
    @Override
    public void set(Cache cache, int key, String value) {
        if (cache == null) {
            throw new IllegalArgumentException();
        }
        cache.setNode(key, value);
    }

    /**
     * Gets the value at key.
     *
     * @param cache is used.
     * @param key   of cache.
     * @return value of cache.
     */
    @Override
    public String get(Cache cache, int key) {
        if (cache == null) {
            throw new IllegalArgumentException();
        }
        return cache.getNode(key);
    }
}
