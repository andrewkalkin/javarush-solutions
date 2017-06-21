package com.javarush.task.task34.task3410.model;

import java.awt.*;

/**
 * Created by kalinnikov_al on 16.06.2017.
 */
public class Home extends GameObject {
    public Home(int x, int y) {
        super(x, y);
        setWidth(2);
        setHeight(2);
    }

    @Override
    public void draw(Graphics graphics) {

        int xLeft = x - getWidth() / 2;
        int yLeft = y - getHeight() / 2;

        graphics.setColor(Color.RED);
        graphics.drawOval(xLeft, yLeft, getWidth(), getHeight());
    }
}
