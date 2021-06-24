package com.github.kodomo.dsmpayments.domain.user.repository;

import com.github.kodomo.dsmpayments.domain.user.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DsmPaymentsUserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByUserNumber(Integer userNumber);

    Optional<User> findByUserUuid(String userUuid);
}
