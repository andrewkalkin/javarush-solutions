package com.javarush.task.task25.task2506;

/**
 * Created by kalinnikov_al on 21.02.2017.
 */

public class LoggingStateThread extends Thread {
    Thread thread;

    public LoggingStateThread(Thread thread) {

        super();
        this.thread = thread;


    }

    public void run() {


        State state = thread.getState();
        System.out.println(state);
        while (state != State.TERMINATED)
        {
            if (state != thread.getState())
            {
                state = thread.getState();
                System.out.println(state);
            }
        }


    }

}
