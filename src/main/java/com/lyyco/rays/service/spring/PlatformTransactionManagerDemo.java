package com.lyyco.rays.service.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * Author liyangyang
 * 2018/7/4
 */
public class PlatformTransactionManagerDemo {
    /*
    Simply pass the implementation of the
    PlatformTransactionManager you are using to your bean
    through a bean reference
     */
    @Autowired
    DataSourceTransactionManager txManager;

    public void main(String...args){
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setName("SomeTxName");
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status =txManager.getTransaction(def);
        try {
            //TODO execute your business logic
        }catch (Exception e){
            txManager.rollback(status);
            throw e;
        }
        txManager.commit(status);
    }

}
