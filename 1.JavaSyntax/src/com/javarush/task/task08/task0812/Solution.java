package com.javarush.task.task08.task0812;

import java.io.*;
import java.util.ArrayList;

/* 
Cамая длинная последовательность
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        //напишите тут ваш код

        ArrayList<Integer> masInt = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        for (int i=0; i < 10; i++) {
            masInt.add(Integer.parseInt(reader.readLine()));
        }

        int count = 1, maxcount = 1;

        for (int i=1; i < 10; i++) {
            if (masInt.get(i) == masInt.get(i-1)) count++; else  {
                if (count > maxcount) maxcount = count;
                count=1;
            }
        }

        if (count > maxcount) maxcount = count;

        System.out.println(maxcount);



    }
}