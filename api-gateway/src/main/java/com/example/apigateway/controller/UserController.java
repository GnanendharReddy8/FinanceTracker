package com.example.apigateway.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final WebClient.Builder webClientBuilder;

    @GetMapping("/{id}")
    public Mono<String> getUser(@PathVariable Long id) {
        return webClientBuilder.build()
                .get()
                .uri("http://user-service:8082/users/" + id)
                .retrieve()
                .bodyToMono(String.class);
    }
}
