package com.revature.controller;

import com.revature.entity.Bank;
import com.revature.entity.User;
import com.revature.exception.LoginFail;
import com.revature.exception.RegisterFail;
import com.revature.service.BankService;

import java.util.*;

public class BankController {
    private Scanner scan;
    private BankService bServe;

    public BankController(Scanner scan, BankService bServe)
    {
        this.scan = scan;
        this.bServe = bServe;
    }

    public void promptBankService(Map<String,String> userMap, User user)
    {
        try{
            System.out.println("Would you like to: \n" +
                "1. Create a New Account\n" +
                "2. Check Bank Account Details\n" +
                "3. Deposit Money\n" +
                "4. Withdraw Money\n" +
                "5. Close an Account\n" +
                "q. To Log Out...");
            String bankAction = scan.nextLine();
            switch (bankAction) {
                case "1":
                    //create new account
                    registerNewAccount(user);
                    break;
                case "2":
                    //check details
                    bankAccount(user);
                    break;
                case "3":
                    //deposit money
                    deposit(bankAccount(user));
                    break;
                case "4":
                    //withdraw money
                    withdraw(bankAccount(user));
                    break;
                case "5":
                    //close an account
                    closeAccount(bankAccount(user));
                    break;
                case "q":
                    System.out.println("Logging Out!");
                    userMap.remove("User");
                    //userMap.put("Continue Loop", "False");
            }
        }catch (LoginFail e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void registerNewAccount(User user){
        Bank newCred = createNewAccount(user);
        Bank newAccount = bServe.validateBankCred(newCred);
        if(newAccount == null)
        {
            System.out.println("Username already exists, Please try another username.");
        }
        else
        {
            System.out.printf("New account created: %s", newAccount.getAccountUser() + "\n");
        }

    }

    public Bank createNewAccount(User user)
    {
        //userMap should be the username of user
        //it should connect to username id
        // make the account id for the account the user id

        Random r = new Random();
        int accountId = r.nextInt(10000);
        String bankName;
        String accountType;
        double balance;
        int account_id = user.getUserId();
        int joint_id;

        System.out.print("Which bank are you opening an account with: ");
        bankName = scan.nextLine();
        System.out.print("What type of account are you opening: ");
        accountType = scan.nextLine();
        System.out.print("How much are you depositing initially: ");
        balance = Double.parseDouble(scan.nextLine());
        System.out.println("Will this account be a joint account(y/n): ");

        String yORn = scan.nextLine();

        if(yORn.equals("y") || yORn.equals("Y"))
        {
            System.out.println("Enter joint owner's ID: ");
            joint_id = scan.nextInt();
        }
        else if(yORn.equals("n") || yORn.equals("N")) {
            joint_id = account_id;// if not joint defaults to single user's ID avoiding null
        }
        else {
            throw new RegisterFail("Please enter y or n.");
        }
        //System.out.println("Username: " + newUsername + "\nPassword: " + newPassword);
        return new Bank(accountId,bankName,accountType,balance,account_id,joint_id);
    }

    public List<Bank> bankAccount(User user)
    {
        List<Bank> accounts = bServe.specificAccounts(user);
        if (checkAccount(accounts))
            return accounts;
        return null;
        //throw new RegisterFail("There is no account with this user.\n" +
                //"Please create an account before continuing.");
    }

    public void deposit(List<Bank> accounts)
    {
        int accountId;
        if (checkAccount(accounts))
        {
            System.out.println("Which account would you like to deposit in to: ");
            accountId = Integer.parseInt(scan.nextLine());
            if (searchAccount(accounts, accountId))
            {
                System.out.println("How much would you like to deposit: ");
                double deposit = Double.parseDouble(scan.nextLine());
                bServe.depositCheck(accounts, accountId, deposit);
            }
        }
    }

    public void withdraw(List<Bank> accounts)
    {
        int accountId;
        if (checkAccount(accounts))
        {
            System.out.println("Which account would you like to withdraw from: ");
            accountId = Integer.parseInt(scan.nextLine());
            if (searchAccount(accounts, accountId))
            {
                System.out.println("How much would you like to withdraw: ");
                double withdraw = Double.parseDouble(scan.nextLine());
                bServe.withdrawCheck(accounts, accountId, withdraw);
            }
        }
    }

    public boolean checkAccount(List<Bank> accounts)
    {
        if (accounts.isEmpty()) {
            System.out.println("There is no account with this user.\n" +
                    "Please create an account before continuing.");
            return false;
        }
        return true;
    }

    public boolean searchAccount(List<Bank> accounts, int accountId)
    {
        for(Bank bank: accounts)
        {
            if(bank.getAccountId() == accountId)
            {
                return true;
            }
        }
        return false;
    }

    public void closeAccount(List<Bank> accounts)
    {
        int accountId;
        System.out.print("Which account would you like to close: ");
        accountId = Integer.parseInt(scan.nextLine());

        if(searchAccount(accounts,accountId))
        {
            bServe.close(accounts,accountId);
        }
    }
}
