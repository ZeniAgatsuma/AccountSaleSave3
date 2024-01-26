package com.zeni.accountssale.util;

import static com.zeni.accountssale.presistance.JsonDataManager.filterAndPrintByField;

import java.util.Set;

public class GameFilter {
    
    public static <T> void filterAndPrintByGameTitle(Set<T> items, String gameTitle) {
        filterAndPrintByField(items, "gameTitle", gameTitle);
    }
    
    public static <T> void filterAndPrintByGamePublisher(Set<T> items, String gamePublisher) {
        filterAndPrintByField(items, "gamePublisher", gamePublisher);
    }
    
    public static <T> void filterAndPrintByGameDeveloper(Set<T> items, String gameDeveloper) {
        filterAndPrintByField(items, "gameDeveloper", gameDeveloper);
    }
    
    public static <T> void filterAndPrintByGameGenre(Set<T> items, String gameGenre) {
        filterAndPrintByField(items, "gameGenre", gameGenre);
    }
    
    public static <T> void filterAndPrintByGamePlatform(Set<T> items, String gamePlatform) {
        filterAndPrintByField(items, "gamePlatform", gamePlatform);
    }
}
