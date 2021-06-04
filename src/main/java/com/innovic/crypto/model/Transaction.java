package com.innovic.crypto.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


@Entity
public class Transaction {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;

    @OneToOne
    private Market market;

    @Column(nullable = false)
    private Double ammount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionType transactionType;

    public Transaction() {}

    public Transaction(Wallet wallet, Market market, Double ammount, TransactionType transactionType){
        this.wallet = wallet;
        this.market = market;
        this.ammount = ammount;
        this.transactionType = transactionType;
    }

    public Market getMarket() {
        return market;
    }

    public Double getAmmount() {
        return ammount;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

}
