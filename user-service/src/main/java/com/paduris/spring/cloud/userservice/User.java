package com.paduris.spring.cloud.userservice;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author paduris
 */
@Data
@AllArgsConstructor
public class User {
    private String userId;
    private String userName;
    private String dateOfBirth;
    private Status status;
    public enum Status {
        NEW,
        OK,
        FRAUD
    }
}
