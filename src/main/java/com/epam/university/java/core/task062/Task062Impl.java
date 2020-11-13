package com.epam.university.java.core.task062;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class Task062Impl implements Task062 {

    /**
     * Object serialization.
     *
     * @param obj object to serialize
     * @return an output stream of serialized object
     */
    @Override
    public OutputStream objectSerialization(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException();
        }

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream;
        try {
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(obj);
            objectOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return byteArrayOutputStream;
    }

    /**
     * Object deserialization.
     *
     * @param outStream output stream of serialized object
     * @return a deserialized object
     */
    @Override
    public Object objectDeserialization(OutputStream outStream) {
        if (outStream == null) {
            throw new IllegalArgumentException();
        }

        ByteArrayOutputStream byteArrayOutputStream = (ByteArrayOutputStream) outStream;
        byte[] bytes = byteArrayOutputStream.toByteArray();
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            Object object = objectInputStream.readObject();
            if (object instanceof SingletonObject) {
                return SingletonObject.getInstance();
            } else {
                return object;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return  null;
    }
}
