package com.lyyco.rays.service.reflect;


import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;

/**
 * java链式调用
 * com.lyyco.rays.service.Reflect
 *
 * @Author liyangyang
 * 2018/3/26
 */
public class Entity<E> {
    private final static String SET = "set";

    /**
     * 链式set方法，单个属性
     * @param e
     * @param _attr_
     * @param _value_
     * @return
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public E set(E e,String _attr_,Object _value_) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        if(isEmpty(e) || isEmpty(_attr_) || isEmpty(_value_))
            throw new NullPointerException("Entity/_attr_/_value_ isNul.");
        Method method = e.getClass().getMethod(SET +
                StringUtils.capitalize(_attr_),_value_.getClass());
        method.invoke(e,_value_);
        return e;
    }

    /**
     * 链式set方法，多个属性且保证传入两个集合参数个数相同
     * @param e
     * @param _attrs_$attrs_
     * @return
     * @throws Exception
     */
    public E setMultiple(E e, Object[][] _attrs_$attrs_) throws Exception {
        if(isEmpty(_attrs_$attrs_) || _attrs_$attrs_.length != 2 || _attrs_$attrs_[0].length != _attrs_$attrs_[1].length)
            throw new Exception("");
        for (int i = 0; i < _attrs_$attrs_[0].length; i++)
            set(e, String.valueOf(_attrs_$attrs_[0][i]), _attrs_$attrs_[1][i]);

        return e;
    }

    public static <T> String toString(T e) throws IllegalAccessException {
        StringBuilder builder = new StringBuilder(e.getClass().getName()+"-->");
        Field[] fields = e.getClass().getDeclaredFields();
        Field.setAccessible(fields,true);
        for(int i =0; i<fields.length;i++)
            builder.append("{" + fields[i].getName() + "=" + fields[i].get(e).toString() + "}, ");
        return builder.toString();
    }


    private boolean isEmpty(Object object){
        if(object == null) return true;
        if(object instanceof String && object.toString().trim().length() ==0)return true;
        if(object.getClass().isArray() && Array.getLength(object) == 0) return true;
        if(object instanceof Collection && ((Collection<?>) object).isEmpty())return true;
        return object instanceof Map && ((Map<?, ?>) object).isEmpty();
    }
}
