package com.lyyco.rays.service.pattern.facade;


import java.math.BigDecimal;
import java.util.Hashtable;

/**
 * BankService class is the facade
 * it's public methods are simple to use and show no hint of the
 * underlying interface and implementing classes
 * Author liyangyang
 * 2019/1/13
 */
public class BankService {
    private Hashtable<Integer, IAccount> bankAccounts;

    public BankService() {
        this.bankAccounts = new Hashtable<>();
    }

    public int createNewAccount(String type, BigDecimal initAmount) {
        IAccount newAccount = null;
        switch (type) {
            case "chequing":
                newAccount = new Chequing();
                break;
            case "saving":
                newAccount = new Saving();
                break;
        }
        if (newAccount != null) {
            this.bankAccounts.put(newAccount.getAccountNumber(),newAccount);
            return newAccount.getAccountNumber();
        }
        return 0;
    }

}
