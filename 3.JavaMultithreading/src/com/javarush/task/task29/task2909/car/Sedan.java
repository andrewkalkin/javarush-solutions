package com.javarush.task.task29.task2909.car;

/**
 * Created by kalinnikov_al on 04.03.2017.
 */
public class Sedan extends Car {
    public Sedan(int numberOfPassengers) {
        super(SEDAN, numberOfPassengers);
    }

    public int getMaxSpeed(){
        final int MAX_SEDAN_SPEED = 120;
        return MAX_SEDAN_SPEED;
    }
}
