package com.tradair.orderbook.service;

import com.tradair.orderbook.common.Order;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;

public class Checker<T> implements Runnable
{
    @Override
    public void run() {

        System.out.println("Starting Checker<T>");

        while (true)
        {
            try {
                System.out.println("Waiting for queue...");

                Reference<? extends T> wf = queue.remove(1000);

                if (wf != null)
                {
                    T order = wf.get();

                    if (order == null)
                        System.out.println("Dequeued successfully but referent is null");
                    else
                        System.out.println("Dequeued successfully with valid referent");
                }
            }
            catch (InterruptedException e) {

            }
        }
    }

    public Checker(ReferenceQueue queue) {
        this.queue = queue;
    }

    private ReferenceQueue<T> queue;
}
