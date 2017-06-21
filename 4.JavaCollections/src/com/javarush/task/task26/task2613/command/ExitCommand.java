package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.ResourceBundle;

/**
 * Created by kalinnikov_al on 13.06.2017.
 */
class ExitCommand implements Command {

    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.class.getPackage().getName() + ".resources.exit_en");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("exit.question.y.n"));

        if (ConsoleHelper.readString().equals("y"))
            ConsoleHelper.writeMessage(res.getString("thank.message"));


    }
}
