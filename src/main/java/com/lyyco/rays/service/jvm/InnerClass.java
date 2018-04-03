package com.lyyco.rays.service.jvm;

import java.util.function.Function;

/**
 * 反编译汇编语言
 * 观察与lambda的不同之处
 */
public class InnerClass{
	Function<Object,String> f = new Function<Object,String>(){
		@Override
		public String apply(Object obj){
			return obj.toString();
		}
	};
}