package com.github.kodomo.dsmpayments.domain.user.controller;

import com.github.kodomo.dsmpayments.domain.user.controller.payload.request.UserLoginRequest;
import com.github.kodomo.dsmpayments.domain.user.controller.payload.response.GetUserResponse;
import com.github.kodomo.dsmpayments.domain.user.controller.payload.response.UserLoginResponse;
import com.github.kodomo.dsmpayments.domain.user.controller.payload.response.UserResponse;
import com.github.kodomo.dsmpayments.domain.user.entity.User;
import com.github.kodomo.dsmpayments.domain.user.service.UserService;
import com.github.kodomo.dsmpayments.infra.token.JWTRequired;
import com.github.kodomo.dsmpayments.infra.token.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/auth")
    @ResponseStatus(HttpStatus.OK)
    public UserLoginResponse login(@RequestBody UserLoginRequest request) {
        return UserLoginResponse.builder()
                .accessToken(userService.login((request)))
                .build();
    }

    @PostMapping("/auth/teacher")
    @ResponseStatus(HttpStatus.OK)
    public UserLoginResponse teacherLogin(@RequestBody UserLoginRequest request) {
        return UserLoginResponse.builder()
                .accessToken(userService.teacherLogin(request.getId(), request.getPassword()))
                .build();
    }

    @JWTRequired
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public UserResponse getUser(@AuthenticationPrincipal Object number) {
        User user = userService.getUser((String) number);
        return UserResponse.builder()
                .uuid(user.getUserUuid())
                .name(user.getUserName())
                .number(user.getUserNumber())
                .coin(user.getCoin())
                .countOfUsedBooth(userService.getNumOfBoothsUsedByUser((String) number))
                .build();
    }

    @JWTRequired
    @GetMapping("/booth")
    @ResponseStatus(HttpStatus.OK)
    public GetUserResponse getUserByUuid(@AuthenticationPrincipal Object boothId, @RequestParam("uuid") String uuid) {
        User user = userService.getUserByUuid(uuid);
        return GetUserResponse.builder()
                .uuid(user.getUserUuid())
                .name(user.getUserName())
                .number(user.getUserNumber())
                .coin(user.getCoin())
                .build();
    }
}
