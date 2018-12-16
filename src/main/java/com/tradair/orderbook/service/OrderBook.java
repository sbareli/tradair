package com.tradair.orderbook.service;

import com.tradair.orderbook.common.Order;
import com.tradair.orderbook.common.OrderKey;

import java.util.Collection;
import java.util.Comparator;
import java.util.concurrent.ConcurrentSkipListMap;

/*
    Managed by the Book Manager per symbol with many venues
    Assuming that Order Id per Symbol and Venue is unique
 */
public class OrderBook {

    public OrderBook(Comparator<OrderKey> comparator) {
        rows = new ConcurrentSkipListMap(comparator);
    }

    public boolean
    addOrder(Order order)
    {
        OrderKey key = new OrderKey(order.getSymbol(), order.getPrice(), order.getVenue());

        Order exists = rows.putIfAbsent(key, order);

        return exists == null ? true : false ;
    }

    public boolean
    deleteOrder(Order order)
    {
        OrderKey key = new OrderKey(order.getSymbol(), order.getPrice(), order.getVenue());

        Order exists = rows.get(key);

        if (exists != null && exists.getId() == order.getId()) {
            exists = rows.remove(key);
        }

        return exists == null ? false : true ;
    }

    public boolean
    updateOrder(Order order)
    {
        OrderKey key = new OrderKey(order.getSymbol(), order.getPrice(), order.getVenue());

        Order exists = rows.get(key);

        if (exists != null && exists.getId() == order.getId()) {
            //TODO: verify that only quantity changed
            exists = rows.put(key, order);
        }

        return exists == null ? false : true ;
    }

    public double
    getBestPrice() {
        OrderKey key = rows.firstKey();
        return key.getPrice();
    }

    public Collection<Order>
    getValues() {
        return rows.values();
    }

    private ConcurrentSkipListMap<OrderKey, Order> rows;
}
