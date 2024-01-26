package com.zeni.accountssale.presistance.entity.impl;

import java.util.UUID;

public class GameAccount {
    
    private final String achievements;
    private final String accountId; // Змінено тип на String
    private final int progressLevel;
    private final String items;
    private final String characters;
    
    private GameAccount(String achievements, String accountId, int progressLevel, String items,
            String characters) {
        this.achievements = achievements;
        this.accountId = accountId;
        this.progressLevel = progressLevel;
        this.items = items;
        this.characters = characters;
    }
    
    public static GameAccountBuilder builder() {
        return new GameAccountBuilder();
    }
    
    public String getAchievements() {
        return achievements;
    }
    
    public String getAccountId() {
        return accountId;
    }
    
    public int getProgressLevel() {
        return progressLevel;
    }
    
    public String getItems() {
        return items;
    }
    
    public String getCharacters() {
        return characters;
    }
    
    @Override
    public String toString() {
        return String.format(
                "GameAccount{achievements='%s', accountId='%s', progressLevel=%d, items='%s', characters='%s'}",
                achievements, accountId, progressLevel, items, characters);
    }
    
    public static class GameAccountBuilder {
        
        private String achievements;
        private String accountId; // Змінено тип на String
        private int progressLevel;
        private String items;
        private String characters;
        
        private GameAccountBuilder() {
            this.accountId = UUID.randomUUID()
                    .toString(); // Генеруємо UUID та конвертуємо його у рядок
        }
        
        public GameAccountBuilder achievements(String achievements) {
            this.achievements = achievements;
            return this;
        }
        
        public GameAccountBuilder accountId(String accountId) {
            this.accountId = accountId;
            return this;
        }
        
        public GameAccountBuilder progressLevel(int progressLevel) {
            this.progressLevel = progressLevel;
            return this;
        }
        
        public GameAccountBuilder items(String items) {
            this.items = items;
            return this;
        }
        
        public GameAccountBuilder characters(String characters) {
            this.characters = characters;
            return this;
        }
        
        public GameAccount build() {
            return new GameAccount(achievements, accountId, progressLevel, items, characters);
        }
    }
}
