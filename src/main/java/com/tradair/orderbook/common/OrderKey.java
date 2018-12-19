package com.tradair.orderbook.common;

import lombok.Getter;

@Getter
public class OrderKey implements Comparable<OrderKey> {

    public OrderKey(String symbol, Double price, String venue) {
        this.symbol = symbol;
        this.price = price;
        this.venue = venue;
    }

    @Override
    public int compareTo(OrderKey o) {
        int res = symbol.compareToIgnoreCase(o.symbol);
        if (res != 0) return res;

        res = (price < o.price) ? -1 : (price == o.price ? 0 : 1) ;
        if (res != 0) return res;

        res = venue.compareToIgnoreCase(o.venue);

        return res;
    }

    @Override
    public boolean equals(Object o) {
        boolean res = false;

        if (this == o) return true;

        if (o instanceof OrderKey) {
            OrderKey key = (OrderKey)o;
            res = symbol.equalsIgnoreCase(key.symbol) &&
                  price == key.price &&
                  venue.equalsIgnoreCase(key.venue);
        }

        return res;
    }

    @Override
    public int hashCode() {
        int hash = 7;

        return hash;
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
