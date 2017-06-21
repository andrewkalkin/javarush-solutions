package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;


import java.util.ResourceBundle;

/**
 * Created by kalinnikov_al on 13.06.2017.
 */
public class LoginCommand implements Command {


    private ResourceBundle validCreditCards = ResourceBundle.getBundle(CashMachine.class.getPackage().getName() + ".resources.verifiedCards");
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.class.getPackage().getName() + ".resources.login_en");


    @Override
    public void execute() throws InterruptOperationException {

        ConsoleHelper.writeMessage(res.getString("before"));

        String sNumber;
        String sPin;


        while (true){
            ConsoleHelper.writeMessage(res.getString("specify.data"));
            sNumber = ConsoleHelper.readString();
            sPin = ConsoleHelper.readString();

            if (sNumber.length() != 12 || sPin.length() != 4) {
                ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"),sNumber));
                ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
            }
            else if (validCreditCards.containsKey(sNumber)) {
                if (validCreditCards.getString(sNumber).equals(sPin))
                {
                    ConsoleHelper.writeMessage(String.format(res.getString("success.format"),sNumber));
                    break;
                }
            } else {
                ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"), sNumber));
                ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
            }

        }


    }
}
