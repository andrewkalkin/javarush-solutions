package com.javarush.task.task34.task3410.model;

import java.awt.*;

/**
 * Created by kalinnikov_al on 16.06.2017.
 */
public class Box extends CollisionObject implements Movable{
    public Box(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics) {

        int xLeft = x - getWidth() / 2;
        int yLeft = y - getHeight() / 2;
        int xRight = x + getWidth() / 2;
        int yRight = y + getHeight() / 2;


        graphics.setColor(new Color(230,123,6));
        graphics.fillRect(xLeft, yLeft, getWidth(), getHeight());
        graphics.setColor(Color.BLACK);
        graphics.fillRect(xLeft + 2, yLeft + 2, getWidth() - 4, getHeight() - 4);
        graphics.setColor(new Color(230,123,6));
        graphics.drawLine(xLeft , yLeft, xRight - 1, yRight - 1);
        graphics.drawLine(xRight - 1 , yLeft, xLeft , yRight - 1);


    }

    @Override
    public void move(int x, int y) {
        setX(getX() + x);
        setY(getY() + y);
    }
}
