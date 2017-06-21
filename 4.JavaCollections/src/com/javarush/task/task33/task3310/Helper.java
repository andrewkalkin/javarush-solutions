package com.javarush.task.task33.task3310;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Created by kalinnikov_al on 30.05.2017.
 */
public class Helper {
    public static String generateRandomString() {
        return new BigInteger(130, new SecureRandom()).toString(32);
    }

    public static void printMessage(String message) {
        System.out.println(message);
    }
}
