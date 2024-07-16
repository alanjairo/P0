package com.revature.service;

import com.revature.entity.Bank;
import com.revature.entity.User;
import com.revature.repo.BankDao;

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

    public void specificAccounts(User user)
    {
        for(Bank bank: bankDao.getAllAccounts()) {
            boolean cMatch = bank.getAccountUser() == user.getUserId();
            boolean aMatch = bank.getJointUser() == (user.getUserId());
            if(cMatch || aMatch) {
                System.out.println("Account Id: " + bank.getAccountId() + "\nBank Name: " +
                        bank.getBankName() + "\nAccount Type: " + bank.getAccountType() +
                        "\nBalance: " + bank.getBalance() + "\n");

            }
        }
    }

}
