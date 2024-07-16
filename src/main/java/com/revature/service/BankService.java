package com.revature.service;

import com.revature.entity.Bank;
import com.revature.repo.BankDao;

public class BankService {
    private BankDao bankDao;

    public BankService(BankDao bankDao){
        this.bankDao = bankDao;
    }

    public Bank validateBankCred(Bank newBankCred)
    {
        return bankDao.createNewAccount(newBankCred);
    }

}
