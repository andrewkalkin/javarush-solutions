package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.Tablet;
import com.javarush.task.task27.task2712.ad.AdvertisementManager;
import com.javarush.task.task27.task2712.ad.NoVideoAvailableException;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;

import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;

/**
 * Created by kalinnikov_al on 18.04.2017.
 */
public class Cook extends Observable implements Observer {
    private String name;

    public Cook(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public void update(Observable o, Object arg) {
        StatisticManager.getInstance().register(new CookedOrderEventDataRow(((Tablet) o).toString(),this.name,((Order) arg).getTotalCookingTime() * 60,((Order) arg).getDishes()));
        ConsoleHelper.writeMessage("Start cooking - " + arg + ", cooking time " + ((Order) arg).getTotalCookingTime() + "min");
        setChanged();
        notifyObservers(arg);
    }
}

