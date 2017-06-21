package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.Tablet;

import java.io.IOException;
import java.util.List;

/**
 * Created by kalinnikov_al on 18.04.2017.
 */
public class Order {
    protected List<Dish> dishes;
    private final Tablet tablet;

    public Order(Tablet tablet) throws IOException {

        this.tablet = tablet;
        dishes = ConsoleHelper.getAllDishesForOrder();
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    @Override
    public String toString() {
        if (!dishes.isEmpty()) {
            return String.format("Your order: %s of %s",dishes.toString(),tablet.toString());
        }
        return "";
    }

    public int getTotalCookingTime() {
        int sum=0;
        for (Dish dish: dishes) sum += dish.getDuration();
        return sum;
    }

    public boolean isEmpty(){
        return dishes.isEmpty();
    }

}
