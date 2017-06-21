package com.javarush.task.task30.task3012;

/* 
Получи заданное число
*/

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.createExpression(74);
    }

    public void createExpression(int number) {
        //напишите тут ваш код
        String simNumber = new String();

        int q, r, pow=0, num = number;

        while (number > 0) {
            q = number / 3;
            r = number % 3;
            pow++;

            switch (r) {
                case 0:
                    number = q;
                    break;
                case 1:
                    simNumber = simNumber + " + " + (int) Math.pow(3,pow-1);
                    number = q;
                    break;
                case 2:
                    simNumber = simNumber + " - " + (int) Math.pow(3,pow-1) ;
                    number = q + 1;
            }

        }


        System.out.println(num + " =" + simNumber);

    }
}