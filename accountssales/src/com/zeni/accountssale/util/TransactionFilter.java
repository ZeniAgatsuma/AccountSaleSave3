package com.zeni.accountssale.util;

import static com.zeni.accountssale.presistance.JsonDataManager.filterAndPrintByField;

import java.util.Date;
import java.util.Set;

public class TransactionFilter {
    
    public static <T> void filterAndPrintByStatus(Set<T> items, String status) {
        filterAndPrintByField(items, "status", status);
    }
    
    public static <T> void filterAndPrintByDate(Set<T> items, Date date) {
        filterAndPrintByField(items, "date", date);
    }
    
    public static <T> void filterAndPrintByAmount(Set<T> items, double amount) {
        filterAndPrintByField(items, "amount", amount);
    }
}
