package com.ziv.urlshortener.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbUtil {

	private static final String driverName = "com.mysql.jdbc.Driver";
	private static final String url = "jdbc:mysql://localhost:3306/url";
	private static final String user = "root";
	private static final String pass = "root";

	private static Connection connection;

	public static Connection getConnection() {
		if (null == connection) {
			synchronized (DbUtil.class) {
				if (null == connection) {
					try {
						Class.forName(driverName);
						connection = DriverManager.getConnection(url, user, pass);
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
			}
		}
		return connection;
	}

	public static void close(AutoCloseable... closeables) {
		if (null != closeables) {
			for (AutoCloseable closeable : closeables) {
				if (null != closeable) {
					try {
						closeable.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
