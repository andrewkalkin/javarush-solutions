package com.javarush.task.task34.task3410.controller;

import com.javarush.task.task34.task3410.model.Direction;

/**
 * Created by kalinnikov_al on 16.06.2017.
 */
public interface EventListener {
    public void move(Direction direction);
    public void restart();
    public void startNextLevel();
    public void levelCompleted(int level);
}
