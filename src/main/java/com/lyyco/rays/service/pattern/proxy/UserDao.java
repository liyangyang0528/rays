package com.lyyco.rays.service.pattern.proxy;

/**
 * Created by lyy on 2018/1/25.
 */
public class UserDao implements IUserDao{
    @Override
    public void save() {
        System.out.println("-----已保存数据-----");
    }
}
