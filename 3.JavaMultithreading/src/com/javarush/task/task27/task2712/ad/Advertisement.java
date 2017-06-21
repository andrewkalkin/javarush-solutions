package com.javarush.task.task27.task2712.ad;


/**
 * Created by kalinnikov_al on 18.04.2017.
 */
public class Advertisement {
    private Object content;
    private String name;
    private long initialAmount;
    private int hits;
    private int duration;
    private long amountPerOneDisplaying;
    private long balance;

    public Advertisement(Object content, String name, long initialAmount, int hits, int duration) {
        this.content = content;
        this.name = name;
        this.initialAmount = initialAmount;
        this.hits = hits;
        this.duration = duration;
        this.balance = initialAmount;
        if ( initialAmount % hits > 0) this.amountPerOneDisplaying = initialAmount / hits + 1; else this.amountPerOneDisplaying = initialAmount / hits;
    }

    public int getHits() {
        return hits;
    }

    public int getDuration() {
        return duration;
    }

    public long getAmountPerOneDisplaying() {
        return amountPerOneDisplaying;
    }

    public String getName() {
        return name;
    }

    public void revalidate(){

        if (hits <=0)
            throw new UnsupportedOperationException();
        hits--;
        if (hits > 0) {
            balance -= amountPerOneDisplaying;
            if ( balance % hits > 0) this.amountPerOneDisplaying = balance / hits + 1; else this.amountPerOneDisplaying = balance / hits;
        }


    }

}
