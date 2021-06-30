package com.github.kodomo.dsmpayments.domain.user.service;

import com.github.kodomo.dsmpayments.domain.user.entity.User;

public interface UserService {
    public String login(String id, String password);

    public String teacherLogin(String id, String password);

    public User getUser(Integer userNumber);

    public User getUserByUuid(String userUuid);

    public Integer getNumOfBoothsUsedByUser(Integer userNumber);
}
