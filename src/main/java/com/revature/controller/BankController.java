package com.revature.controller;

import com.revature.entity.Bank;
import com.revature.entity.User;
import com.revature.exception.LoginFail;
import com.revature.exception.RegisterFail;
import com.revature.repo.BankDao;
import com.revature.service.BankService;

import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class BankController {
    private Scanner scan;
    private BankService bServe;

    public BankController(Scanner scan, BankService bServe)
    {
        this.scan = scan;
        this.bServe = bServe;
    }

    public void promptBankService(Map<String,String> userMap)
    {
        System.out.println("Would you like to: ");
        System.out.println("1. Create a New Checking Account\n2. Check Bank Details\n" +
                "3. Deposit Money\n4. Withdraw Money\n5. Close a Checking Account\n" +
                "q. To Log Out...");
        try{
            String bankAction = scan.nextLine();
            switch (bankAction) {
                case "1":
                    //create new account
                    createNewAccount(userMap);
                    break;
                case "2":
                    //check details
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

    public Bank createNewAccount(Map<String,String> userMap)
    {
        //userMap should be the username of user
        //it should connect to username id
        // make the account id for the account the user id

        User user = new User();
        Random r = new Random();
        int accountId = r.nextInt(10000);
        String bankName;
        String accountType;
        double balance;
        int account_id;
        int joint_id;

        if(user.getUsername().equals(userMap.get("User")))
            account_id = user.getUserId();
        else
            throw new RegisterFail("Username does not match any ID.");

        System.out.print("Which bank are you opening an account with: ");
        bankName = scan.nextLine();
        System.out.print("What type of account are you opening: ");
        accountType = scan.nextLine();
        System.out.print("How much are you depositing initially: ");
        balance = Double.parseDouble(scan.nextLine());
        System.out.println("Will this account be a joint account(y/n): ");

        if(scan.nextLine().equals("y")||scan.nextLine().equals("Y"))
        {
            System.out.println("Enter joint owner's ID: ");
            joint_id = scan.nextInt();
        }
        else if(scan.nextLine().equals("n")||scan.nextLine().equals("N"))
            joint_id = user.getUserId();// if not joint defaults to single user's ID avoiding null
        else
            throw new RegisterFail("L");
        //System.out.println("Username: " + newUsername + "\nPassword: " + newPassword);
        return new Bank(accountId,bankName,accountType,balance,account_id,joint_id);
    }

}
