package com.javarush.task.task08.task0818;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/* 
Только для богачей
*/

public class Solution {
    public static HashMap<String, Integer> createMap() throws IOException{
        //напишите тут ваш код
        HashMap<String,Integer> PersonCharge = new HashMap<>();

        PersonCharge.put("Петров", 1000);
        PersonCharge.put("Иванов", 300);
        PersonCharge.put("Сидоров", 400);
        PersonCharge.put("Смекалкин", 600);
        PersonCharge.put("Дурнов", 700);
        PersonCharge.put("Еремин", 100);
        PersonCharge.put("Воеводин", 300);
        PersonCharge.put("Тарануха", 800);
        PersonCharge.put("Вафельник", 700);
        PersonCharge.put("Прохоров", 400);


        return PersonCharge;

    }

    public static void removeItemFromMap(HashMap<String, Integer> map) {
        //напишите тут ваш код

        HashMap<String, Integer> copy = new HashMap<>(map);

        for (Map.Entry<String, Integer> entry: copy.entrySet()) {
            if (entry.getValue() < 500) map.remove(entry.getKey());
        }

    }

    public static void main(String[] args) {

    }
}