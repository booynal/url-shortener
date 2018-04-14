package com.ziv.urlshortener.service;

import com.ziv.urlshortener.dao.UrlAnalyzeDao;
import com.ziv.urlshortener.dao.UrlMappingDao;
import com.ziv.urlshortener.dto.UrlAnalyze;
import com.ziv.urlshortener.dto.UrlMapping;
import com.ziv.urlshortener.number.NumberUtil;
import com.ziv.urlshortener.redis.RedisProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.SQLException;

@Service
public class UrlShortenerService {

	@Autowired
	private UrlMappingDao urlMappingDao;
	@Autowired
	private UrlAnalyzeDao urlAnalyzeDao;
	@Autowired
	private RedisProxy redisProxy;

	public String long2short(String longUrl) {
		if (null == longUrl || longUrl.isEmpty()) {
			return null;
		}

		// 查询缓存
		// 如果命中，则更新过期时间并返回
		// 否则插入数据库，并放入缓存
		String shortCode = redisProxy.getAndTouch(longUrl);
		if (null == shortCode) {
			UrlMapping urlMapping = new UrlMapping();
			urlMapping.setLongUrl(longUrl);
			urlMapping.setCreateTime(new Date(System.currentTimeMillis()));
			try {
				shortCode = NumberUtil.convertDecimalTo62(urlMappingDao.insertOne(urlMapping));
				redisProxy.set(longUrl, shortCode);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return shortCode;
	}

	/**
	 * 将短url的path部分转成长URL
	 *
	 * @param shortCode 短URL的path部分，即不含域名
	 * @return
	 */
	public String short2long(String shortCode, String fromIp) {
		if (null == shortCode || shortCode.isEmpty()) {
			return null;
		}

		long shortId = NumberUtil.convert62ToDecimal(shortCode);
		try {
			UrlMapping byId = urlMappingDao.findById(shortId);
			if (null != byId) {
				UrlAnalyze dto = new UrlAnalyze();
				dto.setUrlId(shortId);
				dto.setClickIp(fromIp);
				dto.setClickTime(new Date(System.currentTimeMillis()));
				long l = urlAnalyzeDao.insertOne(dto);
				System.out.println(String.format("ip(%s)访问了地址(%s->%s)，访问id为: '%s'", fromIp, shortCode, byId.getLongUrl(), l));
				return byId.getLongUrl();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
