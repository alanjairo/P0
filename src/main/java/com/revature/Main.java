package com.revature;

import com.revature.controller.UserController;
import com.revature.repo.SQLiteUserDao;
import com.revature.repo.UserDao;
import com.revature.service.UserService;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    /*  registration
            - new user prompt
            - user provide username & password
            - system check for system requirements
            - user informed for results
    */
        try(Scanner scan = new Scanner(System.in)) {
            UserDao userDao = new SQLiteUserDao();
            UserService userService = new UserService(userDao);
            UserController userControl = new UserController(scan, userService);

            Map<String, String> userMap = new HashMap<>();
            userMap.put("Continue Loop", "True");

            while(Boolean.parseBoolean(userMap.get("Continue Loop")))
            {
                userControl.promptUserService(userMap);
                if(userMap.containsKey("User"))
                {
                    //BankActivity();
                    System.out.printf("bank stuff for %s happens", userMap.get("User"));
                    scan.nextLine();
                }
            }
        }
    }
}