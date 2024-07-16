package com.revature.controller;

import com.revature.entity.Bank;
import com.revature.entity.User;
import com.revature.exception.LoginFail;
import com.revature.exception.RegisterFail;
import com.revature.repo.BankDao;
import com.revature.service.BankService;
import com.revature.service.UserService;

import java.nio.file.Path;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class BankController {
    private Scanner scan;
    private BankService bServe;
    private UserController userController;

    public BankController(Scanner scan, BankService bServe, UserController userController)
    {
        this.scan = scan;
        this.bServe = bServe;
        this.userController = userController;
    }

    public void promptBankService(Map<String,String> userMap, User user)
    {
        System.out.println("Would you like to: ");
        System.out.println("1. Create a New Checking Account\n2. Check Bank Details\n" +
                "3. Deposit Money\n4. Withdraw Money\n5. Close a Checking Account\n" +
                "q. To Log Out...");
        try{
            String bankAction = scan.nextLine();
            Bank bankInfo = new Bank();
            switch (bankAction) {
                case "1":
                    //create new account
                    bankInfo = registerNewAccount(user);
                    break;
                case "2":
                    //check details
                    bankInfo = bankAccount();
                    break;
                case "3":
                    //deposit money
                    break;
                case "4":
                    //withdraw money
                    break;
                case "5":
                    //close an account
                    break;
                case "q":
                    System.out.println("Logging Out!");
                    userMap.put("Continue Loop", "False");
            }
        }catch (LoginFail e)
        {
            System.out.println(e.getMessage());
        }
    }

    public Bank registerNewAccount(User user){
        Bank newCred = createNewAccount(user);
        Bank newAccount = bServe.validateBankCred(newCred);
        if(newAccount == null)
        {
            System.out.println("Username already exists, Please try another username.");
            return null;
        }
        else
        {
            System.out.printf("New account created: %s", newAccount.getAccountUser() + "\n");
            return newAccount;
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
            throw new RegisterFail("L");
        }
        //System.out.println("Username: " + newUsername + "\nPassword: " + newPassword);
        return new Bank(accountId,bankName,accountType,balance,account_id,joint_id);
    }

    public Bank bankAccount()
    {

        return null;
        //return bServe.checkBankInfo();
    }

}
