package com.paduris.spring.cloud.cardservice.user;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author paduris
 */
@Component
public class UserServiceClient {

    private final WebClient.Builder webClientBuilder;
    private final DiscoveryClient discoveryClient;

    public UserServiceClient(@Qualifier("webClient") WebClient.Builder webClientBuilder,
                             DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
        this.webClientBuilder = webClientBuilder;
    }

    /**
     * Register User
     *
     * @param user
     * @return
     */
    public Mono<User> registerUser(User user) {
        List<ServiceInstance> instances = discoveryClient.getInstances("proxy");
        ServiceInstance serviceInstance = instances
                .stream()
                .findAny()
                .orElseThrow(() -> new IllegalStateException("No Proxies found"));

        return webClientBuilder
                .build()
                .post()
                .uri(serviceInstance.getUri().toString() + "/user-service/registration")
                .bodyValue(user)
                .retrieve()
                .bodyToMono(User.class);

    }
}
