package com.github.kodomo.dsmpayments.domain.user.service;

import com.github.kodomo.dsmpayments.domain.receipt.integrate.ReceiptIntegrate;
import com.github.kodomo.dsmpayments.domain.user.entity.DMSUser;
import com.github.kodomo.dsmpayments.domain.user.entity.User;
import com.github.kodomo.dsmpayments.domain.user.exception.LoginFailedException;
import com.github.kodomo.dsmpayments.domain.user.exception.UserNotFoundException;
import com.github.kodomo.dsmpayments.domain.user.repository.UserRepository;
import com.github.kodomo.dsmpayments.infra.token.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ReceiptIntegrate receiptIntegrate;

    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    @Override
    public String login(String id, String password) {
        DMSUser user = userRepository.findDMSUserById(id)
                .orElseThrow(LoginFailedException::new);

        if (!passwordEncoder.matches(password, user.getPassword())) { throw new LoginFailedException(); }

        return tokenProvider.generateAccessToken(String.valueOf(user.getNumber()), "user");
    }

    @Override
    public User getUser(Integer userNumber) {
        return userRepository.findByUserNumber(userNumber)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public User getUserByUuid(String userUuid) {
        return userRepository.findByUserUuid(userUuid)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public Integer getNumOfBoothsUsedByUser(Integer userNumber) {
        User user = userRepository.findByUserNumber(userNumber).orElseThrow(UserNotFoundException::new);
        return receiptIntegrate.getNumOfBoothsUsedByUser(user);
    }
}
