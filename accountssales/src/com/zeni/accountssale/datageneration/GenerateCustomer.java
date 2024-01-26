package com.zeni.accountssale.datageneration;

import com.github.javafaker.Faker;
import com.zeni.accountssale.presistance.entity.impl.Customer;
import com.zeni.accountssale.util.PaymentMethodGenerator;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class GenerateCustomer {
    
    public static Set<Customer> generateCustomers(int count) {
        Set<Customer> customers = new HashSet<>();
        Faker faker = new Faker();
        
        for (int i = 0; i < count; i++) {
            UUID customerId = UUID.randomUUID();
            String password = generateValidPassword();
            String email = faker.internet().emailAddress();
            String customername = faker.name().username();
            String payment = PaymentMethodGenerator.generatePaymentMethod();
            
            Customer customer = Customer.builder()
                    .id(customerId)
                    .nickname(customername)
                    .password(password)
                    .email(email)
                    .paymentMethod(payment)
                    .build();
            
            customers.add(customer);
        }
        
        return customers;
    }
    
    public static String generateValidPassword() {
        Faker faker = new Faker();
        String password;
        
        do {
            password = faker.internet().password(9, 31, true, true, true);
        } while (!isValidPassword(password));
        
        return password;
    }
    
    private static boolean isValidPassword(String password) {
        // Додайте свої власні критерії валідації для паролю
        return password.length() >= 8 &&
                password.length() <= 32 &&
                password.matches(".*[a-z].*") &&
                password.matches(".*[A-Z].*") &&
                password.matches(".*\\d.*") &&
                password.matches(".*[@#$%^&+=].*");
    }
}
