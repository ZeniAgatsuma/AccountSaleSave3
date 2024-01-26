package com.zeni.accountssale.presistance.entity.impl;

import com.zeni.accountssale.presistance.entity.Entity;
import java.util.Date;
import java.util.UUID;

public class Transaction extends Entity {
    
    private final String status;
    private final Date date;
    private final double amount;
    
    private Transaction(UUID transactionId, String status, Date date, double amount) {
        super(transactionId);
        this.status = status;
        this.date = date;
        this.amount = amount;
    }
    
    public static TransactionBuilder builder() {
        return new TransactionBuilder();
    }
    
    public String getStatus() {
        return status;
    }
    
    public Date getDate() {
        return date;
    }
    
    public double getAmount() {
        return amount;
    }
    
    @Override
    public String toString() {
        return String.format("Transaction{id=%s, status='%s', date=%s, amount=%.2f}",
                getId(), status, date, amount);
    }
    
    public static class TransactionBuilder {
        
        private UUID transactionId;
        private String status;
        private Date date;
        private double amount;
        
        private TransactionBuilder() {
            this.transactionId = UUID.randomUUID();
            this.date = new Date();
        }
        
        public TransactionBuilder transactionId(UUID transactionId) {
            this.transactionId = transactionId;
            return this;
        }
        
        public TransactionBuilder status(String status) {
            this.status = status;
            return this;
        }
        
        public TransactionBuilder date(Date date) {
            this.date = date;
            return this;
        }
        
        public TransactionBuilder amount(double amount) {
            this.amount = amount;
            return this;
        }
        
        public Transaction build() {
            return new Transaction(transactionId, status, date, amount);
        }
    }
}
