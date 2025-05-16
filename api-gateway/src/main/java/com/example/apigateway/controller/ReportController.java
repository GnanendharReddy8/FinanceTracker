package com.example.apigateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/reports")
public class ReportController {

    private final WebClient.Builder webClientBuilder;

    @Autowired
    public ReportController(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    @GetMapping("/summary")
    public Mono<ResponseEntity<ByteArrayResource>> getReport(@RequestParam Long userId,
                                                             @RequestParam String from,
                                                             @RequestParam String to) {
        return webClientBuilder.build()
                .get()
                .uri("http://report-service:8083/reports/summary?userId=" + userId + "&from=" + from + "&to=" + to)
                .accept(MediaType.APPLICATION_OCTET_STREAM)
                .retrieve()
                .toEntity(byte[].class)
                .map(response -> {
                    ByteArrayResource resource = new ByteArrayResource(response.getBody());
                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
                    headers.setContentDisposition(ContentDisposition.attachment().filename("report.xlsx").build());
                    return new ResponseEntity<>(resource, headers, response.getStatusCode());
                });
    }
}
