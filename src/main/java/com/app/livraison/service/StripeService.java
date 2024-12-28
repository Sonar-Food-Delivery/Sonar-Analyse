package com.app.livraison.service;

import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.stereotype.Service;

@Service
public class StripeService {
    public String createCheckoutSession(double totalAmountInDirhams) throws StripeException {
        // Convert the total amount to cents (1 Dirham = 100 cents)
        long totalAmountInCents = (long) (totalAmountInDirhams * 100);

        // Define session parameters
        SessionCreateParams params = SessionCreateParams.builder()
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .addLineItem(
                        SessionCreateParams.LineItem.builder()
                                .setPriceData(
                                        SessionCreateParams.LineItem.PriceData.builder()
                                                .setCurrency("mad") // Set currency to MAD (Moroccan Dirham)
                                                .setUnitAmount(totalAmountInCents) // Use the total amount in cents
                                                .setProductData(
                                                        SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                                .setName("Order Total")
                                                                .build()
                                                )
                                                .build()
                                )
                                .setQuantity(1L)
                                .build()
                )
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl("http://localhost:4200/success") // Replace with your actual success URL
                .setCancelUrl("http://localhost:4200/cancel")   // Replace with your actual cancel URL
                .build();

        // Create the session
        Session session = Session.create(params);

        // Return the session id to use in the frontend
        return session.getId();
    }

}