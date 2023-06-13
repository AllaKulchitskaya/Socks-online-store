package com.example.termpaper_3.services.impl;

import com.example.termpaper_3.exception.IncorrectParametersException;
import com.example.termpaper_3.model.Color;
import com.example.termpaper_3.model.Size;
import com.example.termpaper_3.model.Sock;
import com.example.termpaper_3.model.SockUnit;
import com.example.termpaper_3.services.SocksService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class SocksServiceImpl implements SocksService {

    private final Map<Sock, Integer> socks = new HashMap<>();

    @Override
    public void registerSocksSupply(SockUnit sockUnit) {
        if (isNotValid(sockUnit)) {
            throw new IncorrectParametersException();
        }
        Sock sock = sockUnit.getSock();
        if (socks.containsKey(sock)) {
            socks.replace(sock, socks.get(sock) + sockUnit.getQuantity());
        } else {
            socks.put(sock, sockUnit.getQuantity());
        }
    }

    @Override
    public void registerSocksOutgo(SockUnit sockUnit) {
        Sock sock = sockUnit.getSock();
        if (!socks.containsKey(sock) || isNotValid(sockUnit)) {
            throw new IncorrectParametersException();
        }
        int available = socks.get(sock);
        int result = available - sockUnit.getQuantity();
        if (result < 0) {
            throw new IncorrectParametersException();
        }
        socks.replace(sock, result);
    }

    @Override
    public int getTotalSocksQuantity(String color, float size, int cottonMin, int cottonMax) {
        Color c = Color.parse(color);
        Size s = Size.parse(size);
        if (Objects.isNull(c) || Objects.isNull(s) || cottonMin >= cottonMax || cottonMin < 0 || cottonMax > 100) {
            throw new IncorrectParametersException();
        }
        for (Map.Entry<Sock, Integer> entry : socks.entrySet()) {
            Sock sock = entry.getKey();
            int available = entry.getValue();
            if (sock.getColor() == c && sock.getSize() == s
                    && sock.getCottonPart() >= cottonMin && sock.getCottonPart() <= cottonMax) {
                return available;
            }
        }
        return 0;
    }

    private boolean isNotValid (SockUnit sockUnit) {
        Sock sock = sockUnit.getSock();
        return sock.getCottonPart() < 0 || sock.getCottonPart() > 100 || sockUnit.getQuantity() <= 0;
    }
}
