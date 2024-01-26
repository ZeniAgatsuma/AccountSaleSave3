package com.zeni.accountssale.presistance;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zeni.accountssale.presistance.entity.impl.Customer;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class JsonDataManager {
    
    public static <T> void writeObjectsToJsonFile(Set<T> newObjects, String fileName,
            Class<T> objectClass) {
        // Завантаження існуючих даних з файлу
        Set<T> existingObjects = readFromFile(fileName, objectClass);
        
        if (existingObjects == null) {
            existingObjects = new HashSet<>();
        }
        
        // Додавання нових об'єктів
        existingObjects.addAll(newObjects);
        
        // Запис оновлених даних у файл
        try (FileWriter writer = new FileWriter(new File("Data", fileName))) {
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create();
            
            gson.toJson(existingObjects, writer);
            
            System.out.println("Дані збережено в файл " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static <T> void createJsonFile(String fileName, Set<T> objects) {
        try {
            // Додаємо розширення ".json" до імені файлу
            fileName = fileName.endsWith(".json") ? fileName : fileName + ".json";
            
            // Створення об'єкту File для файлу в папці "Data"
            File file = new File("Data", fileName);
            
            // Якщо файл існує, читаємо його вміст
            Set<T> existingObjects = new HashSet<>();
            if (file.exists()) {
                try (FileReader fileReader = new FileReader(file)) {
                    Gson gson = new Gson();
                    existingObjects = gson.fromJson(fileReader, new HashSet<T>().getClass());
                }
            }
            
            // Додаємо нові об'єкти до існуючого вмісту
            existingObjects.addAll(objects);
            
            // Створення FileWriter для запису JSON у файл
            try (FileWriter writer = new FileWriter(file)) {
                // Запис JSON у файл
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                gson.toJson(existingObjects, writer);
            }
            
            System.out.println("JSON файл створено/оновлено успішно: " + file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static <T> Set<T> readFromFile(String fileName, Class<T> clazz) {
        try {
            File file = new File("Data", fileName);
            
            if (!file.exists()) {
                System.out.println("Файл не знайдено: " + file.getAbsolutePath());
                return null;
            }
            
            try (FileReader reader = new FileReader(file)) {
                Gson gson = new Gson();
                Type type = com.google.gson.internal.$Gson$Types.newParameterizedTypeWithOwner(null,
                        HashSet.class, clazz);
                return gson.fromJson(reader, type);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static void deleteFromJsonFileByIndex(String fileName, int index) {
        Set<Customer> customers = readFromFile(fileName, Customer.class);
        
        if (customers == null || customers.isEmpty()) {
            System.out.println("Файл не знайдено або порожній.");
            return;
        }
        
        List<Customer> customerList = new ArrayList<>(customers);
        
        if (index < 0 || index >= customerList.size()) {
            System.out.println("Некоректний номер запису для видалення.");
            return;
        }
        
        // Видаляємо елемент за номером запису
        customerList.remove(index);
        
        // Оновлюємо Set знову
        customers = new HashSet<>(customerList);
        
        // Записуємо оновлений вміст у файл
        updateJsonFile(fileName, customers);
        System.out.println("Елемент за номером " + index + " видалено успішно.");
    }
    
    public static <T> void updateJsonFile(String fileName, Set<T> objects) {
        try {
            // Додаємо розширення ".json" до імені файлу
            fileName = fileName.endsWith(".json") ? fileName : fileName + ".json";
            
            // Створення об'єкту File для файлу в папці "Data"
            File file = new File("Data", fileName);
            
            if (!file.exists()) {
                System.out.println("Файл не знайдено: " + file.getAbsolutePath());
                return;
            }
            
            // Створення FileWriter для запису JSON у файл
            try (FileWriter writer = new FileWriter(file)) {
                // Запис JSON у файл
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                gson.toJson(new HashSet<>(objects), writer);
            }
            System.out.println("JSON файл оновлено успішно: " + file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void filterByPaymentMethod(Set<Customer> customers, String paymentMethod) {
        if (customers == null || customers.isEmpty()) {
            System.out.println("Файл не знайдено або порожній.");
            return;
        }
        
        Set<Customer> filteredCustomers = customers.stream()
                .filter(customer -> paymentMethod.equals(customer.getPaymentMethod()))
                .collect(Collectors.toSet());
        
        if (filteredCustomers.isEmpty()) {
            System.out.println("Не знайдено користувачів з методом оплати: " + paymentMethod);
        } else {
            System.out.println("Користувачі з методом оплати " + paymentMethod + ":");
            for (Customer customer : filteredCustomers) {
                System.out.println(customer);
            }
        }
    }
    
    public static <T> void filterAndPrintByField(Set<T> items, String fieldName, Object value) {
        if (items == null || items.isEmpty()) {
            System.out.println("Колекція не знайдена або порожня.");
            return;
        }
        
        Set<T> filteredItems = items.stream()
                .filter(item -> value.equals(getFieldValue(item, fieldName)))
                .collect(Collectors.toSet());
        
        if (filteredItems.isEmpty()) {
            System.out.println("Не знайдено елементів за полем " + fieldName + ": " + value);
        } else {
            System.out.println("Елементи за полем " + fieldName + " зі значенням " + value + ":");
            for (T item : filteredItems) {
                System.out.println(item);
            }
        }
    }
    
    private static <T> String getFieldValue(T item, String fieldName) {
        try {
            Field field = item.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            Object value = field.get(item);
            return value != null ? value.toString() : null;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
}
