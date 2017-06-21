package com.javarush.task.task35.task3505;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConvertableUtil {

    public static <Key> Map convert(List<? extends Convertable<Key>> list) {
        Map result = new HashMap<Key,Convertable<Key>>();
        for (Convertable<Key> element: list) result.put(element.getKey(), element);
        return result;
    }
}
