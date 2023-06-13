package com.example.termpaper_3.services;

import com.example.termpaper_3.model.SockUnit;

public interface SocksService {
    void registerSocksSupply(SockUnit sockUnit);

    void registerSocksOutgo(SockUnit sockUnit);

    int getTotalSocksQuantity(String color, float size, int cottonMin, int cottonMax);
}
