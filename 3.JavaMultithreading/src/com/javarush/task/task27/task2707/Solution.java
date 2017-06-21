package com.javarush.task.task27.task2707;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Timer;

/*
Определяем порядок захвата монитора
*/
public class Solution {
    public void someMethodWithSynchronizedBlocks(Object obj1, Object obj2) {
        synchronized (obj1) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ignored) {
            }

            synchronized (obj2) {
                System.out.println(obj1 + " " + obj2);
            }
        }
    }

    public static boolean isNormalLockOrder(final Solution solution, final Object o1, final Object o2) throws Exception {
        //do something here

        boolean sync = false;

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                solution.someMethodWithSynchronizedBlocks(o2,o1);
            }
        });

        synchronized (o1) {
            thread1.start();
            Thread.sleep(20);

            if (thread1.getState().equals(Thread.State.BLOCKED)) sync = true;

        }

        if (sync) {
            sync = false;
            synchronized (o2) {
                Thread.sleep(200);
                if (thread1.getState().equals(Thread.State.BLOCKED)) sync = true;
            }
        }


        return sync;


    }

    public static void main(String[] args) throws Exception {
        final Solution solution = new Solution();
        final Object o1 = new Object();
        final Object o2 = new Object();

        System.out.println(isNormalLockOrder(solution, o1, o2));
    }
}
