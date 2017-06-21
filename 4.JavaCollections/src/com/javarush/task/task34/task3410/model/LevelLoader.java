package com.javarush.task.task34.task3410.model;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by kalinnikov_al on 16.06.2017.
 */
public class LevelLoader {
    private Path levels;

    public LevelLoader(Path levels) {
        this.levels = levels;
    }

    public GameObjects getLevel(int level){

        Set<Home> houses = new HashSet<>();
        Set<Box> boxes = new HashSet<>();
        Set<Wall> walls = new HashSet<>();
        Player player = null;

        level = level % 60;
        if (level == 0) level = 60;

        Pattern pLevel = Pattern.compile("^Maze: ([0-9]+)$");
        Pattern pX = Pattern.compile("^Size X: ([0-9]+)$");
        Pattern pY = Pattern.compile("^Size Y: ([0-9]+)$");
        Matcher matcher;
        int sizeX, sizeY;


        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(levels.toFile()))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                matcher = pLevel.matcher(line);


                if (matcher.matches() && Integer.parseInt(matcher.group(1)) == level) {

                    line = bufferedReader.readLine();
                    line = bufferedReader.readLine();
                    matcher = pX.matcher(line);
                    matcher.matches();
                    sizeX = Integer.parseInt(matcher.group(1));
                    line = bufferedReader.readLine();
                    matcher = pY.matcher(line);
                    matcher.matches();
                    sizeY = Integer.parseInt(matcher.group(1));
                    line = bufferedReader.readLine();
                    line = bufferedReader.readLine();
                    line = bufferedReader.readLine();


                    for (int i = 0; i < sizeY; i++){
                        line = bufferedReader.readLine();
                        for (int j = 0; j < sizeX; j++){
                            switch (line.charAt(j)){
                                case 'X':
                                    walls.add(new Wall(j == 0 ? Model.FIELD_CELL_SIZE / 2: Model.FIELD_CELL_SIZE / 2 + j*Model.FIELD_CELL_SIZE, i == 0 ? Model.FIELD_CELL_SIZE / 2: Model.FIELD_CELL_SIZE / 2 + i*Model.FIELD_CELL_SIZE ));
                                    break;
                                case '*':
                                    boxes.add(new Box(j == 0 ? Model.FIELD_CELL_SIZE / 2: Model.FIELD_CELL_SIZE / 2 + j*Model.FIELD_CELL_SIZE, i == 0 ? Model.FIELD_CELL_SIZE / 2: Model.FIELD_CELL_SIZE / 2 + i*Model.FIELD_CELL_SIZE ));
                                    break;
                                case '.':
                                    houses.add(new Home(j == 0 ? Model.FIELD_CELL_SIZE / 2: Model.FIELD_CELL_SIZE / 2 + j*Model.FIELD_CELL_SIZE, i == 0 ? Model.FIELD_CELL_SIZE / 2: Model.FIELD_CELL_SIZE / 2 + i*Model.FIELD_CELL_SIZE ));
                                    break;
                                case '@':
                                    player = new Player(j == 0 ? Model.FIELD_CELL_SIZE / 2: Model.FIELD_CELL_SIZE / 2 + j*Model.FIELD_CELL_SIZE, i == 0 ? Model.FIELD_CELL_SIZE / 2: Model.FIELD_CELL_SIZE / 2 + i*Model.FIELD_CELL_SIZE );
                                    break;
                                case '&':
                                    boxes.add(new Box(j == 0 ? Model.FIELD_CELL_SIZE / 2: Model.FIELD_CELL_SIZE / 2 + j*Model.FIELD_CELL_SIZE, i == 0 ? Model.FIELD_CELL_SIZE / 2: Model.FIELD_CELL_SIZE / 2 + i*Model.FIELD_CELL_SIZE ));
                                    houses.add(new Home(j == 0 ? Model.FIELD_CELL_SIZE / 2: Model.FIELD_CELL_SIZE / 2 + j*Model.FIELD_CELL_SIZE, i == 0 ? Model.FIELD_CELL_SIZE / 2: Model.FIELD_CELL_SIZE / 2 + i*Model.FIELD_CELL_SIZE ));
                                    break;
                            }
                        }
                    }

                    break;
                }




            }
        } catch (Exception ignored) {

        }



        GameObjects gObjs = new GameObjects(walls, boxes, houses, player);


        return gObjs;
    }
}
