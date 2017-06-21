package com.javarush.task.task35.task3513;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by kalinnikov_al on 23.04.2017.
 */
public class Model {
    private static final int FIELD_WIDTH = 4;
    private Tile[][] gameTiles;
    int score;
    int maxTile;

    private Stack<Tile[][]> previousStates = new Stack<>();
    private Stack<Integer> previousScores = new Stack<>();
    private boolean isSaveNeeded = true;

    public Model() {

        resetGameTiles();

    }

    private void addTile() {
        List<Tile> emptyList = getEmptyTiles();
        if (!emptyList.isEmpty()) {
            getEmptyTiles().get((int) (Math.random() * emptyList.size())).value = Math.random() < 0.9 ? 2 : 4;
        }
    }

    private List<Tile> getEmptyTiles() {

        List<Tile> result = new ArrayList<>();

        for (int i = 0; i < FIELD_WIDTH; i++)
            for (int j = 0; j < FIELD_WIDTH; j++)
                if (gameTiles[i][j].isEmpty()) result.add(gameTiles[i][j]);

        return result;

    }

    protected void resetGameTiles() {
        gameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i < FIELD_WIDTH; i++)
            for (int j = 0; j < FIELD_WIDTH; j++)
                this.gameTiles[i][j] = new Tile();


        this.score = 0;
        this.maxTile = 2;

        addTile();
        addTile();
    }

    private boolean compressTiles(Tile[] tiles) {
        boolean change = false;
        for (int i = 1; i < tiles.length; i++) {
            for (int j = 1; j < tiles.length; j++) {
                if (tiles[j - 1].isEmpty() && !tiles[j].isEmpty()) {
                    change = true;
                    tiles[j - 1] = tiles[j];
                    tiles[j] = new Tile();
                }
            }
        }
        return change;
    }

    private boolean mergeTiles(Tile[] tiles) {
        boolean change = false;
        for (int i = 1; i < tiles.length; i++) {
            if ((tiles[i - 1].value == tiles[i].value) && !tiles[i - 1].isEmpty() && !tiles[i].isEmpty()) {
                change = true;
                tiles[i - 1].value *= 2;
                score += tiles[i - 1].value;
                maxTile = maxTile > tiles[i - 1].value ? maxTile : tiles[i - 1].value;
                tiles[i] = new Tile();
                compressTiles(tiles);
            }
        }
        return change;
    }

    public void left() {
        if (isSaveNeeded) saveState(gameTiles);
        boolean change = false;
        for (int i = 0; i < FIELD_WIDTH; i++) {
            if (compressTiles(gameTiles[i]) | mergeTiles(gameTiles[i])) change = true;
        }
        if (change) addTile();
        isSaveNeeded = true;
    }

    public void right() {
        saveState(gameTiles);
        rotate();
        rotate();
        left();
        rotate();
        rotate();
    }

    public void up() {
        saveState(gameTiles);
        rotate();
        rotate();
        rotate();
        left();
        rotate();
    }

    public void down() {
        saveState(gameTiles);
        rotate();
        left();
        rotate();
        rotate();
        rotate();
    }

    private void rotate() {
        for (int i = 0; i < FIELD_WIDTH; i++)
            for (int j = i; j < FIELD_WIDTH - i - 1; j++) {
                Tile tmp = gameTiles[i][j];
                gameTiles[i][j] = gameTiles[FIELD_WIDTH - 1 - j][i];
                gameTiles[FIELD_WIDTH - 1 - j][i] = gameTiles[FIELD_WIDTH - 1 - i][FIELD_WIDTH - 1 - j];
                gameTiles[FIELD_WIDTH - 1 - i][FIELD_WIDTH - 1 - j] = gameTiles[j][FIELD_WIDTH - 1 - i];
                gameTiles[j][FIELD_WIDTH - 1 - i] = tmp;
            }
    }

    public Tile[][] getGameTiles() {
        return gameTiles;
    }

    public boolean canMove ()
    {
        if(!getEmptyTiles().isEmpty())
            return true;

        for(int i = 0; i < gameTiles.length; i++) {
            for(int j = 1; j < gameTiles.length; j++) {
                if(gameTiles[i][j].value == gameTiles[i][j-1].value)
                    return true;
            }
        }

        for(int j = 0; j < gameTiles.length; j++) {
            for(int i = 1; i < gameTiles.length; i++) {
                if(gameTiles[i][j].value == gameTiles[i-1][j].value)
                    return true;
            }
        }

        return false;
    }

    private void saveState(Tile[][] tiles){
        Tile[][] newTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];

        for (int i = 0; i < FIELD_WIDTH; i++)
            for (int j = 0; j < FIELD_WIDTH; j++){
                newTiles[i][j] = new Tile(tiles[i][j].value);
            }

        previousStates.push(newTiles);
        previousScores.push(score);
        isSaveNeeded = false;
    }

    public void rollback(){
        if (!previousStates.isEmpty() && !previousScores.isEmpty()) {
            gameTiles = previousStates.pop();
            score = previousScores.pop();
        }
    }

    public void randomMove(){
        int n =  ((int) (Math.random() * 100)) % 4;
        switch (n) {
            case 0:
                left();
                break;
            case 1:
                right();
                break;
            case 2:
                up();
                break;
            case 3:
                down();
                break;
        }
    }

    boolean hasBoardChanged(){
        Tile[][] lastTiles = previousStates.peek();
        int sum1 = 0, sum2 = 0;

        for (int i = 0; i < FIELD_WIDTH; i++)
            for (int j = 0; j < FIELD_WIDTH; j++) {
               sum1 += lastTiles[i][j].value;
               sum2 += gameTiles[i][j].value;
            }

        return sum1 != sum2;
    }

    MoveEfficiency getMoveEfficiency(Move move){
        move.move();
        MoveEfficiency moveEfficiency;
        if (hasBoardChanged()) moveEfficiency = new MoveEfficiency(getEmptyTiles().size(), score, move); else moveEfficiency = new MoveEfficiency(-1, 0, move);
        rollback();
        return moveEfficiency;
    }


    public void autoMove(){
        PriorityQueue<MoveEfficiency> pQueue = new PriorityQueue<>(4, Collections.reverseOrder());
        pQueue.offer(getMoveEfficiency(this::left));
        pQueue.offer(getMoveEfficiency(this::right));
        pQueue.offer(getMoveEfficiency(this::up));
        pQueue.offer(getMoveEfficiency(this::down));
        pQueue.peek().getMove().move();
    }

}
