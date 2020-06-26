package com.paduris.spring.cloud.cardservice.application;

import com.paduris.spring.cloud.cardservice.user.User;
import com.paduris.spring.cloud.cardservice.user.UserServiceClient;
import com.paduris.spring.cloud.cardservice.verification.IgnoredServiceClient;
import com.paduris.spring.cloud.cardservice.verification.VerificationApplication;
import com.paduris.spring.cloud.cardservice.verification.VerificationResult;
import com.paduris.spring.cloud.cardservice.verification.VerificationServiceClient;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@Log
@RequiredArgsConstructor
public class CardApplicationService {

    @NonNull
    private final UserServiceClient userServiceClient;
    @NonNull
    private final VerificationServiceClient verificationServiceClient;
    @NonNull
    private final IgnoredServiceClient ignoredServiceClient;

    public Mono<ApplicationResult> registerApplication(CardApplicationDTO applicationDTO) {
        return userServiceClient.registerUser(applicationDTO.user)
                .map(user -> new CardApplication(UUID.randomUUID().toString(), user, applicationDTO.cardCapacity))
                .flatMap(this::verifyApplication);

    }

    public Mono<ApplicationResult> verifyApplication(CardApplication cardApplication) {
        return ignoredServiceClient
                .callIgnoredService()
                .doOnNext(log::info)
                .then(verificationServiceClient
                        .verify(new VerificationApplication(cardApplication.getUserId(), cardApplication.getCardLimit()))
                        .map(verificationResult -> this.updateApplication(verificationResult, cardApplication)));
    }

    private ApplicationResult updateApplication(VerificationResult verificationResult, CardApplication cardApplication) {
        if (!VerificationResult.Status.VERIFICATION_PASSED
                .equals(verificationResult.getStatus())
                || !User.Status.OK.equals(cardApplication.getUser().getStatus())) {
            cardApplication.setApplicationResult(ApplicationResult.rejected());
        } else {
            cardApplication.setApplicationResult(ApplicationResult.granted());
        }
        return cardApplication.getApplicationResult();
    }
}
