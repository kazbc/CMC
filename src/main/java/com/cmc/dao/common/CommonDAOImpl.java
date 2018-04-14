/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmc.dao.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

/**
 * @Author : KAsun Udayanaga
 * @Document : CommonDAOImpl
 * @Created on : Oct 28, 2017, 7:18:23 PM
 */
@Repository("commonDAO")
public class CommonDAOImpl implements CommonDAO {

    @Autowired
    private DataSource dataSource;

    @Override
    public Map<String, String> getDropdownValueList(String query) throws SQLException {
        return new JdbcTemplate(dataSource).query(query, new ResultSetExtractor<Map>() {
            @Override
            public Map extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                Map<String, String> list = new LinkedHashMap<>();
                list.put("", "-- Select --");
                while (resultSet.next()) {
                    list.put(resultSet.getString("ID"), resultSet.getString("DESCRIPTION"));
                }
                return list;
            }
        });
    }

    @Override
    public Date getCurentTimesStamp() throws Exception {
        
        String sql = "SELECT CURRENT_TIMESTAMP AS CURRENTDATETIME FROM DUAL";

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Date curentdatetime = null;
        try {
            
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                curentdatetime = new Date(resultSet.getTimestamp("CURRENTDATETIME").getTime());
            }
        } catch (Exception exception) {
            throw exception;
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (Exception exception) {
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (Exception exception) {
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception exception) {
                }
            }
        }
        return curentdatetime;
    }

}
