package com.zeni.accountssale.util;

import static com.zeni.accountssale.presistance.JsonDataManager.writeObjectsToJsonFile;
import static com.zeni.accountssale.util.EmailVereficator.authenticateUser;

import com.zeni.accountssale.presistance.entity.Validation;
import com.zeni.accountssale.presistance.entity.impl.Customer;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import org.mindrot.bcrypt.BCrypt;

public class Registration {
    
    private static void saveUserToJsonFile(Customer customer) {
        Set<Customer> customers = new HashSet<>();
        customers.add(customer);
        writeObjectsToJsonFile(customers, "Customer.json", Customer.class);
    }
    
    public static Customer registerUser() {
        Scanner scanner = new Scanner(System.in);
        Customer.CustomerBuilder customerBuilder = Customer.builder();
        
        enterNickname(customerBuilder, scanner);
        enterPassword(customerBuilder, scanner);
        
        Customer customer = customerBuilder.build();
        if (authenticateUser(enterEmail(customerBuilder, scanner))) {
            
            saveUserToJsonFile(customer);
            return customer;
        }
        return customer;
    }
    
    private static void enterNickname(Customer.CustomerBuilder builder, Scanner scanner) {
        boolean isValid = false;
        while (!isValid) {
            System.out.print("Нікнейм: ");
            String nickname = scanner.next().trim();
            if (Validation.isValidNickname(nickname)) {
                builder.nickname(nickname);
                isValid = true;
            } else {
                System.out.println("Неправильний нікнейм. Введіть дані користувача ще раз.");
            }
        }
        scanner.nextLine();
    }
    
    private static void enterPassword(Customer.CustomerBuilder builder, Scanner scanner) {
        boolean isValid = false;
        while (!isValid) {
            System.out.print("Пароль: ");
            String password = scanner.nextLine().trim();
            if (Validation.isValidPassword(password)) {
                String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
                builder.password(hashedPassword);
                isValid = true;
            } else {
                System.out.println("Неправильний пароль. Введіть пароль ще раз.");
            }
        }
    }
    
    private static String enterEmail(Customer.CustomerBuilder builder, Scanner scanner) {
        boolean isValid = false;
        while (!isValid) {
            System.out.print("Електронна пошта: ");
            String email = scanner.nextLine().trim();
            if (Validation.isValidEmail(email)) {
                builder.email(email);
                isValid = true;
                return email;
                
            } else {
                System.out.println(
                        "Неправильна електронна пошта. Введіть дані користувача ще раз.");
            }
            
        }
        return null;
    }
}
