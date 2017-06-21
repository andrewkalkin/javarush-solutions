package com.javarush.task.task37.task3712;

/**
 * Created by kalinnikov_al on 06.06.2017.
 */
public abstract class Game {
    abstract void prepareForTheGame();

    abstract void playGame();

    abstract void congratulateWinner();

    void run(){
        prepareForTheGame();
        playGame();
        congratulateWinner();
    }
}
