package com.zeni.accountssale.presistance.entity.impl;

import com.zeni.accountssale.presistance.entity.Entity;
import com.zeni.accountssale.presistance.entity.Validation;
import java.util.StringJoiner;
import java.util.UUID;

public class Customer extends Entity {
    
    private String nickname;
    private String password;
    private String email;
    private String paymentMethod;
    
    private Customer(UUID id, String nickname, String password, String email,
            String paymentMethod) {
        super(id);
        this.nickname = nickname;
        this.password = password;
        this.email = email;
        this.paymentMethod = paymentMethod;
    }
    
    public Customer() {
        super(UUID.randomUUID());
    }
    
    public static CustomerBuilder builder() {
        return new CustomerBuilder();
    }
    
    public String getNickname() {
        return nickname;
    }
    
    public void setNickname(String nickname) {
        if (Validation.isValidNickname(nickname)) {
            this.nickname = nickname;
        } else {
            // Обробка невалідного нікнейму, наприклад, викидання помилки або інша обробка за потреби
            System.out.println("Неправильний нікнейм: " + nickname);
        }
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        if (Validation.isValidPassword(password)) {
            this.password = password;
        } else {
            // Обробка невалідного паролю, наприклад, викидання помилки або інша обробка за потреби
            System.out.println("Неправильний пароль: " + password);
        }
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        if (Validation.isValidEmail(email)) {
            this.email = email;
        } else {
            // Обробка невалідної електронної пошти, наприклад, викидання помилки або інша обробка за потреби
            System.out.println("Неправильна електронна пошта: " + email);
        }
    }
    
    public String getPaymentMethod() {
        return paymentMethod;
    }
    
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    
    @Override
    public String toString() {
        return new StringJoiner(", ", "", "")
                .add("id = " + getId())
                .add(nickname)
                .add(password)
                .add(email)
                .add(paymentMethod)
                .toString();
    }
    
    public static class CustomerBuilder {
        
        private UUID id;
        private String nickname;
        private String password;
        private String email;
        private String paymentMethod;
        
        public CustomerBuilder() {
            this.id = UUID.randomUUID();
        }
        
        public CustomerBuilder id(UUID id) {
            this.id = id;
            return this;
        }
        
        public CustomerBuilder nickname(String nickname) {
            if (Validation.isValidNickname(nickname)) {
                this.nickname = nickname;
            } else {
                System.out.println("Неправильний нікнейм: " + nickname);
            }
            return this;
        }
        
        public CustomerBuilder password(String password) {
            this.password = password;
            return this;
        }
        
        public CustomerBuilder email(String email) {
            if (Validation.isValidEmail(email)) {
                this.email = email;
            } else {
                System.out.println("Неправильна електронна пошта: " + email);
            }
            return this;
        }
        
        public CustomerBuilder paymentMethod(String paymentMethod) {
            this.paymentMethod = paymentMethod;
            return this;
        }
        
        public Customer build() {
            return new Customer(id, nickname, password, email, paymentMethod);
        }
        
        public String getNickname() {
            return nickname;
        }
        
        public String getPassword() {
            return password;
        }
        
        public String getEmail() {
            return email;
        }
    }
}
