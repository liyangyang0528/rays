package com.lyyco.rays.service.pattern.proxy;

/**
 * 使用静态代理
 * 需要定义接口或者父类
 * 被代理对象与代理对象一起实现相同的接口或者继承相同的父类
 * 通过调用代理对象的方法调用目标对象
 * Created by lyy on 2018/1/25.
 */
public class UserDaoProxy implements IUserDao {
    //接收目标对象
    private IUserDao target;
    public UserDaoProxy(IUserDao target){
        this.target = target;
    }

    @Override
    public void save() {
        System.out.println("开始事务1---");
        target.save();
        System.out.println("提交事务1---");
    }
}
