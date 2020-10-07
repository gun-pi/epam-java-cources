package com.epam.university.java.core.task041;

import java.util.Collection;

public class Task041Impl implements Task041 {
    /**
     * Perform <p>Create</p> operation with <code>collection</code> of entities.
     * Tip: Pay attention that id of entity is unique.
     *
     * @param collection in which should create new entity.
     * @param value      for creation new entity.
     * @return created entity.
     */
    @Override
    public Entity create(Collection<Entity> collection, String value) {
        if (collection == null || value == null) {
            throw new IllegalArgumentException();
        }

        Entity temp = new EntityImpl(value, collection.size());
        collection.add(temp);

        return temp;
    }

    /**
     * Perform <p>Read</p> operation with <code>collection</code> of entities.
     *
     * @param collection from which should read entity.
     * @param entity     to be read.
     * @return read entity.
     */
    @Override
    public Entity read(Collection<Entity> collection, Entity entity) {
        if (collection == null || entity == null) {
            throw new IllegalArgumentException();
        }

        Entity temp = null;
        for (Entity each : collection) {
            if (each.getValue().equals(entity.getValue())) {
                temp = each;
            }
        }

        return temp;
    }

    /**
     * Perform <p>Update</p> operation with <code>collection</code> of entities.
     *
     * @param collection in which have to update entity object.
     * @param entity     to be updated.
     * @param value      that have to be changed in entity object.
     */
    @Override
    public void update(Collection<Entity> collection, Entity entity, String value) {
        if (collection == null || value == null || entity == null) {
            throw new IllegalArgumentException();
        }

        boolean flag = true;
        for (Entity each : collection) {
            if (entity.getValue().equals(each.getValue())
                && entity.getId() == each.getId()) {
                ((EntityImpl) each).setValue(value);
                flag = false;
            }
        }

        if (flag) {
            throw new IllegalArgumentException();
        }

    }

    /**
     * Perform <p>Delete</p> operation with <code>collection</code> of entities.
     *
     * @param collection from which have to delete object.
     * @param entity     to be deleted.
     */
    @Override
    public void delete(Collection<Entity> collection, Entity entity) {
        if (collection == null || entity == null) {
            throw new IllegalArgumentException();
        }

        Entity temp = null;
        for (Entity each : collection) {
            if (entity.getValue().equals(each.getValue())
                    && entity.getId() == each.getId()) {
                temp = each;
            }
        }

        collection.remove(temp);
    }
}
