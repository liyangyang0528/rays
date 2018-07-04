package com.lyyco.rays.service.spring;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * Author liyangyang
 * 2018/7/4
 */
public class TransactionTemplateDemo {
    private final TransactionTemplate transactionTemplate;
    public TransactionTemplateDemo(PlatformTransactionManager transactionManager){
        this.transactionTemplate = new TransactionTemplate(transactionManager);
        this.transactionTemplate.setIsolationLevel(TransactionDefinition.ISOLATION_DEFAULT);
        this.transactionTemplate.setTimeout(100);
    }
    public Object someMethod(){
        return transactionTemplate.execute(new TransactionCallback<Object>() {
            @Override
            public Object doInTransaction(TransactionStatus transactionStatus) {
                /*
                Code within the callback can roll the transaction
                back by calling the setRollbackOnly() method on
the             supplied TransactionStatus object
                 */
                transactionStatus.setRollbackOnly();
                return null;
            }
        });
    }
}
