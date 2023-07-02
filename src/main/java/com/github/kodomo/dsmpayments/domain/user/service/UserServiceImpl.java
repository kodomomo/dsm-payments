package com.github.kodomo.dsmpayments.domain.user.service;

import com.github.kodomo.dsmpayments.domain.receipt.integrate.ReceiptIntegrate;
import com.github.kodomo.dsmpayments.domain.user.controller.payload.request.UserLoginRequest;
import com.github.kodomo.dsmpayments.domain.user.entity.Teacher;
import com.github.kodomo.dsmpayments.domain.user.entity.User;
import com.github.kodomo.dsmpayments.domain.user.entity.xquare.XquareUser;
import com.github.kodomo.dsmpayments.domain.user.exception.LoginFailedException;
import com.github.kodomo.dsmpayments.domain.user.exception.UserNotFoundException;
import com.github.kodomo.dsmpayments.domain.user.repository.TeacherRepository;
import com.github.kodomo.dsmpayments.domain.user.repository.UserRepository;
import com.github.kodomo.dsmpayments.infra.feign.client.XquareAPI;
import com.github.kodomo.dsmpayments.infra.feign.dto.response.XquareSignInRequest;
import com.github.kodomo.dsmpayments.infra.feign.dto.response.XquareSignInResponse;
import com.github.kodomo.dsmpayments.infra.token.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final TeacherRepository teacherRepository;
    private final ReceiptIntegrate receiptIntegrate;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final XquareAPI xquareAPI;

    @Override
    public String login(UserLoginRequest request) {
        XquareSignInResponse response = xquareAPI.signIn(
                new XquareSignInRequest(request.getAccountId(), request.getPassword())
        );
        XquareUser xquareUser = xquareAPI.getUser(response.getAccess_token(), request.getAccountId());

        if (!passwordEncoder.matches(request.getPassword(), xquareUser.getPassword())) {
            throw new LoginFailedException();
        }

        return tokenProvider.generateAccessToken(xquareUser.getStrGradeClassNumber(), "user");
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
