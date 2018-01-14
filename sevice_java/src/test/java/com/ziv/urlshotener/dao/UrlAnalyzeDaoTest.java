package com.ziv.urlshotener.dao;

import com.ziv.urlshotener.dto.UrlAnalyze;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class UrlAnalyzeDaoTest {

    private UrlAnalyzeDao urlAnalyzeDao;

    @Before
    public void setUp() throws Exception {
        urlAnalyzeDao = new UrlAnalyzeDao();
    }

    @After
    public void tearDown() throws Exception {
        DbUtil.close(DbUtil.getConnection());
    }

    @Test
    public void insertOne() throws SQLException {
        UrlAnalyze dto = new UrlAnalyze();
        dto.setUrlId(1);
        dto.setClickTime(new Date(System.currentTimeMillis()));
        dto.setClickIp("10.155.23.56");
        dto.setExtInfo("extInfo");
        long l = urlAnalyzeDao.insertOne(dto);
        System.out.println(l);
        Assert.assertTrue(l > 0);
    }

    @Test
    public void findById() throws SQLException {
        UrlAnalyze byId = urlAnalyzeDao.findById(1);
        System.out.println(byId);
        Assert.assertEquals(1, byId.getId());
    }
}