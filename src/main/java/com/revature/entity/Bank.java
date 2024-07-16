package com.revature.entity;

import java.io.Serializable;

public class Bank implements Serializable {
    private int accountId;
    private String bankName;
    private String accountType;
    private double balance;
    private int accountUser;
    private int jointUser;

    public Bank(){}

    public Bank(int accountId, String bankName, String accountType,
                double balance, int accountUser, int jointUser){
        this.accountId = accountId;
        this.bankName = bankName;
        this.accountType = accountType;
        this.balance = balance;
        this.accountUser = accountUser;
        this.jointUser = jointUser;
    }
    public int getAccountId() {return accountId;}
    public void setAccountId(int accountId) {this.accountId = accountId;}

    public String getBankName() {return bankName;}
    public void setBankName(String bankName) {this.bankName = bankName;}

    public String getAccountType() {return accountType;}
    public void setAccountType(String accountType) {this.accountType = accountType;}

    public double getBalance() {return balance;}
    public void setBalance(double balance) {this.balance = balance;}

    public int getAccountUser() {return accountUser;}
    public void setAccountUser(int accountUser) {this.accountUser = accountUser;}

    public int getJointUser() {return jointUser;}
    public void setJointUser(int jointUser) {this.jointUser = jointUser;}
}
