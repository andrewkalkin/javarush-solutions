package com.javarush.task.task36.task3605;

import java.io.*;
import java.util.Set;
import java.util.TreeSet;

/* 
Использование TreeSet
*/
public class Solution {
    public static void main(String[] args) throws IOException {

        FileReader file = new FileReader(args[0]);
        Set<Character> set = new TreeSet();
        char code;
        int i= 0;


        while (file.ready()) {
            code = Character.toLowerCase((char) file.read());
            if (code <='z' && code >= 'a') set.add(code);
        }

        file.close();

        for (Character ch: set) {
            i++;
            System.out.print(ch);
            if (i == 5) break;
        }

    }
}
