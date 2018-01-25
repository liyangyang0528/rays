package com.lyyco.rays.service.pattern.proxy;

/**
 * 构建目标对象子类的方式实现代理----Cglib代理
 * 代理的类不能为final,否则报错；
 * 目标对象的方法如果为final/static,那么就不会被拦截,即不会执行目标对象额外的业务方法
 * Created by lyy on 2018/1/25.
 */
public class CglibUserDao {
    public void save(){
        System.out.println("------已保存数据------");
    }
}
