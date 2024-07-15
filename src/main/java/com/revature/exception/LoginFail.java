package com.revature.exception;

public class LoginFail extends RuntimeException{

    public LoginFail(String m)
    {
        super(m);
    }
}
