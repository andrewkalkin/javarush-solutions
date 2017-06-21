package com.javarush.task.task28.task2812;



import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.lang.String;

/* 
ShutdownNow!
*/

public class Solution {
    public static void main(java.lang.String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        for (int i = 1; i <= 10; i++) {
            final int localId = i;
            executor.submit(new Runnable() {
                public void run() {
                    doExpensiveOperation(localId);
                }
            });
        }

        List<Runnable> list = executor.shutdownNow();


        for (Runnable curRun: list) System.out.println(String.format("%s was not completed",curRun));
    }

    private static void doExpensiveOperation(int localId) {
        System.out.println(Thread.currentThread().getName() + ", localId="+localId);
    }
}
