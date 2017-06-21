package com.javarush.task.task33.task3308;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by kalinnikov_al on 04.05.2017.
 */
@XmlRootElement
@XmlType(name= "shop")
public class Shop {
    public Goods goods;
    public int count;
    public double profit;
    public String[] secretData;

    static class Goods {
        public List<String> names;
    }
}
