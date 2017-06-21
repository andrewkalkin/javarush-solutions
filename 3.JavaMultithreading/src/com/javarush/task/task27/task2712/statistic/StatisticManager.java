package com.javarush.task.task27.task2712.statistic;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventType;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.util.*;

/**
 * Created by kalinnikov_al on 19.04.2017.
 */
public class StatisticManager {
    private final static StatisticManager ourInstance = new StatisticManager();
    private StatisticStorage statisticStorage = new StatisticStorage();
    private Set<Cook> cooks = new HashSet<>();

    private StatisticManager(){

    }

    public static StatisticManager getInstance(){
        return ourInstance;
    }

    public void register(EventDataRow data){
        statisticStorage.put(data);
    }
    public void register(Cook cook) {
        cooks.add(cook);
    }

    private class StatisticStorage {

        Map<EventType, List<EventDataRow>> storage;

        private StatisticStorage(){

            storage = new HashMap<>();
            for (EventType eType: EventType.values()) {

                storage.put(eType, new ArrayList<EventDataRow>());

            }

        }

        private void put(EventDataRow data) {
            storage.get(data.getType()).add(data);
        }

        private Map<EventType, List<EventDataRow>> getStorage() {
            return storage;
        }
    }


    public Map<Date,Double> getProfitperDay(){
        Map<Date, Double> result = new TreeMap<>(Collections.reverseOrder());

        VideoSelectedEventDataRow curEvent;
        Date date;

        for (EventDataRow dataEvent: statisticStorage.getStorage().get(EventType.SELECTED_VIDEOS)) {
            curEvent = (VideoSelectedEventDataRow) dataEvent;
            date = clearTime(curEvent.getDate());

            if (result.containsKey(date)) result.put(date, result.get(date) + 0.01d * curEvent.getAmount());
                else result.put(date, 0.01d * curEvent.getAmount());

        }

        return result;
    }

    public Map<Date,Map<String,Integer>> getCookWorkLoad() {
        Map<Date, Map<String,Integer>> result = new TreeMap<>(Collections.reverseOrder());

        CookedOrderEventDataRow curEvent;
        Date date;

        for (EventDataRow dataEvent: statisticStorage.getStorage().get(EventType.COOKED_ORDER)) {
            curEvent = (CookedOrderEventDataRow) dataEvent;
            date = clearTime(curEvent.getDate());

            if (curEvent.getTime() > 0) {
                if (result.containsKey(date)) {
                    if (curEvent.getCookName() == null) System.out.println("NULL");
                    if (result.get(date).containsKey(curEvent.getCookName()))
                        result.get(date).put(curEvent.getCookName(), result.get(date).get(curEvent.getCookName()) + (int) Math.ceil(curEvent.getTime()/60));
                    else result.get(date).put(curEvent.getCookName(), (int) Math.ceil(curEvent.getTime()/60));
                } else {
                    Map<String, Integer> cooks = new TreeMap<>();
                    cooks.put(curEvent.getCookName(), (int) Math.ceil(curEvent.getTime()/60));
                    result.put(date,cooks);
                }
            }
        }

        return result;

    }

    private Date clearTime(Date date){
        GregorianCalendar curDate = new GregorianCalendar();
        GregorianCalendar newDate = new GregorianCalendar();
        newDate.clear();
        newDate.set(Calendar.YEAR, curDate.get(Calendar.YEAR));
        newDate.set(Calendar.MONTH, curDate.get(Calendar.MONTH));
        newDate.set(Calendar.DAY_OF_MONTH, curDate.get(Calendar.DAY_OF_MONTH));
        return newDate.getTime();
    }
}
