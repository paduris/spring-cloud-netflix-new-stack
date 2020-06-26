package com.paduris.spring.cloud.cardservice.application;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class ApplicationResult {
    @Getter
    private Status status;
    public static ApplicationResult granted() {
        return new ApplicationResult(Status.GRANTED);
    }
    public static ApplicationResult rejected() {
        return new ApplicationResult(Status.REJECTED);
    }
    public enum Status {
        GRANTED,
        REJECTED
    }
}
