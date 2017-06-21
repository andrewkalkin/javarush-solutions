package com.javarush.task.task37.task3702.male;

import com.javarush.task.task37.task3702.AbstractFactory;
import com.javarush.task.task37.task3702.Human;

/**
 * Created by kalinnikov_al on 23.05.2017.
 */
public class MaleFactory implements AbstractFactory{
    public Human getPerson(int age){
        return age <= KidBoy.MAX_AGE? new KidBoy(): age <= TeenBoy.MAX_AGE? new TeenBoy() : new Man();
    }
}
