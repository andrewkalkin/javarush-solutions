package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

/**
 * Created by kalinnikov_al on 13.06.2017.
 */
public class ConsoleHelper {

    private static ResourceBundle res = ResourceBundle.getBundle(CashMachine.class.getPackage().getName() + ".resources.common_en");

    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message){
        System.out.println(message);
    }

    public static void printExitMessage()
    {
        ConsoleHelper.writeMessage(res.getString("the.end"));
    }

    public static String readString() throws InterruptOperationException {
        try {
            String str = bis.readLine();
            if (str.equalsIgnoreCase(res.getString("operation.EXIT"))) throw new InterruptOperationException();
            return str;
        } catch (IOException e) {

        }

        return null;
    }

    public static String askCurrencyCode() throws InterruptOperationException {

        String curencyCode;

        while (true){
            writeMessage(res.getString("choose.currency.code"));
            curencyCode = readString();

            if (curencyCode.length() != 3) writeMessage(res.getString("invalid.data")); else break;
        }

        return curencyCode.toUpperCase();

    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {

        String valueAndAmount;
        String[] result;
        writeMessage(String.format(res.getString("choose.denomination.and.count.format"),currencyCode));
        while (true) {
            valueAndAmount = readString();
            if (valueAndAmount.matches("\\d+ \\d+")) {
                result = valueAndAmount.split(" ");
                if(result[0].equals("0") || result[1].equals("0")){
                    writeMessage(res.getString("invalid.data"));
                    continue;
                }
                return result;
            } else{
                writeMessage(res.getString("invalid.data"));
            }
        }

    }

    public static Operation askOperation() throws InterruptOperationException {
        while (true){
            try {
                writeMessage(res.getString("choose.operation"));
                writeMessage(res.getString("operation.INFO") + " - 1");
                writeMessage(res.getString("operation.DEPOSIT") + " - 2");
                writeMessage(res.getString("operation.WITHDRAW") + " - 3");
                writeMessage(res.getString("operation.EXIT") + " - 4");
                int opCode = Integer.parseInt(readString());
                return Operation.getAllowableOperationByOrdinal(opCode);
            } catch (IllegalArgumentException e) {
                writeMessage(res.getString("invalid.data"));
                continue;
            }
        }

    }
}
