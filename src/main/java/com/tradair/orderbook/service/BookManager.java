package com.tradair.orderbook.service;


import com.tradair.orderbook.common.Order;
import com.tradair.orderbook.common.OrderKey;

import java.util.Comparator;

public class BookManager {

    public boolean
    accept(Order order)
    {
        boolean status = false;

        if (order.isLimit() && order.isBid())
            status = bidOrderBook.addOrder(order);
        else if (order.isLimit() && order.isOffer())
            status = askOrderBook.addOrder(order);

        return status;
    }

    public double
    getBestBid() {
        return bidOrderBook.getBestPrice();
    }

    public double
    getBestOffer() {
        return askOrderBook.getBestPrice();
    }

    // high to low
    private OrderBook bidOrderBook = new OrderBook(
            Comparator.comparing(OrderKey::getSymbol)
                    .thenComparing(OrderKey::getPrice).reversed()
                    .thenComparing(OrderKey::getVenue)
    );

    // low to high
    private OrderBook askOrderBook = new OrderBook(
            Comparator.comparing(OrderKey::getSymbol)
                    .thenComparing(OrderKey::getPrice)
                    .thenComparing(OrderKey::getVenue)
    );

}
