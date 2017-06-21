package com.javarush.task.task30.task3004;

import java.util.concurrent.RecursiveTask;

/**
 * Created by kalinnikov_al on 21.04.2017.
 */
public class BinaryRepresentationTask extends RecursiveTask<String> {

    private int number;

    public BinaryRepresentationTask(int x) {
        this.number = x;
    }

    @Override
    protected String compute() {

        if (number/2 > 0) {
            BinaryRepresentationTask rest = new BinaryRepresentationTask(number/2);
            rest.fork();
            return rest.join() + String.valueOf(number%2);
        }
        return String.valueOf(number%2);

    }
}
