package com.javarush.task.task38.task3803;

/* 
Runtime исключения (unchecked exception)
*/


public class VeryComplexClass {
    public void methodThrowsClassCastException() {
        Object x = new Integer(0);
        String str = (String) x;

    }

    public void methodThrowsNullPointerException() {
        Integer i = null;
        i.byteValue();

    }

    public static void main(String[] args) {

    }
}
