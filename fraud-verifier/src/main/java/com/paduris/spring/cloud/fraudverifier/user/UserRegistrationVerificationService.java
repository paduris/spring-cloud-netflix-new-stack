package com.paduris.spring.cloud.fraudverifier.user;

import org.springframework.stereotype.Service;

/**
 * @author paduris
 */
@Service
public class UserRegistrationVerificationService {

    /**
     * verify user
     * @param userId
     * @param userName
     * @return
     */
    public VerificationResult verifyUser(String userId, String userName) {
        if(userName != null || !userName.equals("")){
            return VerificationResult.passed(userId);
            //in real we call another service or persistence for verification
        }
        return VerificationResult.failed(userId);
    }
}
