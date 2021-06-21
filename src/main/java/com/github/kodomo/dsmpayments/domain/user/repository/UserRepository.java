package com.github.kodomo.dsmpayments.domain.user.repository;

import com.github.kodomo.dsmpayments.domain.user.entity.DMSUser;
import com.github.kodomo.dsmpayments.domain.user.entity.User;

import java.util.Optional;

public interface UserRepository {
    public Optional<DMSUser> findDMSUserById(String id);

    public Optional<User> findByUserNumber(Integer userNumber);
}
