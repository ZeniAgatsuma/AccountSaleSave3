package com.zeni.accountssale.util;

import static com.zeni.accountssale.presistance.JsonDataManager.filterAndPrintByField;

import java.util.Set;

public class GameAccountFilter {
    
    public static <T> void filterAndPrintByAchievements(Set<T> items, String achievements) {
        filterAndPrintByField(items, "achievements", achievements);
    }
    
    public static <T> void filterAndPrintByAccountId(Set<T> items, String accountId) {
        filterAndPrintByField(items, "accountId", accountId);
    }
    
    public static <T> void filterAndPrintByProgressLevel(Set<T> items, String progressLevel) {
        filterAndPrintByField(items, "progressLevel", progressLevel);
    }
    
    public static <T> void filterAndPrintByItems(Set<T> items, String itemsname) {
        filterAndPrintByField(items, "items", itemsname);
    }
    
    public static <T> void filterAndPrintByCharacters(Set<T> items, String characters) {
        filterAndPrintByField(items, "characters", characters);
    }
}
