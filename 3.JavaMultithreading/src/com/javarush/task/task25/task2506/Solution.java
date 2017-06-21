package com.javarush.task.task25.task2506;

/* 
Мониторинг состояния нити
*/
public class Solution {
    public static void main(String[] args) throws InterruptedException {
        Thread target = new Thread();
        LoggingStateThread loggingStateThread = new LoggingStateThread(target);

        loggingStateThread.start();
        target.start();  //NEW
        target.sleep(5000); //RUNNABLE
        target.join(15000);
        target.sleep(4000);
        target.interrupt(); //TERMINATED
        target.sleep(3000);
    }

}
