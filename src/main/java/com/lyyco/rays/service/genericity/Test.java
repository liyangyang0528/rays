package com.lyyco.rays.service.genericity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lyy on 2018/1/2.
 */
public class Test {
    /*
    使用原生类型---运行报错
    使用List<Object>编译就会报错
     */
    private static void unsafeAdd(List list, Object o){
        list.add(o);
        //数组是协变的
        Object[] objectArray = new Long[1];
        objectArray[0] = "I dont fit in";
        //List不是协变的--编译时就会报错
        //List<Object> o1 = new ArrayList<Long>();
        //o1.add("I dont fit in");
    }
    public static void main(String[]args){
        List<String> strings = new ArrayList<String>();
        unsafeAdd(strings,new Integer(32));
        String s = strings.get(0);
    }
}
