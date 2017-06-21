package com.javarush.task.task28.task2805;

/**
 * Created by kalinnikov_al on 10.03.2017.
 */
public class MyThread extends Thread {

    private static int priority=Thread.MIN_PRIORITY;

    private void cyclicPriority(){
        priority++;
        if (Thread.currentThread().getThreadGroup() == null){
            if (priority > Thread.MAX_PRIORITY) priority = Thread.MIN_PRIORITY;
        } else
            if (priority > Thread.currentThread().getThreadGroup().getMaxPriority()) priority = Thread.MIN_PRIORITY;

    }

    public MyThread() {
        setPriority(priority);
        cyclicPriority();

    }

    public MyThread(Runnable target) {
        super(target);
        setPriority(priority);
        cyclicPriority();
    }

    public MyThread(ThreadGroup group, Runnable target) {
        super(group, target);
        setPriority(priority);
        cyclicPriority();
    }

    public MyThread(String name) {
        super(name);
        setPriority(priority);
        cyclicPriority();
    }

    public MyThread(ThreadGroup group, String name) {
        super(group, name);
        setPriority(priority);
        cyclicPriority();
    }

    public MyThread(Runnable target, String name) {
        super(target, name);
        setPriority(priority);
        cyclicPriority();
    }

    public MyThread(ThreadGroup group, Runnable target, String name) {
        super(group, target, name);
        setPriority(priority);
        cyclicPriority();
    }

    public MyThread(ThreadGroup group, Runnable target, String name, long stackSize) {
        super(group, target, name, stackSize);
        setPriority(priority);
        cyclicPriority();
    }
}
