package com.tradair.orderbook.service;

import com.tradair.orderbook.common.Order;
import com.tradair.orderbook.common.OrderKey;

import java.util.Comparator;
import java.util.concurrent.ConcurrentSkipListMap;

public class OrderBook {

    public OrderBook(Comparator<OrderKey> comparator) {
        rows = new ConcurrentSkipListMap(comparator);
    }

    public boolean
    addOrder(Order order)
    {
        OrderKey key = new OrderKey(order.getSymbol(), order.getVenue(), order.getPrice());

        Order exists = rows.putIfAbsent(key, order);

        return exists == null ? true : false ;
    }

    public double
    getBestPrice() {
        OrderKey key = rows.firstKey();
        return key.getPrice();
    }

    private ConcurrentSkipListMap<OrderKey, Order> rows;
}
