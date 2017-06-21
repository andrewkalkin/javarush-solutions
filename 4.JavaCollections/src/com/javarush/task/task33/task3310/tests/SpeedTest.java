package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Helper;
import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.HashBiMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.HashMapStorageStrategy;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by kalinnikov_al on 05.06.2017.
 */
public class SpeedTest {
    public long getTimeForGettingIds(Shortener shortener, Set<String> strings, Set<Long> ids){
        long start = new Date().getTime();
        for (String string: strings) ids.add(shortener.getId(string));
        return new Date().getTime() - start;
    }

    public long getTimeForGettingStrings(Shortener shortener, Set<Long> ids, Set<String> strings){
        long start = new Date().getTime();
        for (Long id: ids) strings.add(shortener.getString(id));
        return new Date().getTime() - start;
    }

    @Test
    public void testHashMapStorage(){
        Shortener shortener1 = new Shortener(new HashMapStorageStrategy());
        Shortener shortener2 = new Shortener(new HashBiMapStorageStrategy());

        Set<String> origStrings = new HashSet<>();
        Set<Long> ids = new HashSet<>();

        for (int i = 0; i < 10000; i++) origStrings.add(Helper.generateRandomString());

        long time1 = getTimeForGettingIds(shortener1, origStrings, ids);
        long time2 = getTimeForGettingIds(shortener2, origStrings, ids);

        Assert.assertTrue(time1 > time2);

        Set<String> strings1 = new HashSet<>();
        Set<String> strings2 = new HashSet<>();

        time1 = getTimeForGettingStrings(shortener1, ids, strings1);
        time2 = getTimeForGettingStrings(shortener2, ids, strings2);

        Assert.assertEquals(time1, time2, 30);

    }
}
