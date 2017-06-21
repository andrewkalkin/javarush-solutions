package com.javarush.task.task07.task0712;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Самые-самые
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код

        ArrayList<String> masString = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        for (int i=0; i < 10; i++) {
            masString.add(reader.readLine());
        }

        String minString=masString.get(0), maxString=masString.get(0);

        for (String str: masString) {
            if (str.length() < minString.length()) minString = str;
            if (str.length() > maxString.length()) maxString = str;
        }


        System.out.println(masString.indexOf(minString) < masString.indexOf(maxString) ? minString: maxString);

    }
}
