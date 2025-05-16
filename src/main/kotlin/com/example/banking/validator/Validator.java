package com.example.banking.validator;

import com.example.banking.domain.Account;

public class Validator {

    private static volatile Validator INSTANCE;

    public Validator() {
    }

    public static Validator getInstance() {
        if (INSTANCE == null) {
            synchronized (Validator.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Validator();
                }
            }
        }
        return INSTANCE;
    }


    public boolean createAccountValidate(Account account) {
        return false;
    }
}
