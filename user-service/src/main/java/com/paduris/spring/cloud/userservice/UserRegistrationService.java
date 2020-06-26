package com.paduris.spring.cloud.userservice;

import org.springframework.stereotype.Service;

/**
 * @author paduris
 */
@Service
public class UserRegistrationService {
    private final VerificationServiceClient verificationServiceClient;

    public UserRegistrationService(VerificationServiceClient verificationServiceClient) {
        this.verificationServiceClient = verificationServiceClient;
    }

    public User registerUser(User user) {
        this.verifyUser(user);
        return user;
    }

    private void verifyUser(User user) {
        VerificationResult verificationResult = verificationServiceClient.verifyUser(user.getUserId(), user.getUserName());
        user.setStatus(VerificationResult.Status.VERIFICATION_PASSED.equals(verificationResult.getStatus()) ?
                User.Status.OK : User.Status.FRAUD);
    }
}
