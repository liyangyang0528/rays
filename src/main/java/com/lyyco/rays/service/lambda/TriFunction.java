package com.lyyco.rays.service.lambda;

/**
 * for a three-argument constructor reference
 * Created by lyy on 2018/2/23.
 */
public interface TriFunction<T,U,V,R> {
    R apply(T t,U u,V v);
}
