package com.waldron.online_library.cohereai;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import reactor.core.publisher.Mono;

@Configuration
public class WebClientCongif {

    @Value("${cohere.url}")
    private String url;

    @Value("${cohere.api-key}")
    private String apiKey;

    @Bean
    public WebClient cohereWebClient() {
        return WebClient.builder()
                .baseUrl(url)
                .defaultHeader("Authorization", "Bearer " + apiKey)
                .defaultHeader("Content-Type", "application/json")
                .filter(logRequest())
                .clientConnector(new ReactorClientHttpConnector())
                .build();
    }

    private ExchangeFilterFunction logRequest() {
        return ExchangeFilterFunction.ofRequestProcessor(request -> {
            System.out.println("Request: " + request.method() + " " + request.url());
            return Mono.just(request);
        });
    }
}
