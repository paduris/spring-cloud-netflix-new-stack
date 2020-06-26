package com.paduris.spring.cloud.userservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author paduris
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VerificationResult {
    private String userId;
    private Status status;

    public enum Status {
        VERIFICATION_PASSED,
        VERIFICATION_FAILED
    }

    public static VerificationResult passed(String userId) {
        return new VerificationResult(userId, Status.VERIFICATION_PASSED);
    }

    public static VerificationResult failed(String userId) {
        return new VerificationResult(userId, Status.VERIFICATION_FAILED);
    }
}
