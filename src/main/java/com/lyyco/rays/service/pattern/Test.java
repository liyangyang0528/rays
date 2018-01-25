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
/*********装饰器模式**********/

//        int c;
//        InputStream in = new LowerCaseInputStream(new BufferedInputStream(new FileInputStream("test.txt")));
//        while((c = in.read())>=0){
//            System.out.println((char)c);
//        }
//        in.close();

/*********工厂模式*************/

//        PizzaStore nyPizzaStore = new NYStylePizzaStore();
//        nyPizzaStore.orderPizza("cheese");
/*********代理模式**************/
        //1.静态代理---目标对象
        UserDao target = new UserDao();
        System.out.println(target.getClass());
        //代理对象---将目标对象传递给代理对象，建立代理关系
        UserDaoProxy proxy = new UserDaoProxy(target);
        System.out.println(proxy.getClass());
        //执行代理的方法
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
