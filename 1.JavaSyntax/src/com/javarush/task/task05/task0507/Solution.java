package com.javarush.task.task05.task0507;

/* 
Среднее арифметическое
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        double num, sum=0;
        int count=0;
         while ((num = Integer.parseInt(reader.readLine())) != -1 ) {
             sum += num;
             count++;
         }

        double sr = sum/count;

        System.out.println(sr);
    }
}

