package com.javarush.task.task37.task3714;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

/* 
Древний Рим
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Input a roman number to be converted to decimal: ");
        String romanString = bufferedReader.readLine();
        System.out.println("Conversion result equals " + romanToInteger(romanString));
    }

    public static int romanToInteger(String s) {
        String[] Rome = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
        int[] Arab = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };

        StringBuffer romeNumber = new StringBuffer(s);
        int arabNumber = 0, i = 0;

        if (romeNumber.length() > 0)
            while (true) {
                do {
                    if (Rome[i].length() <= romeNumber.length()) {
                        if (romeNumber.toString().startsWith(Rome[i])){
                            arabNumber+=Arab[i];
                            romeNumber.delete(0, Rome[i].length());
                            if (romeNumber.length() == 0) return arabNumber;
                        } else break;
                    } else break;
                } while (romeNumber.length() != 0);
                i++;
            }


        return 0;
    }
}
