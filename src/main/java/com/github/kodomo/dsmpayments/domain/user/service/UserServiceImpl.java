package com.github.kodomo.dsmpayments.domain.user.service;

import com.github.kodomo.dsmpayments.domain.receipt.integrate.ReceiptIntegrate;
import com.github.kodomo.dsmpayments.domain.user.controller.payload.request.UserLoginRequest;
import com.github.kodomo.dsmpayments.domain.user.controller.payload.response.UserLoginResponse;
import com.github.kodomo.dsmpayments.domain.user.entity.DMSUser;
import com.github.kodomo.dsmpayments.domain.user.entity.Teacher;
import com.github.kodomo.dsmpayments.domain.user.entity.User;
import com.github.kodomo.dsmpayments.domain.user.entity.XquareUser;
import com.github.kodomo.dsmpayments.domain.user.exception.LoginFailedException;
import com.github.kodomo.dsmpayments.domain.user.exception.UserNotFoundException;
import com.github.kodomo.dsmpayments.domain.user.repository.TeacherRepository;
import com.github.kodomo.dsmpayments.domain.user.repository.UserRepository;
import com.github.kodomo.dsmpayments.infra.feign.client.XquareAuth;
import com.github.kodomo.dsmpayments.infra.token.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.bouncycastle.openssl.PasswordException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final TeacherRepository teacherRepository;
    private final ReceiptIntegrate receiptIntegrate;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final XquareAuth xquareAuth;

    @Override
    public String login(UserLoginRequest request) {
        XquareUser xquareUser = xquareAuth.xquareAuth(request.getAccountId());

        if (!passwordEncoder.matches(request.getPassword(), xquareUser.getPassword())) {
            throw new LoginFailedException();
        }

        return tokenProvider.generateAccessToken(String.valueOf(xquareUser.getId()), "user");
    }

    @Override
    public String teacherLogin(String id, String password) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(LoginFailedException::new);

        if (!teacher.checkPassword(password)) { throw new LoginFailedException(); }

        return tokenProvider.generateAccessToken(String.valueOf(teacher.getNumber()), "user");
    }

    @Override
    public User getUser(String userNumber) {
        return userRepository.findByUserNumber(userNumber)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public User getUserByUuid(String userUuid) {
        return userRepository.findByUserUuid(userUuid)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public Integer getNumOfBoothsUsedByUser(String userNumber) {
        User user = userRepository.findByUserNumber(userNumber).orElseThrow(UserNotFoundException::new);
        return receiptIntegrate.getNumOfBoothsUsedByUser(user);
    }
}
