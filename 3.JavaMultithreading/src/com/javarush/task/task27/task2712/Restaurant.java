package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Waiter;

/**
 * Created by kalinnikov_al on 18.04.2017.
 */
public class Restaurant {
    public static void main(String[] args) {
        Cook cook = new Cook("Amigo");
        Waiter waiter = new Waiter();
        Tablet tablet = new Tablet(5);
        tablet.addObserver(cook);
        cook.addObserver(waiter);
        tablet.createOrder();
        tablet.createOrder();
        tablet.createOrder();

        DirectorTablet directorTablet = new DirectorTablet();
        directorTablet.printAdvertisementProfit();
        directorTablet.printCookWorkloading();
        directorTablet.printActiveVideoSet();
        directorTablet.printArchivedVideoSet();

    }
}
