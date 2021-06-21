package com.github.kodomo.dsmpayments.domain.admin.service;

import com.github.kodomo.dsmpayments.infra.exception.GlobalException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Value("${admin.password}")
    private String adminPassword;

    @Override
    public void auth(String password) {
        if (!adminPassword.equals(password)) {
            throw new GlobalException(403, "UnAuthenticated");
        }
    }

}
