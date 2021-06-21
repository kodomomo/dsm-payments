package com.github.kodomo.dsmpayments.infra.db;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
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

    @PostConstruct
    public void setup() throws SQLException {
        connection = DataSourceBuilder.create()
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .url(url)
                .username(username)
                .password(password)
                .build()
                .getConnection();
    }

    public DMSUserEntity findById(String id) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from student where id = '" + id + "'");

        if (resultSet.next()) {
            return DMSUserEntity.builder()
                    .id(resultSet.getString("id"))
                    .password(resultSet.getString("password"))
                    .name(resultSet.getString("name"))
                    .number(resultSet.getInt("number"))
                    .email(resultSet.getString("email"))
                    .build();
        }

        return null;
    }

    @PreDestroy
    public void destroy() throws SQLException {
        connection.close();
    }

}
