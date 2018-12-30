package com.tradair.orderbook.service;

import com.tradair.orderbook.common.Order;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;

public class Checker<T> implements Runnable
{
    @Override
    public void run() {

        System.out.println("Starting Checker<T>");

        while (On)
        {
            try {
                System.out.println("Waiting for queue...");

                Reference<? extends T> wf = queue.poll();

                if (wf != null)
                {
                    T order = wf.get();

                    if (order == null)
                        System.out.println("Dequeued successfully but referent is null");
                    else
                        System.out.println("Dequeued successfully with valid referent");
                }

                synchronized (this) {
                    System.out.println("Starting wait 5 sec...");
                    wait(5000);

                    if (On == false) {
                        System.out.println("Exit Thread");
                        break;
                    }
                    else {
                        System.out.println("Done waiting");
                    }
                }

            }
            catch (InterruptedException e) {

            }
        }
    }

    synchronized public void stop() {
        On = false;
        notify();
    }

    public Checker(ReferenceQueue queue) {
        this.queue = queue;
        On = true;
    }

    private ReferenceQueue<T> queue;
    private boolean On;
}
