package com.paduris.spring.cloud.userservice;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author paduris
 */
@RestController
@RequestMapping("/registration")
@RequiredArgsConstructor
public class UserRegistrationController {
    @NonNull
    private final UserRegistrationService userRegistrationService;

    @PostMapping
    public ResponseEntity<User> registerUser(@RequestBody User user, UriComponentsBuilder uriComponentsBuilder) {
        User registerUser = this.userRegistrationService.registerUser(user);
        UriComponents uriComponents = uriComponentsBuilder
                .path("users/{userId}")
                .buildAndExpand(user.getUserId());
        return ResponseEntity.created(uriComponents.toUri()).body(registerUser);
    }
}
