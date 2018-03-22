package com.lyyco.rays.service.redis;

import redis.clients.jedis.Jedis;

/**
 * Jedis使用测试
 * com.lyyco.rays.service.redis
 *
 * @Author liyangyang
 * 2018/3/22
 */
public class JedisTest {
    public static void main(String...args) {
        Jedis jedds = new Jedis("localhost", 6379);
        jedds.set("foo", "bar");
        String value = jedds.get("foo");
        System.out.println(value);
    }

}
