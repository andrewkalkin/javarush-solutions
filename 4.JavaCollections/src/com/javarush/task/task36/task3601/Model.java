package com.javarush.task.task36.task3601;

import java.util.List;

/**
 * Created by kalinnikov_al on 23.05.2017.
 */
public class Model {
    Service service = new Service();
    public List<String> getStringDataList() {
        return service.getData();
    }
}
