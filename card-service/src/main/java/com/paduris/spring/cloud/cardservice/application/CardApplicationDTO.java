package com.paduris.spring.cloud.cardservice.application;

import com.paduris.spring.cloud.cardservice.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@NoArgsConstructor
public class CardApplicationDTO {
    public User user;
    public BigDecimal cardCapacity;
}
