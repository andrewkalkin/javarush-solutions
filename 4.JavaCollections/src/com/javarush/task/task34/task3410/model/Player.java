package com.javarush.task.task34.task3410.model;

import java.awt.*;

/**
 * Created by kalinnikov_al on 16.06.2017.
 */
public class Player extends CollisionObject implements Movable{
    public Player(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics) {

        int xLeft = x - getWidth() / 2;
        int yLeft = y - getHeight() / 2;

        graphics.setColor(Color.YELLOW);
        graphics.fillOval(xLeft, yLeft, getWidth(), getHeight());

    }

    @Override
    public void move(int x, int y) {
        setX(getX() + x);
        setY(getY() + y);
    }
}
