package com.ziv.urlshotener.dao;

import com.ziv.urlshotener.dto.UrlAnalyze;
import com.ziv.urlshotener.dto.UrlMapping;
import org.springframework.stereotype.Component;

import java.sql.*;

/**
 * @author ziv
 */
@Component
public class UrlAnalyzeDao {

    public long insertOne(UrlAnalyze dto) throws SQLException {
        Connection connection = DbUtil.getConnection();
        String sql = "INSERT INTO url_analyze(url_id, click_time, click_ip, ext_info) VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, dto.getUrlId());
            preparedStatement.setDate(2, dto.getClickTime());
            preparedStatement.setString(3, dto.getClickIp());
            preparedStatement.setString(4, dto.getExtInfo());
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

    public UrlAnalyze findById(long id) throws SQLException {
        Connection connection = DbUtil.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            String sql = "SELECT * FROM url_analyze WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                long rid = resultSet.getLong(1);
                long urlId = resultSet.getLong(2);
                Date clickTime = resultSet.getDate(3);
                String clickIp = resultSet.getString(4);
                String extInfo = resultSet.getString(5);
                return new UrlAnalyze(rid, urlId, clickTime, clickIp, extInfo);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            DbUtil.close(preparedStatement);
        }
        return null;
    }

}
