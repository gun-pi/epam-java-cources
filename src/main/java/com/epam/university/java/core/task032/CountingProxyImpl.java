package com.epam.university.java.core.task032;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class CountingProxyImpl implements CountingProxy {
    private final Map<String, Integer> mapOfMethods = new HashMap<>();

    /**
     * Get amount of method call.
     *
     * @param methodName method name
     * @return amount of call
     */
    @Override
    public int getInvocationsCount(String methodName) {
        return mapOfMethods.get(methodName);
    }

    /**
     * Processes a method invocation on a proxy instance and returns
     * the result.  This method will be invoked on an invocation handler
     * when a method is invoked on a proxy instance that it is
     * associated with.
     *
     * @param proxy  the proxy instance that the method was invoked on
     * @param method the {@code Method} instance corresponding to
     *               the interface method invoked on the proxy instance.
     * @param args   an array of objects containing the values of the
     *               arguments passed in the method invocation on the proxy instance,
     *               or {@code null} if interface method takes no arguments.
     * @return the value to return from the method invocation on the
     *     proxy instance.
     * @throws Throwable the exception to throw from the method
     *                   invocation on the proxy instance.
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (mapOfMethods.containsKey(method.getName())) {
            int temp = mapOfMethods.get(method.getName()) + 1;
            mapOfMethods.put(method.getName(), temp);
        } else {
            mapOfMethods.put(method.getName(), 1);
        }
        return proxy;
    }
}
