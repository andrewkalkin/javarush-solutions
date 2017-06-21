package com.javarush.task.task28.task2802;


import javafx.scene.layout.Priority;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* 
Пишем свою ThreadFactory
*/
public class Solution {

    public static class AmigoThreadFactory implements ThreadFactory{
        volatile static AtomicInteger fabricCount = new AtomicInteger(0);
        volatile AtomicInteger fabricId = new AtomicInteger(0);
        volatile AtomicInteger threadNum = new AtomicInteger(0);

        public AmigoThreadFactory(){
            fabricId.set(fabricCount.incrementAndGet());
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName(String.format("%s-pool-%d-thread-%d",Thread.currentThread().getThreadGroup().getName(),fabricId.get(),threadNum.incrementAndGet()));
            thread.setDaemon(false);
            thread.setPriority(Thread.NORM_PRIORITY);
            return thread;
        }
    }

    public static void main(String[] args) {
        class EmulateThreadFactoryTask implements Runnable {
            @Override
            public void run() {
                emulateThreadFactory();
            }
        }

        ThreadGroup group = new ThreadGroup("firstGroup");
        Thread thread = new Thread(group, new EmulateThreadFactoryTask());

        ThreadGroup group2 = new ThreadGroup("secondGroup");
        Thread thread2 = new Thread(group2, new EmulateThreadFactoryTask());

        thread.start();
        thread2.start();
    }

    private static void emulateThreadFactory() {
        AmigoThreadFactory factory = new AmigoThreadFactory();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        };
        factory.newThread(r).start();
        factory.newThread(r).start();
        factory.newThread(r).start();
    }
}
