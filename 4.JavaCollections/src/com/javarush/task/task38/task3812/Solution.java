package com.javarush.task.task38.task3812;

/* 
Обработка аннотаций
*/

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;

public class Solution {
    public static void main(String[] args) {
        printFullyQualifiedNames(Solution.class);
        printFullyQualifiedNames(SomeTest.class);

        printValues(Solution.class);
        printValues(SomeTest.class);
    }

    public static boolean printFullyQualifiedNames(Class c) {
        if (c.isAnnotationPresent(PrepareMyTest.class)){
            Annotation an = c.getAnnotation(PrepareMyTest.class);
            try {
                String[] quaNames = (String[]) an.annotationType().getDeclaredMethod("fullyQualifiedNames", new Class[]{}).invoke(an, null);
                for (String name: quaNames) System.out.println(name);
                return true;
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean printValues(Class c) {

        if (c.isAnnotationPresent(PrepareMyTest.class)){
            Annotation an = c.getAnnotation(PrepareMyTest.class);
            try {
                Class[] quaNames = (Class[]) an.annotationType().getDeclaredMethod("value", new Class[]{}).invoke(an, null);
                for (Class clazz: quaNames) System.out.println(clazz.getSimpleName());
                return true;
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
