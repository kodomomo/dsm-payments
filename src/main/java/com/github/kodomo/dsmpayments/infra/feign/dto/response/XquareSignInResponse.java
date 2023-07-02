package com.github.kodomo.dsmpayments.infra.feign.dto.response;

import com.github.kodomo.dsmpayments.domain.user.entity.xquare.XquareUserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Getter
public class XquareSignInResponse {
    String access_token;
    String refresh_token;
    LocalDateTime expire_at;
    XquareUserRole role;
}
