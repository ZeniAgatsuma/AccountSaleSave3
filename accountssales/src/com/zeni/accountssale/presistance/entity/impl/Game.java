package com.zeni.accountssale.presistance.entity.impl;

import com.zeni.accountssale.presistance.entity.Entity;
import java.util.UUID;

public class Game extends Entity {
    
    private final String gameTitle;
    private final String gamePublisher;
    private final String gameDeveloper;
    private final String gameGenre;
    private final String gamePlatform;
    
    private Game(UUID gameId, String gameTitle, String gamePublisher, String gameDeveloper,
            String gameGenre, String gamePlatform) {
        super(gameId);
        this.gameTitle = gameTitle;
        this.gamePublisher = gamePublisher;
        this.gameDeveloper = gameDeveloper;
        this.gameGenre = gameGenre;
        this.gamePlatform = gamePlatform;
    }
    
    public static GameBuilder builder() {
        return new GameBuilder();
    }
    
    public String getGameTitle() {
        return gameTitle;
    }
    
    public String getGamePublisher() {
        return gamePublisher;
    }
    
    public String getGameDeveloper() {
        return gameDeveloper;
    }
    
    public String getGameGenre() {
        return gameGenre;
    }
    
    public String getGamePlatform() {
        return gamePlatform;
    }
    
    @Override
    public String toString() {
        return String.format(
                "Game{id=%s, title='%s', publisher='%s', developer='%s', genre='%s', platform='%s'}",
                getId(), gameTitle, gamePublisher, gameDeveloper, gameGenre, gamePlatform);
    }
    
    public static class GameBuilder {
        
        private UUID gameId;
        private String gameTitle;
        private String gamePublisher;
        private String gameDeveloper;
        private String gameGenre;
        private String gamePlatform;
        
        private GameBuilder() {
            this.gameId = UUID.randomUUID();
        }
        
        public GameBuilder gameId(UUID gameId) {
            this.gameId = gameId;
            return this;
        }
        
        public GameBuilder gameTitle(String gameTitle) {
            this.gameTitle = gameTitle;
            return this;
        }
        
        public GameBuilder gamePublisher(String gamePublisher) {
            this.gamePublisher = gamePublisher;
            return this;
        }
        
        public GameBuilder gameDeveloper(String gameDeveloper) {
            this.gameDeveloper = gameDeveloper;
            return this;
        }
        
        public GameBuilder gameGenre(String gameGenre) {
            this.gameGenre = gameGenre;
            return this;
        }
        
        public GameBuilder gamePlatform(String gamePlatform) {
            this.gamePlatform = gamePlatform;
            return this;
        }
        
        public Game build() {
            return new Game(gameId, gameTitle, gamePublisher, gameDeveloper, gameGenre,
                    gamePlatform);
        }
    }
}