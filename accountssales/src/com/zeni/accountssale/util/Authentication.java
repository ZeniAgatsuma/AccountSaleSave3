package com.zeni.accountssale.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zeni.accountssale.presistance.entity.impl.Customer;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import org.mindrot.bcrypt.BCrypt;

public class Authentication {
    
    private static final String CUSTOMER_FILE_PATH = "Data/Customer.json";
    
    public static void authenticateCustomerFromInput() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Нікнейм: ");
        String username = scanner.nextLine().trim();
        System.out.print("Пароль: ");
        String password = scanner.nextLine().trim();
        
        if (authenticateCustomer(username, password)) {
            System.out.println("Користувач успішно авторизований!");
        } else {
            System.out.println("Помилка авторизації. Нікнейм або пароль невірний.");
        }
    }
    
    private static boolean authenticateCustomer(String username, String password) {
        // Зчитування користувачів з файлу
        Set<Customer> customerSet = readCustomersFromJson();
        
        // Пошук користувача за нікнеймом
        for (Customer customer : customerSet) {
            if (customer.getNickname().equals(username)) {
                return BCrypt.checkpw(password, customer.getPassword());
            }
        }
        
        return false; // Користувача з таким нікнеймом не знайдено
    }
    
    private static Set<Customer> readCustomersFromJson() {
        try (FileReader reader = new FileReader(CUSTOMER_FILE_PATH)) {
            Type setType = new TypeToken<HashSet<Customer>>() {
            }.getType();
            return new Gson().fromJson(reader, setType);
        } catch (IOException e) {
            e.printStackTrace();
            return new HashSet<>();
        }
    }
}
