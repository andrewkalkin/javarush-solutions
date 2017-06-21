package com.javarush.task.task30.task3010;

/* 
Минимальное допустимое основание системы счисления
*/

public class Solution {
    public static void main(String[] args) {
        //напишите тут ваш код

        try {
            String number = args[0].toUpperCase();
            if (number.matches("^[0-9A-Z]+$")) {
                int codePoint = 49;
                for (int i = 0; i < number.length(); i++) {
                    if (number.charAt(i) > codePoint) codePoint = number.charAt(i);
                }
                if (codePoint > 64 & codePoint < 91) System.out.println(codePoint - 54);
                else System.out.println((char) (codePoint + 1));

            } else System.out.println("incorrect");
        } catch (Exception e) {

        }


    }
}