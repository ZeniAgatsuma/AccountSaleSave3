package com.zeni.accountssale.presistance.entity;

public enum ErrorTemplates {
    REQUIRED("Поле %s є обов'язковим до заповнення."),
    INVALID_EMAIL("Неправильний формат електронної пошти."),
    INVALID_LENGTH("Поле %s має неприпустиму довжину."),
    PASSWORD_LENGTH("Пароль повинен бути від 8 до 32 символів."),
    PASSWORD_LOWERCASE("Пароль повинен містити принаймні одну маленьку літеру."),
    PASSWORD_UPPERCASE("Пароль повинен містити принаймні одну велику літеру."),
    PASSWORD_DIGIT("Пароль повинен містити принаймні одну цифру."),
    PASSWORD_SPECIAL_CHAR(
            "Пароль повинен містити принаймні один спеціальний символ (@#$%%^&+=).");
    private final String template;
    
    ErrorTemplates(String template) {
        this.template = template;
    }
    
    public String getTemplate() {
        return template;
    }
}
