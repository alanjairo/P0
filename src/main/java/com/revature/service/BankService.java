package com.revature.service;

import com.revature.entity.Bank;
import com.revature.entity.User;
import com.revature.exception.LoginFail;
import com.revature.exception.RegisterFail;
import com.revature.repo.BankDao;

import javax.lang.model.type.NullType;
import java.util.ArrayList;
import java.util.List;

public class BankService {
    private BankDao bankDao;

    public BankService(BankDao bankDao){
        this.bankDao = bankDao;
    }

    public Bank validateBankCred(Bank newBankCred)
    {
        return bankDao.createNewAccount(newBankCred);
    }

    public List<Bank> specificAccounts(User user)
    {
        List<Bank> accounts = new ArrayList<>();
        try{
            for (Bank bank : bankDao.getAllAccounts()) {
                boolean cMatch = bank.getAccountUser() == user.getUserId();
                boolean aMatch = bank.getJointUser() == user.getUserId();
                if (cMatch || aMatch) {
                    accounts.add(bank);
                    System.out.println("Account Id: " + bank.getAccountId() + "\nBank Name: " +
                            bank.getBankName() + "\nAccount Type: " + bank.getAccountType() +
                            "\nBalance: " + bank.getBalance() + "\n");
                }
            }
            return accounts;
        }catch (LoginFail e)
        {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void depositCheck (List<Bank> accounts, int accountId, double deposit)
    {
        double result = 0.0;
        try {
            for (Bank bank : accounts) {
                boolean idMatch = bank.getAccountId() == accountId;
                if (idMatch) {
                    result = bank.getBalance() + deposit;
                    bank.setBalance(result);

                    bankDao.updateMoney(bank);
                    System.out.println("\nAccount: " + bank.getAccountId() + "\nBalance: " + bank.getBalance());
                    break;
                }
            }
        }catch (LoginFail e)
        {
            System.out.println(e.getMessage());
        }
    }
    public void withdrawCheck (List<Bank> accounts, int accountId, double withdraw)
    {
        double result = 0.0;
        try {
            for (Bank bank : accounts) {
                boolean idMatch = bank.getAccountId() == accountId;
                if (idMatch && withdraw <= bank.getBalance()) {
                    result = bank.getBalance() - withdraw;
                    bank.setBalance(result);

                    bankDao.updateMoney(bank);
                    System.out.println("\nAccount: " + bank.getAccountId() + "\nCurrent Balance: " + bank.getBalance() +"\n");
                }
                else{
                    System.out.println("You cannot withdraw more money than you have.\n");
                }
                break;
            }
        }catch (LoginFail e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void close(List<Bank> accounts, int accountId)
    {
        try {
            for (Bank bank : accounts) {
                boolean idMatch = bank.getAccountId() == accountId;
                if (idMatch) {


                    bankDao.closeAccount(bank);
                    System.out.println("\nAccount " + accountId + " has been closed.");
                    break;
                }
            }
        }catch (LoginFail e)
        {
            System.out.println(e.getMessage());
        }
    }
}
