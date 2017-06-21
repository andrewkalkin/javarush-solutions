package com.javarush.task.task34.task3410.model;

import com.javarush.task.task34.task3410.controller.EventListener;

import java.nio.file.Paths;

/**
 * Created by kalinnikov_al on 15.06.2017.
 */
public class Model {
    public static final int FIELD_CELL_SIZE = 20;

    EventListener eventListener;

    GameObjects gameObjects;
    int currentLevel = 4;
    LevelLoader levelLoader = new LevelLoader( Paths.get("4.JavaCollections","src","com","javarush","task","task34","task3410","res","levels.txt"));

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public GameObjects getGameObjects(){
        return gameObjects;
    }

    public void restartLevel(int level){
        this.gameObjects = levelLoader.getLevel(level);
    }

    public void restart(){

        restartLevel(currentLevel);
    }

    public void startNextLevel() {
        restartLevel(++currentLevel);
    }

    public void move(Direction direction) {
        if (checkWallCollision(gameObjects.getPlayer(), direction)) return;
        if (checkBoxCollisionAndMoveIfAvaliable(direction)) return;

        switch (direction.ordinal()) {
            case 0:
                gameObjects.getPlayer().move(-FIELD_CELL_SIZE,0);
                break;
            case 1:
                gameObjects.getPlayer().move(FIELD_CELL_SIZE,0);
                break;
            case 2:
                gameObjects.getPlayer().move(0, -FIELD_CELL_SIZE);
                break;
            case 3:
                gameObjects.getPlayer().move(0, FIELD_CELL_SIZE);
                break;
        }

        checkCompletion();
    }

    public boolean checkWallCollision(CollisionObject gameObject, Direction direction){
        for (Wall wall: gameObjects.getWalls()) {
            if (gameObject.isCollision(wall, direction)) return true;
        }

        return false;
    }

    public boolean checkBoxCollisionAndMoveIfAvaliable(Direction direction){
        for (Box box : gameObjects.getBoxes()) {
            if (gameObjects.getPlayer().isCollision(box, direction)) {
                if (checkWallCollision(box,direction)) return true;
                for (Box box2: gameObjects.getBoxes()){
                    if (box.isCollision(box2,direction)) return true;
                }
            }
        }

        for (Box box : gameObjects.getBoxes()) {
            if (gameObjects.getPlayer().isCollision(box, direction)) {
                switch (direction.ordinal()) {
                    case 0:
                        box.move(-FIELD_CELL_SIZE,0);
                        return false;
                    case 1:
                        box.move(FIELD_CELL_SIZE,0);
                        return false;
                    case 2:
                        box.move(0, -FIELD_CELL_SIZE);
                        return false;
                    case 3:
                        box.move(0, FIELD_CELL_SIZE);
                        return false;
                }
            }
        }


        return false;
    }

    public void checkCompletion(){

        int count = gameObjects.getHomes().size();

        for (Home home: gameObjects.getHomes())
            for (Box box : gameObjects.getBoxes()) {
                if (home.getX() == box.getX() && home.getY() == box.getY()) count--;
            }

        if (count == 0) eventListener.levelCompleted(currentLevel);
    }
}
