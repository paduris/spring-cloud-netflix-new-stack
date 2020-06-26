package com.paduris.spring.cloud.fraudverifier.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author paduris
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VerificationResult {

    private String userId;
    private String userName;

    public VerificationResult(String userId, Status verificationFailed) {
    }

    public static VerificationResult passed(String userId) {
        return new VerificationResult(userId, Status.VERIFICATION_PASSED);
    }

    public static VerificationResult failed(String userId) {
        return new VerificationResult(userId, Status.VERIFICATION_FAILED);
    }
    enum Status {
        VERIFICATION_PASSED,
        VERIFICATION_FAILED
    }
}
