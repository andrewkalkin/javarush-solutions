package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.*;

/**
 * Created by kalinnikov_al on 13.06.2017.
 */
public class CurrencyManipulator {
    private String currencyCode;

    private Map<Integer, Integer> denominations = new TreeMap<>(Comparator.reverseOrder());

    public String getCurrencyCode() {
        return currencyCode;
    }

    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public void addAmount(int denomination, int count){
        if(denominations.containsKey(denomination))
        {
            denominations.put(denomination, denominations.get(denomination) + count);
        }
        else
        {
            denominations.put(denomination, count);
        }
    }

    public int getTotalAmount() {
        int sum = 0;
        for (Map.Entry entry: denominations.entrySet()) {
            sum += (Integer) entry.getKey()* (Integer) entry.getValue();
        }
        return sum;
    }

    public boolean hasMoney(){
        return !denominations.isEmpty();
    }

    public boolean isAmountAvailable(int expectedAmount) {
        return getTotalAmount() - expectedAmount >= 0;
    }

    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException {

        if (!hasMoney()) throw new NotEnoughMoneyException();

        Map<Integer, Integer> map = new HashMap<>();

        Set<Integer> keys = new TreeSet<>(Comparator.reverseOrder());
        keys.addAll(denominations.keySet());
        Map<Integer, Integer> copyDenoninations = new TreeMap<>(Comparator.reverseOrder());
        copyDenoninations.putAll(denominations);

        int intPart, modPart = expectedAmount;
        int val, save, testsum = 0;

        for (Integer key: keys) {
            intPart = modPart / key;
            modPart = modPart % key;
            if (intPart > 0) {
                val = copyDenoninations.get(key) - intPart >= 0 ? intPart: copyDenoninations.get(key);
                save = copyDenoninations.get(key) - intPart >= 0 ? copyDenoninations.get(key) - intPart: 0;
                if (val < intPart) modPart += (intPart - val) * key;

                if (save == 0) copyDenoninations.remove(key); else copyDenoninations.put(key, save);

                map.put(key, val);

                testsum += (val * key);
            }

            if (modPart == 0) break;
        }

        if (testsum != expectedAmount) throw new NotEnoughMoneyException();

        denominations = copyDenoninations;

        return map;
    }
}
