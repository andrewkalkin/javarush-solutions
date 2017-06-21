package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by kalinnikov_al on 30.05.2017.
 */
public class Solution {
    public static void main(String[] args){

        testStrategy(new HashMapStorageStrategy(), 10000);
        testStrategy(new OurHashMapStorageStrategy(), 10000);
        testStrategy(new FileStorageStrategy(), 100);
        testStrategy(new OurHashBiMapStorageStrategy(), 10000);
        testStrategy(new HashBiMapStorageStrategy(), 10000);
        testStrategy(new DualHashBidiMapStorageStrategy(), 10000);

    }

    public static Set<Long> getIds(Shortener shortener, Set<String> strings) {
        Set<Long> setLong = new HashSet<>();
        for (String str: strings) setLong.add(shortener.getId(str));
        return setLong;
    }

    public static Set<String> getStrings(Shortener shortener, Set<Long> keys) {
        Set<String> setString = new HashSet<>();
        for (Long number: keys) setString.add(shortener.getString(number));
        return setString;
    }

    public static void testStrategy(StorageStrategy strategy, long elementsNumber) {
        System.out.println(strategy.getClass().getSimpleName());
        Set<String> testSet = new HashSet<>();
        for (long i = 0; i < elementsNumber; i++) testSet.add(Helper.generateRandomString());
        Shortener shortener = new Shortener(strategy);
        long initalTime = new Date().getTime();
        Set<Long> testSetLong = getIds(shortener, testSet);
        long time = new Date().getTime() - initalTime;
        System.out.printf("Время затраченное на проверку функцией getIds: %s\n", time);
        initalTime = new Date().getTime();
        Set<String> resultSet = getStrings(shortener, testSetLong);
        time = new Date().getTime() - initalTime;
        System.out.printf("Время затраченное на проверку функцией getStrings: %s\n", time);
        System.out.println(resultSet.equals(testSet) ? "Тест пройден." : "Тест не пройден.");
    }
}
