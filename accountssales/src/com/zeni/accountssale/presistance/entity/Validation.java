package com.zeni.accountssale.presistance.entity;

import com.zeni.accountssale.presistance.exception.EntityArgumentException;

public class Validation {
    
    public static void validateNickname(String nickname) {
        final String templateName = "нікнейму";
        
        if (nickname.isBlank()) {
            throwValidationException(ErrorTemplates.REQUIRED, templateName);
        }
        if (nickname.length() < 4 || nickname.length() > 20) {
            throwValidationException(ErrorTemplates.INVALID_LENGTH, templateName);
        }
    }
    
    public static void validateEmail(String email) {
        final String templateName = "електронної пошти";
        
        if (!email.contains("@") || !email.endsWith(".com")) {
            throwValidationException(ErrorTemplates.INVALID_EMAIL, templateName);
        }
    }
    
    public static void validatePassword(String password) {
        final String templateName = "паролю";
        
        if (password == null || password.isBlank()) {
            throwValidationException(ErrorTemplates.REQUIRED, templateName);
        }
        
        if (password.length() < 8 || password.length() > 32) {
            throwValidationException(ErrorTemplates.PASSWORD_LENGTH, templateName);
        }
        
        if (!containsLowercase(password)) {
            throwValidationException(ErrorTemplates.PASSWORD_LOWERCASE, templateName);
        }
        
        if (!containsUppercase(password)) {
            throwValidationException(ErrorTemplates.PASSWORD_UPPERCASE, templateName);
        }
        
        if (!containsDigit(password)) {
            throwValidationException(ErrorTemplates.PASSWORD_DIGIT, templateName);
        }
        
        if (!containsSpecialChar(password)) {
            throwValidationException(ErrorTemplates.PASSWORD_SPECIAL_CHAR, templateName);
        }
        
    }
    
    private static boolean containsLowercase(String str) {
        return !str.equals(str.toUpperCase());
    }
    
    private static boolean containsUppercase(String str) {
        return !str.equals(str.toLowerCase());
    }
    
    private static boolean containsDigit(String str) {
        for (char c : str.toCharArray()) {
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }
    
    private static boolean containsSpecialChar(String str) {
        // Додайте інші спеціальні символи за потреби
        String specialChars = "@#$%^&+=";
        
        for (char c : str.toCharArray()) {
            if (specialChars.contains(String.valueOf(c))) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean isValidNickname(String nickname) {
        try {
            validateNickname(nickname);
            return true;
        } catch (EntityArgumentException e) {
            return false;
        }
    }
    
    public static boolean isValidEmail(String email) {
        try {
            validateEmail(email);
            return true;
        } catch (EntityArgumentException e) {
            return false;
        }
    }
    
    public static boolean isValidPassword(String password) {
        try {
            validatePassword(password);
            return true;
        } catch (EntityArgumentException e) {
            return false;
        }
    }
    
    private static void throwValidationException(ErrorTemplates errorTemplate, String fieldName) {
        throw new EntityArgumentException(errorTemplate.getTemplate().formatted(fieldName));
    }
}