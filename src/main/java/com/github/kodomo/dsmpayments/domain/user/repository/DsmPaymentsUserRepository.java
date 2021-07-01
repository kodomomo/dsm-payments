package com.github.kodomo.dsmpayments.domain.user.repository;

import com.github.kodomo.dsmpayments.domain.user.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface DsmPaymentsUserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByUserNumber(String userNumber);

    Optional<User> findByUserUuid(String userUuid);

    @Query("select user from tbl_user user where user.userName like %?1%")
    List<User> findAllUserStatus(String searchWord);

    @Query(value = "SELECT (sum(coin) / count(*)) as value FROM tbl_user", nativeQuery = true)
    double allUserCoinAverage();
}
