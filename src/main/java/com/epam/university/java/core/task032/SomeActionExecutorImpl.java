package com.epam.university.java.core.task032;

import java.lang.reflect.Method;

public class SomeActionExecutorImpl implements SomeActionExecutor {
    private final CountingProxy countingProxy;

    public SomeActionExecutorImpl(CountingProxy countingProxy) {
        this.countingProxy = countingProxy;
    }

    /**
     * Do the action, not important for us.
     */
    @Override
    public void doAction() {
        try {
            Method method = SomeActionExecutorImpl.class.getDeclaredMethod("doAction");
            countingProxy.invoke(countingProxy, method, null);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    /**
     * Do another action, not important for us.
     */
    @Override
    public void doAnotherAction() {
        try {
            Method method = SomeActionExecutorImpl.class.getDeclaredMethod("doAnotherAction");
            countingProxy.invoke(countingProxy, method, null);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
