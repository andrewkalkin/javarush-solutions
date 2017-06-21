package com.javarush.task.task36.task3602;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* 
Найти класс по описанию
*/
public class Solution {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        System.out.println(getExpectedClass());
    }

    public static Class getExpectedClass() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        for (Class clazz: Collections.class.getDeclaredClasses()) {


            if (Modifier.isPrivate(clazz.getModifiers()) && Modifier.isStatic(clazz.getModifiers())) {

                    for (Class cls: clazz.getSuperclass().getInterfaces()) {
                        if (cls.equals(List.class)) {
                            Constructor[] constructors = clazz.getDeclaredConstructors();
                            for (Constructor constructor: constructors) {
                                constructor.setAccessible(true);
                                if (constructor.getParameterCount() == 0) {
                                    List list = (List) constructor.newInstance(new Class[0]);
                                    try {
                                        list.get(0);
                                    } catch (IndexOutOfBoundsException e) {
                                        return clazz;
                                    }

                                }
                            }
                        }
                    }
                }

            }

        return null;
    }
}
