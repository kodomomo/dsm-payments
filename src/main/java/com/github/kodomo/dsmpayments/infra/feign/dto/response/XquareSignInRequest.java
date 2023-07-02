package com.github.kodomo.dsmpayments.infra.feign.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class XquareSignInRequest {
    String account_id;
    String password;
    String device_token;

    public XquareSignInRequest(String accountId, String password) {
        this.account_id = accountId;
        this.password = password;
        this.device_token = "deviceToken";
    }
}
