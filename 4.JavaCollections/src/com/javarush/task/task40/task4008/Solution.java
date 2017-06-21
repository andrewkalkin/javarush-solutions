package com.javarush.task.task40.task4008;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.WeekFields;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* 
Работа с Java 8 DateTime API
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

        Matcher mDate = Pattern.compile("[0-3]?[0-9]\\.[0-1]?[0-9]\\.[0-9]{1,4}").matcher(date);
        Matcher mTime = Pattern.compile("[0-2]?[0-9]:[0-5]?[0-9]:[0-5]?[0-9]").matcher(date);

        boolean bDate = mDate.find();
        boolean bTime = mTime.find();

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d.M.yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("H:m:s");

        LocalDate localDate;
        LocalTime localTime;


        if (bDate) {
            localDate = LocalDate.parse(date.substring(mDate.start(), mDate.end()), dateFormatter);
            System.out.printf("День: %d\n", localDate.getDayOfMonth());
            System.out.printf("День недели: %d\n", localDate.getDayOfWeek().getValue());
            System.out.printf("День месяца: %d\n", localDate.getDayOfMonth());
            System.out.printf("День года: %d\n", localDate.getDayOfYear());
            System.out.printf("Неделя месяца: %d\n", localDate.get(WeekFields.of(Locale.getDefault()).weekOfMonth()));
            System.out.printf("Неделя года: %d\n", localDate.get(WeekFields.of(Locale.getDefault()).weekOfYear()));
            System.out.printf("Месяц: %d\n", localDate.getMonthValue());
            System.out.printf("Год: %d\n", localDate.getYear());
        }

        if (bTime) {
            localTime = LocalTime.parse(date.substring(mTime.start(), mTime.end()), timeFormatter);
            System.out.printf("AM или PM: %s\n", localTime.get(ChronoField.AMPM_OF_DAY) == 0 ? "AM" : "PM");
            System.out.printf("Часы: %d\n", localTime.get(ChronoField.HOUR_OF_AMPM));
            System.out.printf("Часы дня: %d\n", localTime.get(ChronoField.HOUR_OF_DAY));
            System.out.printf("Минуты: %d\n", localTime.get(ChronoField.MINUTE_OF_HOUR));
            System.out.printf("Секунды: %d\n", localTime.get(ChronoField.SECOND_OF_MINUTE));
        }

    }
}
