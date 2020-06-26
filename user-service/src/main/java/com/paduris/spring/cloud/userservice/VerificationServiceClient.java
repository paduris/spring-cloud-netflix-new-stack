package com.paduris.spring.cloud.userservice;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

/**
 * @author paduris
 */
@Component
public class VerificationServiceClient {

    private final RestTemplate restTemplate;
    private final DiscoveryClient discoveryClient;
    private final CircuitBreakerFactory circuitBreakerFactory;
    private final Timer verifyNewUserTimer;

    /**
     * Parameterized Constructor
     *
     * @param restTemplateBuilder
     * @param discoveryClient
     * @param circuitBreakerFactory
     * @param meterRegistry
     */
    public VerificationServiceClient(RestTemplateBuilder restTemplateBuilder,
                                     DiscoveryClient discoveryClient,
                                     CircuitBreakerFactory circuitBreakerFactory,
                                     MeterRegistry meterRegistry) {
        this.restTemplate = restTemplateBuilder.build();
        this.discoveryClient = discoveryClient;
        this.circuitBreakerFactory = circuitBreakerFactory;
        this.verifyNewUserTimer = meterRegistry.timer("verifyNewUserManual");
    }

    /**
     * Verify User
     * @param userId
     * @param userName
     * @return
     */
    public VerificationResult verifyUser(final String userId, final String userName) {

        return verifyNewUserTimer.record(() -> {
            final List<ServiceInstance> instanceList = this.discoveryClient.getInstances("proxy");
            final ServiceInstance serviceInstance = instanceList
                    .stream()
                    .findAny()
                    .orElseThrow(() -> new IllegalStateException("No proxy instance available"));
            final UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder
                    .fromHttpUrl(serviceInstance.getUri().toString() + "/fraud-verifier/users/verify")
                    .queryParam("userId", userId)
                    .queryParam("userName", userName);
            return circuitBreakerFactory.create("verifyNewUser")
                    .run(() -> restTemplate.getForObject(uriComponentsBuilder.toUriString(),
                            VerificationResult.class), throwable -> this.userRejected(userId, userName));

        });
    }

    /**
     * User Rejected
     * @param userId
     * @param userName
     * @return
     */
    private VerificationResult userRejected(String userId, String userName) {
        return VerificationResult.failed(userId);
    }

}
