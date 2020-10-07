package com.epam.university.java.core.task041;

public class EntityImpl implements Entity {
    private int id;
    private String value;

    EntityImpl(String value, int id) {
        this.value = value;
        this.id = id;
    }

    /**
     * getter for id.
     *
     * @return id of entity
     */
    @Override
    public int getId() {
        return id;
    }

    /**
     * getter of value.
     *
     * @return value of entity
     */
    @Override
    public String getValue() {
        return value;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
