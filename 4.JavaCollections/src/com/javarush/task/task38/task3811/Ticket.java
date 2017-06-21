package com.javarush.task.task38.task3811;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by kalinnikov_al on 09.06.2017.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Ticket {
    enum Priority {
        LOW, MEDIUM, HIGH;
    }

    Priority priority() default Priority.MEDIUM;
    String[] tags() default {};
    String createdBy() default "Amigo";
}
