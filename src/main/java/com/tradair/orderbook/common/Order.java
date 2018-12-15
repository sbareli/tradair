package com.tradair.orderbook.common;

import lombok.Getter;

@Getter
public class Order {

    public enum OrderSide {Bid, Offer}

    public enum OrderType {Limit, Market}

    private Order() {}

    public Order(String venue, int id, long timeStamp, OrderSide side,
                 String symbol, int quantity, OrderType type, double price)
    {
        this.id = id;
        this.timeStamp = timeStamp;
        this.side = side;
        this.quantity = quantity;
        this.venue = venue;
        this.type = type;
        this.price = price;
        this.symbol = symbol;
    }

    public boolean
    isLimit() {
        return type == OrderType.Limit ? true : false ;
    }

    public boolean
    isBid() {
        return side == OrderSide.Bid ? true : false ;
    }

    public boolean
    isOffer() {
        return side == OrderSide.Offer ? true : false ;
    }

    private String symbol;
    private String venue;
    private double price;
    private int id;
    private long timeStamp;
    private OrderSide side;
    private int quantity;
    private OrderType type;
}

