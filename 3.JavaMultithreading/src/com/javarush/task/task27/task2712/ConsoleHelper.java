package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by kalinnikov_al on 18.04.2017.
 */
public class ConsoleHelper {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message){
        System.out.println(message);
    }

    public static String readString() throws IOException {
        return reader.readLine();
    }

    public static List<Dish> getAllDishesForOrder() throws IOException {
        List<Dish> dishes = new ArrayList<>();
        Dish orderDish;
        String menu = Arrays.toString(Dish.values());

        while (true) {
            writeMessage(Dish.allDishesToString());
            writeMessage("Введите название блюда: ");
            String dish = readString();

            if (!"exit".equals(dish)) {

                if (menu.contains(dish)) {
                    dishes.add(Dish.valueOf(dish));
                } else writeMessage("Данного блюда нет в меню.");
            } else break;
        }

        return dishes;

    }
}
