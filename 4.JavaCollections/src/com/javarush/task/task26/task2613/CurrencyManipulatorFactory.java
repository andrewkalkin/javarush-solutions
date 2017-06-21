package com.javarush.task.task26.task2613;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kalinnikov_al on 13.06.2017.
 */
public class CurrencyManipulatorFactory {
    private static Map<String, CurrencyManipulator> map = new HashMap<>();

    public static CurrencyManipulator getManipulatorByCurrencyCode(String currencyCode){
        String curCode = currencyCode.toUpperCase();
        if (!map.containsKey(curCode)) map.put(curCode,new CurrencyManipulator(curCode));
        return map.get(curCode);
    }

    private CurrencyManipulatorFactory() {
    }

    public static Collection<CurrencyManipulator> getAllCurrencyManipulators(){
        return map.values();
    }
}
