package com.lyyco.rays.service.reflect;

import java.util.ArrayList;

/**
 * com.lyyco.rays.service.Reflect
 *
 * @Author liyangyang
 * 2018/3/26
 */
public class UtilArrayList<E> extends ArrayList<Object> {
    private static final long serialVersionUID = 1L;

    /**
     * List 前置add方法
     * @param e
     * @return
     */
    public UtilArrayList<E> _add(Object e){
        add(e);
        return this;
    }
}

