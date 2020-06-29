package com.paduris.spring.cloud.fraudverifier.user;

import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

/**
 * @author paduris
 */
@Service
@Log
public class UserRegistrationVerificationService {

    /**
     * verify user
     * @param userId
     * @param userName
     * @return
     */
    public VerificationResult verifyUser(String userId, String userName) {
        log.severe("UserID " + userId + "   " + "UserName "+userName);
        if(userName != null && !userName.equals("")){
            return VerificationResult.passed(userId);
            //in real we call another service or persistence for verification
        }
        return VerificationResult.failed(userId);
    }
}
