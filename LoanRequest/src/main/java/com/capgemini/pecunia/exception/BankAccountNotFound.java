package com.capgemini.pecunia.exception;

@SuppressWarnings("serial")
public class BankAccountNotFound extends RuntimeException 
{
    public BankAccountNotFound(String exception) {
        super(exception);
    }
}
