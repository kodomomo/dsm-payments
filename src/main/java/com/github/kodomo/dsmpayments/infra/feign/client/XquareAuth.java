package com.github.kodomo.dsmpayments.infra.feign.client;

import com.github.kodomo.dsmpayments.domain.user.entity.XquareUser;
import com.github.kodomo.dsmpayments.infra.feign.dto.response.XquareAuthResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "XquareAuthClient", url = "https://api.xquare.app/account-id")
public interface XquareAuth {

    @GetMapping(value = "/{accountId}")
    XquareUser xquareAuth(@PathVariable ("accountId") String accountId);
}
