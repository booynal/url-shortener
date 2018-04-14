package com.ziv.urlshortener.redis;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RedisProxyTest {

    RedisProxy redisProxy;

    @Before
    public void setUp() throws Exception {
        redisProxy = new RedisProxy();
    }

    @After
    public void tearDown() throws Exception {
        redisProxy.close();
    }

    @Test
    public void set() {
        String l = "http://www.baidu.com";
        String s = "1";
        String set = redisProxy.set(l, s);
        System.out.println(set);
    }

    @Test
    public void get() {
        String s = redisProxy.getAndTouch("http://www.baidu.com");
        System.out.println(s);
    }
}
