package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.command.CommandExecutor;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.Locale;

/**
 * Created by kalinnikov_al on 13.06.2017.
 */
public class CashMachine {
    public static void main(String[] args){
        Locale.setDefault(Locale.ENGLISH);

        try {

            System.out.println("That's good! I'm in try block!!!");

            Operation op;

            CommandExecutor.execute(Operation.LOGIN);

            do {
                op = ConsoleHelper.askOperation();
                CommandExecutor.execute(op);

            } while (op != Operation.EXIT);
        } catch (InterruptOperationException e) {
            ConsoleHelper.printExitMessage();
        }

    }
}
