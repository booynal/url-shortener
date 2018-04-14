package com.ziv.urlshortener.dao;

import com.ziv.urlshortener.dto.UrlMapping;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Date;
import java.sql.SQLException;

public class UrlMappingDaoTest {

	@Test
	public void test_insert() throws SQLException {
		UrlMappingDao urlMappingDao = new UrlMappingDao();
		String longUrl = "http://www.baidu.com/abc";
		UrlMapping dto = new UrlMapping(longUrl, new Date(System.currentTimeMillis()), null);
		long l = urlMappingDao.insertOne(dto);
		System.out.println("key: " + l);
		Assert.assertTrue(l > 0);
	}

	@Test
	public void test_find() throws SQLException {
		UrlMappingDao urlMappingDao = new UrlMappingDao();
		UrlMapping byId = urlMappingDao.findById(1);
		System.out.println(byId);
		Assert.assertEquals(1, byId.getId());
	}
}
