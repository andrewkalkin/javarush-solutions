package com.javarush.task.task07.task0706;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Улицы и дома
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код

        int[] street = new int[15];
        int even = 0, odd = 0;

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < street.length; i++) {
            street[i] = Integer.parseInt(reader.readLine());
        }

        for (int i =0; i < street.length; i++) {
            if (i % 2 == 0) even += street[i]; else  odd += street[i];
        }

        System.out.println(even > odd ? "В домах с четными номерами проживает больше жителей.":"В домах с нечетными номерами проживает больше жителей.");
    }
}
