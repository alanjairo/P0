package com.revature.exception;

import com.revature.entity.User;

public class UserSQLException extends RuntimeException{
    public UserSQLException(String m)
    {
        super(m);
    }
}
