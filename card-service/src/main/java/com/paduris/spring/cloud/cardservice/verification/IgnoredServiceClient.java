package com.paduris.spring.cloud.cardservice.verification;

import lombok.AllArgsConstructor;
import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class IgnoredServiceClient {

    private final ReactorLoadBalancerExchangeFilterFunction lbFunction;

    public Mono<String> callIgnoredService() {
        return WebClient
                .builder()
                .filter(lbFunction)
                .build()
                .get()
                .uri("http://ignored/test/allowed")
                .retrieve()
                .bodyToMono(String.class);
    }

}
