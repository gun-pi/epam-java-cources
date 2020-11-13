package com.epam.university.java.core.task060;

public class NodeImpl implements Node {
    private int key;
    private String value;
    private Node prev;
    private Node next;

    public NodeImpl(int key, String value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Getter for value.
     *
     * @return value of Node.
     */
    @Override
    public String getValue() {
        return value;
    }

    /**
     * Setter for value.
     *
     * @param value of Node
     */
    @Override
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Getter for key.
     *
     * @return key of Node.
     */
    @Override
    public int getKey() {
        return key;
    }

    /**
     * Setter for key.
     *
     * @param key of Node
     */
    @Override
    public void setKey(int key) {
        this.key = key;
    }

    /**
     * Getter for previous Node.
     *
     * @return previous Node.
     */
    @Override
    public Node getPrev() {
        return prev;
    }

    /**
     * Setter for previous Node.
     *
     * @param prev Node.
     */
    @Override
    public void setPrev(Node prev) {
        this.prev = prev;
    }

    /**
     * Getter for next Node.
     *
     * @return next Node.
     */
    @Override
    public Node getNext() {
        return next;
    }

    /**
     * Setter for next Node.
     *
     * @param next Node.
     */
    @Override
    public void setNext(Node next) {
        this.next = next;
    }
}
