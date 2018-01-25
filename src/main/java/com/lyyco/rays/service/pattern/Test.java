package com.lyyco.rays.service.pattern;

import com.lyyco.rays.service.pattern.decorator.LowerCaseInputStream;
import com.lyyco.rays.service.pattern.factory.NYStylePizzaStore;
import com.lyyco.rays.service.pattern.factory.PizzaStore;
import com.lyyco.rays.service.pattern.proxy.*;

import java.io.*;

/**
 * Created by lyy on 2018/1/23.
 */
public class Test {
    public static void main(String[]args) throws IOException {
//        int c;
//        InputStream in = new LowerCaseInputStream(new BufferedInputStream(new FileInputStream("test.txt")));
//        while((c = in.read())>=0){
//            System.out.println((char)c);
//        }
//        in.close();
//
//        PizzaStore nyPizzaStore = new NYStylePizzaStore();
//        nyPizzaStore.orderPizza("cheese");

        UserDao target = new UserDao();
        System.out.println(target.getClass());
        UserDaoProxy proxy = new UserDaoProxy(target);
        proxy.save();

        //给目标对象创建代理对象
        IUserDao proxys = (IUserDao) new ProxyFactory(target).getProxyInstance();
        //内存中动态生成的代理对象
        System.out.println(proxys.getClass());
        //执行方法-代理对象
        proxys.save();

        //目标对象
        CglibUserDao targets = new CglibUserDao();
        CglibUserDao proxyss = (CglibUserDao) new CglibProxyFactory(targets).getProxyInstance();
        proxyss.save();
    }
}
