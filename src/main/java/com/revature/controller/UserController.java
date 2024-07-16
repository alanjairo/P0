package com.revature.controller;

import com.revature.exception.LoginFail;
import com.revature.exception.RegisterFail;
import com.revature.service.UserService;
import com.revature.entity.User;

import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class UserController {
    private Scanner scan;
    private UserService userService;

    public UserController(Scanner scan, UserService userService)
    {
        this.scan = scan;
        this.userService = userService;
    }

    public User promptUserService(Map<String,String> userMap)
    {
        System.out.println("Would you like to: ");
        System.out.println("1. Register Account\n2. Login\nq. To Quit...");
        try{
            User user = new User();
            String userAction = scan.nextLine();
            switch (userAction) {
                case "1":
                    user = registerNewUser();
                    break;
                case "2":
                    user = login();
                    userMap.put("User", user.toString());
                    break;
                case "q":
                    System.out.println("Goodbye!");
                    userMap.put("Continue Loop", "False");
            }
            return user;
        }catch (LoginFail e)
        {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public User registerNewUser()
    {
        User newCreds = getUserCred();
        User newUser = userService.validateUserCred(newCreds);
        if(newUser == null)
        {
            System.out.println("Username already exists, Please try another username.");
            return null;
        }
        else
        {
            System.out.printf("New account created: %s", newUser);
            return newUser;
        }
    }

    public User login()
    {
        return userService.checkLoginCred(getUserCred());
    }


    public User getUserCred()
    {
        int userId;
        String newUsername;
        String newPassword;
        Random r = new Random();

        userId = r.nextInt(10000);
        System.out.print("Please enter a username: ");
        newUsername = scan.nextLine();
        System.out.print("Please enter a password: ");
        newPassword = scan.nextLine();
        //System.out.println("Username: " + newUsername + "\nPassword: " + newPassword);
        return new User(userId, newUsername,newPassword);
    }

}

