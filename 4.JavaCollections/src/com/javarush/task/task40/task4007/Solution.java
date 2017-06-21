package com.javarush.task.task40.task4007;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.regex.Pattern;

/* 
Работа с датами
*/

public class Solution {
    public static void main(String[] args) {
        printDate("21.4.2014 15:56:45");
        System.out.println();
        printDate("21.4.2014");
        System.out.println();
        printDate("17:33:40");
    }

    public static void printDate(String date) {
        //напишите тут ваш код
        boolean bDate = Pattern.compile("[0-3]?[0-9]\\.[0-1]?[0-9]\\.[0-9]{1,4}").matcher(date).find();
        boolean bTime = Pattern.compile("[0-2]?[0-9]:[0-5]?[0-9]:[0-5]?[0-9]").matcher(date).find();

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = null;

        if (bDate && bTime) sdf = new SimpleDateFormat("dd.MM.yy HH:mm:ss");
            else {
            if (bDate) sdf = new SimpleDateFormat("dd.MM.yy");
            if (bTime) sdf = new SimpleDateFormat("HH:mm:ss");
        }

        try {
            calendar.setTime(sdf.parse(date));
        } catch (ParseException e) {
            return;
        }

        if (bDate) {
            System.out.printf("День: %d\n", calendar.get(Calendar.DAY_OF_MONTH));
            System.out.printf("День недели: %d\n", ((calendar.get(Calendar.DAY_OF_WEEK) - 1) == 0 ? 7 : calendar.get(Calendar.DAY_OF_WEEK) - 1));
            System.out.printf("День месяца: %d\n", calendar.get(Calendar.DAY_OF_MONTH));
            System.out.printf("День года: %d\n", calendar.get(Calendar.DAY_OF_YEAR));
            System.out.printf("Неделя месяца: %d\n", calendar.get(Calendar.WEEK_OF_MONTH));
            System.out.printf("Неделя года: %d\n", calendar.get(Calendar.WEEK_OF_YEAR));
            System.out.printf("Месяц: %d\n", calendar.get(Calendar.MONTH) + 1);
            System.out.printf("Год: %d\n", calendar.get(Calendar.YEAR));
        }

        if (bTime) {
            System.out.printf("AM или PM: %s\n", calendar.get(Calendar.AM_PM) == 0 ? "AM" : "PM");
            System.out.printf("Часы: %d\n", calendar.get(Calendar.HOUR));
            System.out.printf("Часы дня: %d\n", calendar.get(Calendar.HOUR_OF_DAY));
            System.out.printf("Минуты: %d\n", calendar.get(Calendar.MINUTE));
            System.out.printf("Секунды: %d\n", calendar.get(Calendar.SECOND));
        }
    }
}
