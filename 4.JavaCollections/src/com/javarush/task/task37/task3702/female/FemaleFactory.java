package com.javarush.task.task37.task3702.female;

import com.javarush.task.task37.task3702.AbstractFactory;
import com.javarush.task.task37.task3702.Human;

/**
 * Created by kalinnikov_al on 23.05.2017.
 */
public class FemaleFactory implements AbstractFactory{
    public Human getPerson(int age){
        return age <= KidGirl.MAX_AGE? new KidGirl(): age <= TeenGirl.MAX_AGE? new TeenGirl() : new Woman();
    }
}
