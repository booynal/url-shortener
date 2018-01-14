package com.ziv.urlshortener.jedis;

import org.junit.Assert;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisConnectorTest {

    String host = "localhost";
    int port = 6379;

    @Test
    public void test() {
        Jedis jedis = new Jedis(host, port);
        Long dbSize = jedis.dbSize();
        System.out.println(dbSize);

        jedis.set("a", "b");
        String get = jedis.get("a");
        System.out.println(get);
        Assert.assertEquals("b", get);
    }

    @Test
    public void test_pool() {
        JedisPool jedisPool = new JedisPool(new JedisPoolConfig(), host, port);
        Jedis jedis = jedisPool.getResource();
        Long dbSize = jedis.dbSize();
        System.out.println("dbSize: " + dbSize);
        long maxBorrowWaitTimeMillis = jedisPool.getMaxBorrowWaitTimeMillis();
        System.out.println("maxBorrowWaitTimeMillis: " + maxBorrowWaitTimeMillis);
        long meanBorrowWaitTimeMillis = jedisPool.getMeanBorrowWaitTimeMillis();
        System.out.println("meanBorrowWaitTimeMillis: " + meanBorrowWaitTimeMillis);
        int numActive = jedisPool.getNumActive();
        System.out.println("numActive: " + numActive);
        int numIdle = jedisPool.getNumIdle();
        System.out.println("numIdle: " + numIdle);
        int numWaiters = jedisPool.getNumWaiters();
        System.out.println("numWaiters: " + numWaiters);
    }

}
