package com.javarush.task.task29.task2909.human;

/**
 * Created by kalinnikov_al on 03.03.2017.
 */
public class Soldier extends Human{

    protected int course;

    public Soldier(String name, int age){
        super(name, age);
    }


    public void live() {
            fight();
    }

    public void fight() {
    }

    public int getCourse() {
        return course;
    }
}
