package com.lyyco.rays.service.pattern.proxy;

/**
 * Created by lyy on 2018/1/25.
 */
public class UserDaoProxy implements IUserDao {
    private IUserDao target;
    public UserDaoProxy(IUserDao target){
        this.target = target;
    }

    @Override
    public void save() {
        System.out.println("开始事务---");
        target.save();
        System.out.println("提交事务---");
    }
}
