package com.ziv.urlshortener.redis;

import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author ziv
 */
@Component
public class RedisProxy {

	private static String redisHost = "localhost";
	private static int redisPort = 6379;
	private static int expireInSec = 3600;

	private JedisPool jedisPool;

	public RedisProxy() {
		jedisPool = new JedisPool(new JedisPoolConfig(), redisHost, redisPort);
	}

	/**
	 * 设置值和过期时间
	 *
	 * @param longUrl
	 * @param shortUrl
	 * @return
	 */
	public String set(String longUrl, String shortUrl) {
		Jedis jedis = jedisPool.getResource();
		try {
			return jedis.setex(longUrl, expireInSec, shortUrl);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			jedisPool.returnResourceObject(jedis);
		}
	}

	/**
	 * 获取并且更新过期时间
	 *
	 * @param longUrl 长URL
	 * @return
	 */
	public String getAndTouch(String longUrl) {
		Jedis jedis = jedisPool.getResource();
		try {
			String s = jedis.get(longUrl);
			if (null != s) {
				jedis.expire(longUrl, expireInSec);
				return s;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jedisPool.returnResourceObject(jedis);
		}
		return null;
	}

	public void close() {
		if (null != jedisPool) {
			try {
				jedisPool.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
