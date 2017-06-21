package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

/**
 * Created by kalinnikov_al on 19.04.2017.
 */
public class DirectorTablet {
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);

    public void printAdvertisementProfit() {
        Double sum = 0d;
        for (Map.Entry<Date,Double> entry: StatisticManager.getInstance().getProfitperDay().entrySet()) {
            ConsoleHelper.writeMessage(String.format(Locale.ENGLISH, "%s - %.2f", sdf.format(entry.getKey()), entry.getValue()));
            sum += entry.getValue();
        }
        ConsoleHelper.writeMessage(String.format(Locale.ENGLISH, "Total - %.2f", sum));
    }

    public void printCookWorkloading(){
        for (Map.Entry<Date,Map<String,Integer>> entry: StatisticManager.getInstance().getCookWorkLoad().entrySet()) {
            ConsoleHelper.writeMessage(String.format(Locale.ENGLISH, sdf.format(entry.getKey())));
            for (Map.Entry<String,Integer> cook: entry.getValue().entrySet()) {
                ConsoleHelper.writeMessage(String.format("%s - %d min", cook.getKey(),cook.getValue()));
            }
            ConsoleHelper.writeMessage("");
        }

    }

    public void printActiveVideoSet(){

    }

    public void printArchivedVideoSet(){

    }

}
