package com.github.kodomo.dsmpayments.infra.feign.client;

import com.github.kodomo.dsmpayments.domain.user.entity.xquare.XquareUser;
import com.github.kodomo.dsmpayments.infra.feign.FeignConfig;
import com.github.kodomo.dsmpayments.infra.feign.dto.response.XquareSignInRequest;
import com.github.kodomo.dsmpayments.infra.feign.dto.response.XquareSignInResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;


@FeignClient(name = "XquareAuthClient", url = "${xquare.base-url}")
public interface XquareAPI {

    @PostMapping(value = "${xquare.signin-suffix}", produces = "application/json")
    XquareSignInResponse signIn(@RequestBody XquareSignInRequest request);

    @GetMapping(value = "${xquare.getuser-suffix}", produces = "application/json")
    XquareUser getUser(@RequestHeader("Authorization") String accessToken, @PathVariable("accountId") String accountId);
}
