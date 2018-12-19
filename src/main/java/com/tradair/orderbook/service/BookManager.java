package com.tradair.orderbook.service;


import com.tradair.orderbook.common.Order;
import com.tradair.orderbook.common.OrderKey;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class BookManager {

    public BookManager(String symbol) {
        this.symbol = symbol;
    }

    public String
    whoAmI() {
        return symbol;
    }

    public boolean
    newOrder(Order order)
    {
        //TODO: assert this symbol belongs to this book

        boolean status = false;

        if (order.isLimit() && order.isBid())
            status = bidOrderBook.addOrder(order);
        else if (order.isLimit() && order.isOffer())
            status = askOrderBook.addOrder(order);

        return status;
    }

    public boolean
    deleteOrder(Order order)
    {
        boolean status = false;

        if (order.isBid())
            status = bidOrderBook.deleteOrder(order);
        else if (order.isOffer())
            status = askOrderBook.deleteOrder(order);

        return status;
    }

    public boolean
    updateOrder(Order order)
    {
        boolean status = false;

        if (order.isBid())
            status = bidOrderBook.updateOrder(order);
        else if (order.isOffer())
            status = askOrderBook.updateOrder(order);

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

    public List<Order>
    getBidsDepth(double price, String venue)
    {
        OrderKey fromKey = new OrderKey(whoAmI(), price, venue);

        return bidOrderBook.getDepth(fromKey);
    }

    public List<Order>
    getOfferDepth(double price, String venue)
    {
        OrderKey fromKey = new OrderKey(whoAmI(), price, venue);

        return askOrderBook.getDepth(fromKey);
    }


    public Collection<Order>
    getBidValues() {
        return bidOrderBook.getValues();
    }

    public Collection<Order>
    getOfferValues() {
        return askOrderBook.getValues();
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

    private BookManager() {}
    private String symbol;
}
