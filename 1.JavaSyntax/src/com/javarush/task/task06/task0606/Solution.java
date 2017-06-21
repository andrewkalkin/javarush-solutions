package com.javarush.task.task06.task0606;

import java.io.*;

/* 
Чётные и нечётные циферки
*/

public class Solution {

    public static int even;
    public static int odd;

    public static void main(String[] args) throws IOException {
        //напишите тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int num = Integer.parseInt(reader.readLine());
        int digit = num;

        while ( num != 0) {
            digit = num % 10;
            if (digit % 2 == 0) even++; else {
                odd++;
            }

            num = num / 10;

        }

        System.out.printf("Even: %d Odd: %d", even, odd);
    }
}
