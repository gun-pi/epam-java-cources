package com.epam.university.java.core.task011;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Task011Impl implements Task011 {
    /**
     * Given a circle of men, on each iteration one man leaves it through one. You should determine
     * name of last man.
     *
     * <p>
     * Example: source collection: Homer Bart Maggie Lisa Marge
     * First iteration: Homer leaves, Bart Maggie List Marge remained
     * Second iteration: Maggie leaves, Bart List Marge remained
     * Third iteration: Marge leaves, Bart and Lise remained
     * Fourth iteration: Lisa leaves, Bart is the last one
     * </p>
     * <p>
     * Implementation with arrays
     * </p>
     *
     * @param collection collection of names
     * @return name of last man
     */
    @Override
    public String getLastName(String[] collection) {
        if (collection == null) {
            throw new IllegalArgumentException();
        }

        List<String> collectionAsList = new ArrayList<>(Arrays.asList(collection));

        if (collectionAsList.isEmpty()) {
            throw new IllegalArgumentException();
        }

        String temp = "";
        int i = 0;

        do {
            temp = collectionAsList.get(i);
            collectionAsList.remove(i);
            if (collectionAsList.size() == 0) {
                break;
            }
            i += 1;
            while (i > collectionAsList.size() - 1) {
                i -= collectionAsList.size();
            }
        } while (!collectionAsList.isEmpty());

        return temp;
    }

    /**
     * Given a circle of men, on each iteration one man leaves it through one. You should determine
     * name of last man.
     *
     * <p>
     * Example: source collection: Homer Bart Maggie Lisa Marge
     * First iteration: Homer leaves, Bart Maggie List Marge remained
     * Second iteration: Maggie leaves, Bart List Marge remained
     * Third iteration: Marge leaves, Bart and Lise remained
     * Fourth iteration: Lisa leaves, Bart is the last one
     * </p>
     * <p>
     * Implementation with ArrayList
     * </p>
     *
     * @param collection collection of names
     * @return name of last man
     */
    @Override
    public String getLastName(ArrayList<String> collection) {
        if (collection == null) {
            throw new IllegalArgumentException();
        }

        List<String> collectionAsList = collection;

        if (collectionAsList.isEmpty()) {
            throw new IllegalArgumentException();
        }

        String temp = "";
        int i = 0;

        do {
            temp = collectionAsList.get(i);
            collectionAsList.remove(i);
            if (collectionAsList.size() == 0) {
                break;
            }
            i += 1;
            while (i > collectionAsList.size() - 1) {
                i -= collectionAsList.size();
            }
        } while (!collectionAsList.isEmpty());

        return temp;
    }

    /**
     * Given a circle of men, on each iteration one man leaves it through one. You should determine
     * name of last man.
     *
     * <p>
     * Example: source collection: Homer Bart Maggie Lisa Marge
     * First iteration: Homer leaves, Bart Maggie List Marge remained
     * Second iteration: Maggie leaves, Bart List Marge remained
     * Third iteration: Marge leaves, Bart and Lise remained
     * Fourth iteration: Lisa leaves, Bart is the last one
     * </p>
     * <p>
     * Implementation with LinkedList
     * </p>
     *
     * @param collection collection of names
     * @return name of last man
     */
    @Override
    public String getLastName(LinkedList<String> collection) {
        if (collection == null) {
            throw new IllegalArgumentException();
        }

        List<String> collectionAsList = collection;

        if (collectionAsList.isEmpty()) {
            throw new IllegalArgumentException();
        }

        String temp = "";
        int i = 0;

        do {
            temp = collectionAsList.get(i);
            collectionAsList.remove(i);
            if (collectionAsList.size() == 0) {
                break;
            }
            i += 1;
            while (i > collectionAsList.size() - 1) {
                i -= collectionAsList.size();
            }
        } while (!collectionAsList.isEmpty());

        return temp;
    }
}
