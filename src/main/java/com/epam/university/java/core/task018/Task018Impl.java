package com.epam.university.java.core.task018;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class Task018Impl implements Task018 {
    /**
     * Check is annotation present in the following object.
     *
     * @param toCheck          object to check
     * @param annotationToFind annotation to look for
     * @return is annotation presents
     */
    @Override
    public boolean isAnnotationPresent(Object toCheck, Class<?> annotationToFind) {
        if (toCheck == null || annotationToFind == null) {
            throw new IllegalArgumentException();
        }

        boolean f = toCheck.getClass().isAnnotationPresent(
                (Class<? extends Annotation>) annotationToFind);
        if (f) {
            return true;
        }

        Method[] arrayOfMethods = toCheck.getClass().getDeclaredMethods();
        for (Method each : arrayOfMethods) {
            f = each.isAnnotationPresent((Class<? extends Annotation>) annotationToFind);
            Class<?>[] arrayOfParameters = each.getParameterTypes();
            for (Class<?> each2 : arrayOfParameters) {
                f = each2.isAnnotationPresent((Class<? extends Annotation>) annotationToFind);
            }

            Parameter[] arrayOfParameters2 = each.getParameters();
            for (Parameter each3 : arrayOfParameters2) {
                f = each3.isAnnotationPresent((Class<? extends Annotation>) annotationToFind);
            }
        }
        if (f) {
            return true;
        }

        Field[] arrayOfFields = toCheck.getClass().getDeclaredFields();
        for (Field each : arrayOfFields) {
            f = each.isAnnotationPresent((Class<? extends Annotation>) annotationToFind);
        }
        if (f) {
            return true;
        }

        Constructor[] arrayOfConstructors = toCheck.getClass().getDeclaredConstructors();
        for (Constructor each : arrayOfConstructors) {
            f = each.isAnnotationPresent((Class<? extends Annotation>) annotationToFind);
        }
        if (f) {
            return true;
        }


        Package packageOfObject = toCheck.getClass().getPackage();
        f = packageOfObject.isAnnotationPresent((Class<? extends Annotation>) annotationToFind);
        if (f) {
            return true;
        }

        return false;
    }
}
