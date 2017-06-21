package com.javarush.task.task34.task3410.model;

import java.awt.*;

/**
 * Created by kalinnikov_al on 15.06.2017.
 */
public abstract class CollisionObject extends GameObject {
    public CollisionObject(int x, int y) {
        super(x, y);
    }

    public boolean isCollision(GameObject gameObject, Direction direction) {
        switch (direction.ordinal()){
            case 0:
                if (gameObject.getX() == (this.getX() - Model.FIELD_CELL_SIZE) && this.getY() == gameObject.getY()) return true;
                return false;
            case 1:
                if (gameObject.getX() == (this.getX() + Model.FIELD_CELL_SIZE) && this.getY() == gameObject.getY()) return true;
                return false;
            case 2:
                if (this.getX() == gameObject.getX() && gameObject.getY() == (this.getY() - Model.FIELD_CELL_SIZE)) return true;
                return false;
            case 3:
                if (this.getX() == gameObject.getX() && gameObject.getY() == (this.getY() + Model.FIELD_CELL_SIZE)) return true;
                return false;
        }

        return false;
    }
}
