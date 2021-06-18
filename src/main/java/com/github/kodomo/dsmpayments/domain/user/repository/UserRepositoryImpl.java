package com.github.kodomo.dsmpayments.domain.user.repository;

import com.github.kodomo.dsmpayments.domain.user.entity.DMSUser;
import com.github.kodomo.dsmpayments.domain.user.exception.SqlErrorException;
import com.github.kodomo.dsmpayments.infra.db.MysqlConnector;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements  UserRepository {
    private final MysqlConnector mysqlConnector;

    @Override
    public Optional<DMSUser> findDMSUserById(String id) {
        try {
            return DMSUser.map(mysqlConnector.findById(id));
        } catch (SQLException sqlException) {
            throw new SqlErrorException();
        }
    }
}
