package com.epam.university.java.core.task060;

import java.util.LinkedHashMap;
import java.util.Map;

public class CacheImpl implements Cache {
    private LinkedHashMap<Integer, String> cache;
    private static final int MAX_ENTRIES = 4;

    /**
     * Constructor.
     *
     * @param size size
     */
    public CacheImpl(int size) {
        cache = new LinkedHashMap<Integer, String>(size, 0.75f, true) {
            protected boolean removeEldestEntry(Map.Entry<Integer, String> eldest) {
                return size() > MAX_ENTRIES;
            }
        };
    }

    /**
     * Get Node from cache.
     *
     * @param key of Node.
     * @return Node from cache.
     */
    @Override
    public String getNode(int key) {
        return cache.get(key) == null ? "0" : cache.get(key);
    }

    /**
     * Add or update Node in cache.
     *
     * @param key   of Node.
     * @param value of Node.
     */
    @Override
    public void setNode(int key, String value) {
        cache.put(key, value);
    }
}
