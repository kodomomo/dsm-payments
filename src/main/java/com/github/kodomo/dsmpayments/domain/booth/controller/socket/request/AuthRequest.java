package com.github.kodomo.dsmpayments.domain.booth.controller.socket.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequest {
    private String accessToken;
}
