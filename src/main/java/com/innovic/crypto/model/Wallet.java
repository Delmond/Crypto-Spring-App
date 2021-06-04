package com.innovic.crypto.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
public class Wallet {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "wallet")
    private Set<Transaction> transactions;

    public Wallet() {}

    public Wallet(User user) {
        this.user = user;
    }

    @JsonIgnore
    public User getUser() {
        return user;
    }

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public Double getBalance(){

        Double total = 0.0;

        if(transactions == null)
            return total;

        for (Transaction transaction : transactions) {
            TransactionType transactionType = transaction.getTransactionType();
            Double quantity = transaction.getAmmount();

            switch(transactionType){
                case ADD_FUNDS:
                    total += quantity;
                    break;
                case BUY:
                    total -= quantity*transaction.getMarket().getPrice();
                    break;
                case SELL:
                    total += quantity*transaction.getMarket().getPrice();
                    break;
            }

        }
        return total;

    }

}
