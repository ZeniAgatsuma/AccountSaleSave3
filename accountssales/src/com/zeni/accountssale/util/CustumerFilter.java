package com.zeni.accountssale.util;

import static com.zeni.accountssale.presistance.JsonDataManager.filterAndPrintByField;

import java.util.Set;

public class CustumerFilter {
    
    public static <T> void filterAndPrintByNickname(Set<T> items, String nickname) {
        filterAndPrintByField(items, "nickname", nickname);
    }
    
    public static <T> void filterAndPrintByPassword(Set<T> items, String password) {
        filterAndPrintByField(items, "password", password);
    }
    
    public static <T> void filterAndPrintByEmail(Set<T> items, String email) {
        filterAndPrintByField(items, "email", email);
    }
    
    public static <T> void filterAndPrintByPaymentMethod(Set<T> items, String paymentMethod) {
        filterAndPrintByField(items, "paymentMethod", paymentMethod);
    }
}
