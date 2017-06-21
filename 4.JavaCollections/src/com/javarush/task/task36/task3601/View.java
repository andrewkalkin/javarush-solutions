package com.javarush.task.task36.task3601;

import java.util.List;

/**
 * Created by kalinnikov_al on 23.05.2017.
 */
public class View {
    Controller controller = new Controller();

    public void fireEventShowData() {
        System.out.println(controller.onDataListShow());
    }


}
