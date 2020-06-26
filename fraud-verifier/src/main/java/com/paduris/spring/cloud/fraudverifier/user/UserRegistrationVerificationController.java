package com.paduris.spring.cloud.fraudverifier.user;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author paduris
 */
@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserRegistrationVerificationController {

    private final UserRegistrationVerificationService userRegistrationVerificationService;

    @GetMapping("/verify")
    public ResponseEntity<VerificationResult> verifyUser(@RequestParam("userId") String userId,
                                                         @RequestParam("userName") String userName) {
        return ResponseEntity.ok(this.userRegistrationVerificationService.verifyUser(userId, userName));
    }
}
