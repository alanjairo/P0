package com.revature.exception;

public class RegisterFail extends RuntimeException{

    public RegisterFail(String m)
    {
        super(m);
    }
}