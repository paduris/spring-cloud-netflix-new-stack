package com.paduris.spring.cloud.fraudverifier.user;

import lombok.*;

/**
 * @author paduris
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
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
    enum Status {
        VERIFICATION_PASSED,
        VERIFICATION_FAILED
    }
}
