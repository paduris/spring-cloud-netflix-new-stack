package com.paduris.spring.cloud.userservice;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author paduris
 */
@Service
@RequiredArgsConstructor
public class UserRegistrationService {
    @NonNull
    private final VerificationServiceClient verificationServiceClient;

    /**
     * Register User
     * @param user
     * @return
     */
    public User registerUser(User user) {
        this.verifyUser(user);
        return user;
    }

    /**
     * Verify User
     * @param user
     */
    private void verifyUser(User user) {
        VerificationResult verificationResult = this.verificationServiceClient.verifyUser(user.getUserId(), user.getUserName());
        user.setStatus(VerificationResult.Status.VERIFICATION_PASSED.equals(verificationResult.getStatus()) ?
                User.Status.OK : User.Status.FRAUD);
    }
}
