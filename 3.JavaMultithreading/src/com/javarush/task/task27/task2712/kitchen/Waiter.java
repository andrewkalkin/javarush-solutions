package com.javarush.task.task27.task2712.kitchen;


import com.javarush.task.task27.task2712.ConsoleHelper;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by kalinnikov_al on 18.04.2017.
 */
public class Waiter implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        ConsoleHelper.writeMessage(arg + " was cooked by " + o);
    }
}
