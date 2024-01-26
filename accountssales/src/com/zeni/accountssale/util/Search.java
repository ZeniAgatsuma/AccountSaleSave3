package com.zeni.accountssale.util;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Search {
    
    public static <T> void searchAndPrintInJsonFile(String fileName, Class<T> type,
            String keyword) {
        try (FileReader reader = new FileReader("Data/" + fileName)) {
            Gson gson = new Gson();
            JsonArray jsonArray = gson.fromJson(reader, JsonArray.class);
            Set<T> searchResults = new HashSet<>();
            
            for (JsonElement jsonElement : jsonArray) {
                JsonObject jsonObject = jsonElement.getAsJsonObject();
                T object = gson.fromJson(jsonObject, type);
                
                // Додаткова логіка для пошуку за ключовим словом
                if (containsKeyword(jsonObject, keyword)) {
                    searchResults.add(object);
                }
            }
            
            if (searchResults != null && !searchResults.isEmpty()) {
                System.out.println("Результати пошуку:");
                for (T result : searchResults) {
                    System.out.println(result);
                }
            } else {
                System.out.println("Нічого не знайдено.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static boolean containsKeyword(JsonObject jsonObject, String keyword) {
        for (String key : jsonObject.keySet()) {
            JsonElement value = jsonObject.get(key);
            if (value.isJsonPrimitive() && value.getAsString().contains(keyword)) {
                return true;
            }
        }
        return false;
    }
}
