package com.tradair.orderbook.service;

import java.util.concurrent.ConcurrentHashMap;

public class AssetManager {

    public AssetManager()
    {
        assets = new ConcurrentHashMap<>();
    }

    public BookManager
    getBookManager(String symbol)
    {
        BookManager exists = assets.get(symbol);

        if (exists == null) {
            exists = new BookManager(symbol);
            assets.putIfAbsent(symbol, exists);
        }

        return exists;
    }

    private ConcurrentHashMap<String, BookManager> assets;
}
