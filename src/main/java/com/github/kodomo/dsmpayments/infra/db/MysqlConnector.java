package com.github.kodomo.dsmpayments.infra.db;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Component
public class MysqlConnector {

    @Value("${db.dms.url}")
    private String url;

    @Value("${db.dms.username}")
    private String username;

    @Value("${db.dms.password}")
    private String password;

    private Connection connection;

    private Statement statement;

    @PostConstruct
    public void setup() throws SQLException {
        connection = DataSourceBuilder.create()
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .url(url)
                .username(username)
                .password(password)
                .build()
                .getConnection();
        statement = connection.createStatement();
    }

    public DMSUserEntity findById(String id) throws SQLException {
        checkConnection();
        ResultSet resultSet = statement.executeQuery("select * from student where id = '" + id + "'");

        if (resultSet.next()) {
            return DMSUserEntity.builder()
                    .id(resultSet.getString("id"))
                    .password(resultSet.getString("pw"))
                    .name(resultSet.getString("name"))
                    .number(resultSet.getInt("number"))
                    .email(resultSet.getString("email"))
                    .build();
        }
        return null;
    }

    public void checkConnection() throws SQLException {
        if (connection.isClosed()) {
            setup();
        }
    }
}
