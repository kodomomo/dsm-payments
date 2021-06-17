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

    private Connection connection;

    @PostConstruct
    public void setup() throws SQLException {
        connection = DataSourceBuilder.create()
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .url(url)
                .build().getConnection();
    }

    public DMSUserEntity findById(String id) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from user where id = " + id);

        if (resultSet.next()) {
            return DMSUserEntity.builder()
                    .id(resultSet.getString("id"))
                    .password(resultSet.getString("password"))
                    .build();
        }

        return null;
    }

}
