package com.tradair.orderbook.common;

import lombok.Getter;

@Getter
public class OrderKey {

    public OrderKey(String symbol, String venue, Double price) {
        this.symbol = symbol;
        this.venue = venue;
        this.price = price;
    }

    @Override
    public String toString() {
        return symbol + " | " + venue + " | " + Double.toString(price);
    }

    protected OrderKey() {}

    private String symbol;
    private String venue;
    private double price;
}
