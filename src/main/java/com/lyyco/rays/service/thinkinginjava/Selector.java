package com.lyyco.rays.service.thinkinginjava;

/**
 * @Description
 * @Author Created by lyy
 * @Date: Created in 14:10 2018/12/11
 */
public interface Selector {
    boolean end();
    Object current();
    void next();
}
