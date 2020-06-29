package com.paduris.spring.cloud.cardservice.verification;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@Log
public class VerificationServiceClient {
    private final WebClient.Builder webClientBuilder;
    public VerificationServiceClient(@Qualifier("loadBalancedWebClient") WebClient.Builder webClientBuilder){
        this.webClientBuilder = webClientBuilder;
    }

    public Mono<VerificationResult> verify(VerificationApplication verificationApplication) {
        final Mono<VerificationResult> verificationResultMono = webClientBuilder.build().get()
                .uri(uriBuilder -> uriBuilder
                        .scheme("http")
                        .host("fraud-verifier")
                        .path("/cards/verify")
                        .queryParam("userId", verificationApplication.getUserId())
                        .queryParam("cardLimit", verificationApplication
                                .getCardLimitAmount())
                        .build())
                .retrieve()
                .bodyToMono(VerificationResult.class);
        //System.out.println(verificationResultMono.block().getStatus() + "  " + verificationResultMono.block().getUserId());
        verificationResultMono.subscribe(result -> {
            System.out.println(result.getStatus() + "---" + result.getUserId());
        });
        return verificationResultMono;
    }
}
