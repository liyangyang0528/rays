package com.lyyco.rays.service.reflect;

import java.util.HashMap;

public class UtilHashMap<K, V> extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    /**
     * Map 前置put方法
     *
     * @param _key_
     * @param _value_
     * @return
     */
    public UtilHashMap<K, V> _put(String _key_, Object _value_) {
        put(_key_, _value_);
        return this;
    }
}
