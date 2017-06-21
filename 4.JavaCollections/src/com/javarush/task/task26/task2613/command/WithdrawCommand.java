package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;
import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.*;

/**
 * Created by kalinnikov_al on 13.06.2017.
 */
class WithdrawCommand implements Command {

    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.class.getPackage().getName() + ".resources.withdraw_en");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        String strTemp;
        String code = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(code);
        int sum;

        while (true) {
            ConsoleHelper.writeMessage(res.getString("specify.amount"));
            strTemp = ConsoleHelper.readString();
            if (!strTemp.matches("\\d+")) {
                ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
                continue;
            }

            sum = Integer.parseInt(strTemp);

            if (!manipulator.isAmountAvailable(sum)) {
                ConsoleHelper.writeMessage(res.getString("not.enough.money"));
                continue;
            }

            try {
                Map<Integer, Integer> map = manipulator.withdrawAmount(sum);
                TreeSet<Integer> keys = new TreeSet<>(Comparator.reverseOrder());
                keys.addAll(map.keySet());


                for (Integer key : keys) {
                    ConsoleHelper.writeMessage(String.format("\t%d - %d", key, map.get(key)));
                }

                ConsoleHelper.writeMessage(String.format(res.getString("success.format"),sum ));
                break;
            } catch (NotEnoughMoneyException e) {
                ConsoleHelper.writeMessage(res.getString("exact.amount.not.available"));
                continue;
            }
        }


    }
}
