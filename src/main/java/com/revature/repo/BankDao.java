package com.revature.repo;

import com.revature.entity.Bank;

import java.util.List;

public interface BankDao {
    Bank createNewAccount(Bank  newAccountCred);
    List<Bank> getAllAccounts();
}
