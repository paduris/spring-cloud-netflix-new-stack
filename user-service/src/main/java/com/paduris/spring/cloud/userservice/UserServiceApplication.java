package com.paduris.spring.cloud.userservice;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.micrometer.CircuitBreakerMetrics;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.java.Log;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;

import java.time.Duration;

@SpringBootApplication
@Log
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

    @Bean
    public RestTemplateBuilder restTemplateBuilder() {
        return new RestTemplateBuilder();
    }

    @Bean
    public Customizer<Resilience4JCircuitBreakerFactory> defaultCustomizer(CircuitBreakerRegistry circuitBreakerRegistry,
                                                                           MeterRegistry meterRegistry) {
        return factory -> {
            factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
                    .timeLimiterConfig(TimeLimiterConfig.custom()
                            .timeoutDuration(Duration.ofSeconds(2))
                            .build())
                    .circuitBreakerConfig(CircuitBreakerConfig.ofDefaults())
                    .build());
            factory.configureCircuitBreakerRegistry(circuitBreakerRegistry);
            factory.addCircuitBreakerCustomizer(circuitBreaker -> CircuitBreakerMetrics
                    .ofCircuitBreakerRegistry(circuitBreakerRegistry)
                    .bindTo(meterRegistry), "verifyNewUser");
        };
    }

    @Bean
    CircuitBreakerRegistry resilience4JCircuitBreakerRegistry() {
        return CircuitBreakerRegistry.ofDefaults();
    }
}
