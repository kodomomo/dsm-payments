package com.github.kodomo.dsmpayments.domain.user.repository;

import com.github.kodomo.dsmpayments.domain.user.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface DsmPaymentsUserRepository extends CrudRepository<User, Integer> {
}
