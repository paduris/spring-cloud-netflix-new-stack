package com.paduris.spring.cloud.fraudverifier.card;

import com.paduris.spring.cloud.fraudverifier.user.VerificationResult;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author paduris
 */
@Service
public class CardApplicationVerficationService {

    private static final BigDecimal CARD_LIMIT = BigDecimal.valueOf(5000L);

    public VerificationResult verifyCard(String userId, BigDecimal cardLimit) {
        if (cardLimit.compareTo(CARD_LIMIT) > 0) {
            return VerificationResult.passed(userId);
        }
        return VerificationResult.failed(userId);
    }
}
