package com.tradair.orderbook.common;

import lombok.Getter;

@Getter
public class OrderKey {

    public OrderKey(String symbol, Double price, String venue) {
        this.symbol = symbol;
        this.price = price;
        this.venue = venue;
    }

    @Override
    public String toString() {
        return symbol + " | " + Double.toString(price) + " | " + venue;
    }

    private OrderKey() {}

    private String symbol;
    private double price;
    private String venue;

}
