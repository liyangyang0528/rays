package com.lyyco.rays.service.jvm;

import java.util.function.Function;

/**
 * 反编译汇编语言
 * 观察与匿名类的不同之处
 */
public class Lambda{
	Function<Object,String> f = obj -> obj.toString();
}