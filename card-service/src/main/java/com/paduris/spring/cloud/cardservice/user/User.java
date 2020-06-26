package com.paduris.spring.cloud.cardservice.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private String userId;
    private Status status;
    public enum Status {
        NEW,
        OK,
        FRAUD
    }
}

