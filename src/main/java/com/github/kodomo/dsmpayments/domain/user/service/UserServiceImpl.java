package com.github.kodomo.dsmpayments.domain.user.service;

import com.github.kodomo.dsmpayments.domain.receipt.integrate.ReceiptIntegrate;
import com.github.kodomo.dsmpayments.domain.user.entity.DMSUser;
import com.github.kodomo.dsmpayments.domain.user.entity.Teacher;
import com.github.kodomo.dsmpayments.domain.user.entity.User;
import com.github.kodomo.dsmpayments.domain.user.exception.LoginFailedException;
import com.github.kodomo.dsmpayments.domain.user.exception.UserNotFoundException;
import com.github.kodomo.dsmpayments.domain.user.repository.TeacherRepository;
import com.github.kodomo.dsmpayments.domain.user.repository.UserRepository;
import com.github.kodomo.dsmpayments.infra.token.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final TeacherRepository teacherRepository;
    private final ReceiptIntegrate receiptIntegrate;

    private final TokenProvider tokenProvider;

    @Override
    public String login(String id, String password) {
        DMSUser user = userRepository.findDMSUserById(id)
                .orElseThrow(LoginFailedException::new);

        if (!user.checkPassword(password)) { throw new LoginFailedException(); }

        return tokenProvider.generateAccessToken(String.valueOf(user.getNumber()), "user");
    }

    @Override
    public String teacherLogin(String id, String password) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(LoginFailedException::new);

        if (!teacher.checkPassword(password)) { throw new LoginFailedException(); }

        return tokenProvider.generateAccessToken(String.valueOf(teacher.getNumber()), "user");
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
