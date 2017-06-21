package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.vo.Vacancy;

import java.util.List;

/**
 * Created by kalinnikov_al on 09.06.2017.
 */
public interface Strategy {
    List<Vacancy> getVacancies(String searchString);
}
