package com.ziv.urlshortener.dao;

import com.ziv.urlshortener.dto.UrlMapping;
import org.springframework.stereotype.Component;

import java.sql.*;

/**
 * @author ziv
 */
@Component
public class UrlMappingDao {

	public long insertOne(UrlMapping dto) throws SQLException {
		Connection connection = DbUtil.getConnection();
		String sql = "INSERT INTO url_mapping(long_url, create_time, expired_time) VALUES (?, ?, ?)";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, dto.getLongUrl());
			preparedStatement.setDate(2, dto.getCreateTime());
			preparedStatement.setDate(3, dto.getExpireTime());
			preparedStatement.executeUpdate();
			ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
			if (generatedKeys.next()) {
				return generatedKeys.getLong(1);
			} else {
				throw new RuntimeException("没有找到主键:" + dto);
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			DbUtil.close(preparedStatement);
		}
	}

	public UrlMapping findById(long id) throws SQLException {
		Connection connection = DbUtil.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			String sql = "SELECT * FROM url_mapping WHERE id = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return new UrlMapping(resultSet.getLong(1), resultSet.getString(2), resultSet.getDate(3), resultSet.getDate(4));
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			DbUtil.close(preparedStatement);
		}
		return null;
	}

}
