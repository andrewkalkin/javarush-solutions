package com.javarush.task.task32.task3205;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by kalinnikov_al on 02.05.2017.
 */
public class CustomInvocationHandler implements InvocationHandler {
    private SomeInterfaceWithMethods someInterface;

    public CustomInvocationHandler(SomeInterfaceWithMethods someInterface) {
        this.someInterface = someInterface;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result;
        System.out.println(method.getName() + " in");
        result = method.invoke(someInterface, args);
        System.out.println(method.getName() + " out");
        return result;
    }
}
