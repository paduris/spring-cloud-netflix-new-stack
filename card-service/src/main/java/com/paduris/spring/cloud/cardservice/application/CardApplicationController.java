package com.paduris.spring.cloud.cardservice.application;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/application")
@RequiredArgsConstructor
public class CardApplicationController {

    @NonNull
    private final CardApplicationService cardApplicationService;

    @PostMapping
    public Mono<ApplicationResult> apply(@RequestBody CardApplicationDTO applicationDTO){
        return cardApplicationService.registerApplication(applicationDTO);
    }
}
