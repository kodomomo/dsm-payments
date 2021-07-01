package com.github.kodomo.dsmpayments.domain.user.service;

import com.github.kodomo.dsmpayments.domain.user.entity.User;

public interface UserService {
    public String login(String id, String password);

    public String teacherLogin(String id, String password);

    public User getUser(String userNumber);

    public User getUserByUuid(String userUuid);

    public Integer getNumOfBoothsUsedByUser(String userNumber);
}
