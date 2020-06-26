package com.paduris.spring.cloud.cardservice.verification;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
public class VerificationApplication {
    @Getter
    private String userId;
    @Getter
    private final BigDecimal cardLimitAmount;


}
