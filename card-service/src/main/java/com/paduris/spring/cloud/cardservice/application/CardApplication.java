package com.paduris.spring.cloud.cardservice.application;

import com.paduris.spring.cloud.cardservice.user.User;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

/**
 * @author paduris
 */
public class CardApplication {
    @Getter
    private final String userId;
    @Getter
    private final User user;
    @Getter
    private final BigDecimal cardLimit;
    @Getter
    @Setter
    private ApplicationResult applicationResult;

    public CardApplication(String userId, User user, BigDecimal cardLimit) {
        this.userId = userId;
        this.user = user;
        if (!User.Status.OK.equals(user.getStatus())) {
            applicationResult = ApplicationResult.rejected();
        }
        this.cardLimit = cardLimit;
    }
}
