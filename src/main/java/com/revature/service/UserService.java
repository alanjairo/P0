package com.revature.service;

import com.revature.entity.User;
import com.revature.exception.LoginFail;
import com.revature.exception.RegisterFail;
import com.revature.repo.UserDao;

import java.util.List;

public class UserService {

    private UserDao userDao;

    public UserService(UserDao userDao)
    {
        this.userDao = userDao;
    }

    public User validateUserCred(User newUserCred){
        // 1. Check Length
        if(checkUsernamePasswordLength(newUserCred))
        {
            // 2. Check Unique
            if(checkUniqueUsername(newUserCred))
            {
                return userDao.createUser(newUserCred);
            }
            else
            {
                return null;
            }
        }
        throw new RegisterFail("Password Does Not Meet Requirements. Please Try Again.");
    }

    public User checkLoginCred(User cred)
    {
        for(User user: userDao.getAllUsers())
        {
            boolean uMatch = user.getUsername().equals(cred.getUsername());
            boolean pMatch = user.getPassword().equals(cred.getPassword());
            if(uMatch && pMatch)
            {
                return cred;
            }
        }
        throw new LoginFail("Credentials Invalid, Please Try Again.");
        //return cred;
    }

    public boolean checkUsernamePasswordLength(User newUserCred){
        boolean uValid = newUserCred.getUsername().length() <= 30;
        boolean pValid = newUserCred.getPassword().length() <= 30;
        return uValid && pValid;
    }

    public boolean checkUniqueUsername(User newUserCred){
        boolean uniqueUsername = true;
        List<User> users = userDao.getAllUsers();
        for(User user : users)
        {
            if(newUserCred.getUsername().equals(user.getUsername()))
            {
                uniqueUsername = false;
                break;
            }
        }

        return uniqueUsername;
    }
}
