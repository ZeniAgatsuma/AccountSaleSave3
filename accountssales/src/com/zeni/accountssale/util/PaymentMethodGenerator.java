package com.zeni.accountssale.util;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class PaymentMethodGenerator {
    
    private static final Set<String> paymentMethods = new HashSet<>();
    
    static {
        paymentMethods.add("Credit Card");
        paymentMethods.add("Debit Card");
        paymentMethods.add("PayPal");
        paymentMethods.add("Bitcoin");
        paymentMethods.add("Apple Pay");
        paymentMethods.add("Google Pay");
    }
    
    public static String generatePaymentMethod() {
        Random random = new Random();
        int index = random.nextInt(paymentMethods.size());
        return paymentMethods.toArray(new String[0])[index];
    }
    
    public static void main(String[] args) {
        // Використовуйте теперішній Set paymentMethods
        for (int i = 0; i < 5; i++) {
            String paymentMethod = generatePaymentMethod();
            System.out.println(paymentMethod);
        }
    }
}
