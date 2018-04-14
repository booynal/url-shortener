package com.ziv.urlshortener.mysql;

import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MysqlConnectorTest {

    @Test
    public void test() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306";
            String user = "root";
            String password = "root";
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            boolean execute = statement.execute("select 1");
            System.out.println(execute);
            Assert.assertTrue(execute);
            ResultSet resultSet = statement.getResultSet();
            if (resultSet.next()) {
                int r = resultSet.getInt(1);
                System.out.println(r);
                Assert.assertEquals(1, r);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
