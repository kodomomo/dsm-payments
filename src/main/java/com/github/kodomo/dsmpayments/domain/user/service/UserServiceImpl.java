package com.github.kodomo.dsmpayments.domain.user.service;

import com.github.kodomo.dsmpayments.domain.user.entity.DMSUser;
import com.github.kodomo.dsmpayments.domain.user.exception.LoginFailedException;
import com.github.kodomo.dsmpayments.domain.user.repository.UserRepository;
import com.github.kodomo.dsmpayments.infra.token.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final TokenProvider tokenProvider;

    @Override
    public String login(String id, String password) {
        DMSUser user = userRepository.findDMSUserById(id)
                .orElseThrow(LoginFailedException::new);

        System.out.println(user.getPassword());

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }

        if (!user.getPassword().equals(password)) { throw new LoginFailedException(); }

        return tokenProvider.generateAccessToken(user.getNumber(), "user");
    }
}
