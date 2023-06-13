package com.example.termpaper_3.controllers;

import com.example.termpaper_3.model.SockUnit;
import com.example.termpaper_3.services.SocksService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/socks")
@Tag(name = "Склад интернет-магазина носков", description = "Учёт товаров на складе")
public class SocksController {

    private final SocksService socksService;

    public SocksController(SocksService socksService) {
        this.socksService = socksService;
    }

    @PostMapping
    @Operation(summary = "Регистрация прихода носков на склад")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Удалось добавить приход"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Параметры запроса отсутствуют или имеют некорректный формат"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Произошла ошибка, не зависящая от вызывающей стороны"
            )
    })
    public ResponseEntity<Void> registerSocksSupply(@RequestBody SockUnit sockUnit) {
        socksService.registerSocksSupply(sockUnit);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Operation(summary = "Регистрация отпуска носков со склада")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Удалось произвести отпуск носков со склада"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Товара нет на складе в нужном количестве, или параметры запроса имеют некорректный формат"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Произошла ошибка, не зависящая от вызывающей стороны"
            )
    })
    public ResponseEntity<Void> registerSocksOutgo(@RequestBody SockUnit sockUnit) {
        socksService.registerSocksOutgo(sockUnit);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    @Operation(summary = "Запрос информации о товарах на складе")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Запрос выполнен"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Параметры запроса отсутствуют или имеют некорректный формат"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Произошла ошибка, не зависящая от вызывающей стороны"
            )
    })
    public ResponseEntity<Integer> getTotalSocksQuantity (@RequestParam String color, @RequestParam float size,
                                                          @RequestParam(required = false, defaultValue = "0") int cottonMin,
                                                          @RequestParam(required = false, defaultValue = "100") int cottonMax) {
        int available = socksService.getTotalSocksQuantity(color, size, cottonMin, cottonMax);
        return ResponseEntity.ok(available);
    }

    @DeleteMapping
    @Operation(summary = "Регистрация списания бракованных носков")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Запрос выполнен, товар списан со склада"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Параметры запроса отсутствуют или имеют некорректный формат"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Произошла ошибка, не зависящая от вызывающей стороны"
            )
    })
    public ResponseEntity<Void> writeOffWasteSocks(@RequestBody SockUnit sockUnit) {
        socksService.registerSocksOutgo(sockUnit);
        return ResponseEntity.ok().build();
    }
}
