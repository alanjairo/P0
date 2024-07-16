package com.revature.service;

import com.revature.repo.BankDao;

public class BankService {
    private BankDao bankDao;

    public BankService(BankDao bankDao){
        this.bankDao = bankDao;
    }


}
