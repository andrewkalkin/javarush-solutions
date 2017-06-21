package com.javarush.task.task34.task3403;

/* 
Разложение на множители с помощью рекурсии
*/
public class Solution {
    public void recursion(int n) {
        boolean[] A = new boolean[n];

        for (int i = 0; i < A.length; i++) A[i] = true;

        for (int i = 2; 2*i <= n; i++) {
            if (A[i-1])
                for (int j = i*i; j <= n; j +=  i) {
                    A[j-1] = false;
                }
        }

        for (int i=1; i < n; i++) if (A[i]) {
            if (n % (i+1) == 0) {
                System.out.println(i+1);
                recursion(n / (i+1));
                break;
            }
        }
    }

}
