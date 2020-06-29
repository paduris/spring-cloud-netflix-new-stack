package com.paduris.spring.cloud.fraudverifier.card;

import com.paduris.spring.cloud.fraudverifier.user.VerificationResult;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/cards")
@AllArgsConstructor
public class CardApplicationVerficationController {

    private final CardApplicationVerficationService cardApplicationVerficationService;

    /**
     * verify card
     *
     * @param userId
     * @param cardLimit
     * @return
     */
    @GetMapping("/verify")
    public ResponseEntity<VerificationResult> verifyCard(@RequestParam String userId,
                                                         @RequestParam BigDecimal cardLimit) {
        final VerificationResult result = cardApplicationVerficationService.verifyCard(userId, cardLimit);
        return ResponseEntity.ok(result);
    }
}
