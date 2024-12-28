package com.app.livraison.controller;

import com.app.livraison.service.StripeService;
import com.stripe.exception.StripeException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    private final StripeService stripeService;

    public PaymentController(StripeService stripeService) {
        this.stripeService = stripeService;
    }

    @PostMapping("/create-checkout-session")
    public ResponseEntity<Map<String, String>> createCheckoutSession(@RequestBody Map<String, Object> request) throws StripeException {
        // Extract totalAmount from the request body
    	double totalAmount = ((Number) request.get("totalAmount")).doubleValue();

        // Call the StripeService to create the session with the totalAmount
        String sessionId = stripeService.createCheckoutSession(totalAmount);

        Map<String, String> response = new HashMap<>();
        response.put("sessionId", sessionId);
        return ResponseEntity.ok(response); // Return sessionId in JSON format
    }
}