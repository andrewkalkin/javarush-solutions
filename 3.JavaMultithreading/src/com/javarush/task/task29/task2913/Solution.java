package com.javarush.task.task29.task2913;

import java.util.Random;
import java.util.stream.IntStream;

/* 
Замена рекурсии
*/

public class Solution {
    private static int numberA;
    private static int numberB;

    public static String getAllNumbersBetween(int a, int b) {
        StringBuilder result = new StringBuilder();
        IntStream.
                iterate(a, i -> (a <= b) ? (i + 1) : (i - 1)).
                limit((a <= b) ? (b - a + 1) : (a - b + 1)).
                forEach(i -> result.append(i).append(" "));
        return String.valueOf(result).trim();
    }

    public static void main(String[] args) {
        Random random = new Random();
        numberA = random.nextInt() % 1_000;
        numberB = random.nextInt() % 10_000;
        System.out.println(getAllNumbersBetween(numberA, numberB));
        System.out.println(getAllNumbersBetween(numberB, numberA));
    }
}