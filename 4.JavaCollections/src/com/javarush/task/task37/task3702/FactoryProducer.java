package com.javarush.task.task37.task3702;

import com.javarush.task.task37.task3702.female.FemaleFactory;
import com.javarush.task.task37.task3702.male.MaleFactory;

/**
 * Created by kalinnikov_al on 23.05.2017.
 */
public class FactoryProducer {
    public static enum HumanFactoryType {
        MALE,
        FEMALE
    }

    public static AbstractFactory getFactory(HumanFactoryType type){
        return type.equals(HumanFactoryType.FEMALE) ? new FemaleFactory() : new MaleFactory();
    }
}
