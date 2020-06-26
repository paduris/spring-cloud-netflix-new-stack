package com.paduris.spring.cloud.cardservice.verification;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class VerificationResult {

    @Getter
    private String userId;
    @Getter
    private Status status;

    public static VerificationResult passed(String userId) {
        return new VerificationResult(userId, Status.VERIFICATION_PASSED);
    }

    public static VerificationResult failed(String userId) {
        return new VerificationResult(userId, Status.VERIFICATION_FAILED);
    }

    public enum Status {
        VERIFICATION_PASSED,
        VERIFICATION_FAILED
    }
}
