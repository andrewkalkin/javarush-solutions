package com.javarush.task.task34.task3410.model;

import java.awt.*;

/**
 * Created by kalinnikov_al on 15.06.2017.
 */
public abstract class GameObject {
    int x;
    int y;
    int width;
    int height;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public GameObject(int x, int y){
        this(x, y, Model.FIELD_CELL_SIZE, Model.FIELD_CELL_SIZE);
    }

    public GameObject(int x, int y, int width, int height){
        setX(x);
        setY(y);
        setWidth(width);
        setHeight(height);
    }

    public abstract void draw(Graphics graphics);
}
