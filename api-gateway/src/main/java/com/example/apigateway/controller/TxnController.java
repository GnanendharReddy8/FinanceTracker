package com.example.apigateway.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/txns")
@RequiredArgsConstructor
public class TxnController {

    private final WebClient.Builder webClientBuilder;

    @GetMapping("/get-tr")
    public Mono<String> getTxn(
            @RequestParam Long userId,
            @RequestParam String from,
            @RequestParam String to) {

        return webClientBuilder.build()
                .get()
                .uri(uriBuilder -> uriBuilder
                    .scheme("http")
                    .host("txn-service")
                    .port(8081)
                    .path("/txns/get-tr")
                    .queryParam("userId", userId)
                    .queryParam("from", from)
                    .queryParam("to", to)
                    .build())
                .retrieve()
                .bodyToMono(String.class);
    }
}
