package com.paduris.spring.cloud.cardservice.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String userId;
    private String userName;
    private Status status;
    public enum Status {
        NEW,
        OK,
        FRAUD
    }
}

