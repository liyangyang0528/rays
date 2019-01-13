package com.lyyco.rays.service.pattern.facade;

import java.math.BigDecimal;

/**
 * @Description
 * @Author Created by lyy
 * @Date: Created in 14:12 2019/1/13
 */
public interface IAccount {
    
    void deposit(BigDecimal amount);

    void withdraw(BigDecimal amount);

    void transfer(BigDecimal amount);

    int getAccountNumber();
}
