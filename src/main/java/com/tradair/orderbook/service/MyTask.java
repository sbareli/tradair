package com.tradair.orderbook.service;

public class MyTask implements Runnable {

    public MyTask() {
        counter++;
    }

    @Override
    public void run() {
        System.out.println("Starting MyTask");
    }

    static private Integer counter = 0;
}
