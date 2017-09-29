package com.epam.university.java.core.task014;

import com.epam.university.java.core.task018.BasicAnnotation;
import com.sun.xml.internal.bind.annotation.OverrideAnnotationOf;
import jdk.nashorn.internal.objects.annotations.Constructor;

import java.beans.ConstructorProperties;
import java.lang.annotation.Target;

/**
 * Created by Вера on 19.09.2017.
 */
public class VampireNumberImpl implements VampireNumber {
    private int multilplication;
    private int first;
    private int second;


    public VampireNumberImpl(int multilplication) {
        this.multilplication = multilplication;
    }

    /**
     * This is constructor.
     * @param multilplication
     * @param first
     * @param second
     * @return VampireNumberImpl
     */
    public VampireNumberImpl(int multilplication, int first, int second) {
        this.multilplication = multilplication;
        this.first = first;
        this.second = second;
    }

    @Override
    public int getMultiplication() {

        return multilplication;
    }

    @Override
    public int getFirst() {
        return first;
    }

    @Override
    public int getSecond() {
        return second;
    }

    @Override
    public boolean equals(Object value) {
        if (this == value) {
            return true;
        }

        if (getClass() != value.getClass()) {
            return false;
        }
        VampireNumber v = (VampireNumber) value;

        if (multilplication != v.getMultiplication()) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = multilplication;
        //result = 31 * result + first;
        //result = 31 * result + second;
        return result;
    }
}
