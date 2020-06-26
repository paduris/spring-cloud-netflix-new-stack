package com.paduris.spring.cloud.userservice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author paduris
 */
@RestController
@RequestMapping("/registration")
public class UserRegistrationController {

    private final UserRegistrationService userRegistrationService;

    public UserRegistrationController(UserRegistrationService userRegistrationService) {
        this.userRegistrationService = userRegistrationService;
    }

    @PostMapping
    public ResponseEntity<User> registerUser(User user, UriComponentsBuilder uriComponentsBuilder) {
        User registerUser = userRegistrationService.registerUser(user);
        UriComponents uriComponents = uriComponentsBuilder.path("users/{userId}").buildAndExpand(user.getUserId());
        return ResponseEntity.created(uriComponents.toUri()).body(registerUser);

    }
}
