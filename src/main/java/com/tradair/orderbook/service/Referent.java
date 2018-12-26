package com.tradair.orderbook.service;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

public class Referent<T> extends WeakReference<T>{

    public Referent(T referent, ReferenceQueue queue) {
        super(referent, queue);
        _queue = queue;
    }

    public static <T> Referent<T> build(T t)
    {
        ReferenceQueue referenceQueue = new ReferenceQueue();

        return new Referent<>(t, referenceQueue);


    }

    private ReferenceQueue _queue;

}
