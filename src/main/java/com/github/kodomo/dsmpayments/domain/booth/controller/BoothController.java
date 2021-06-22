package com.github.kodomo.dsmpayments.domain.booth.controller;

import com.github.kodomo.dsmpayments.domain.booth.controller.payload.request.BoothLoginRequest;
import com.github.kodomo.dsmpayments.domain.booth.controller.payload.request.CreateMenuRequest;
import com.github.kodomo.dsmpayments.domain.booth.controller.payload.response.BoothLoginResponse;
import com.github.kodomo.dsmpayments.domain.booth.controller.payload.response.GetMenusResponse;
import com.github.kodomo.dsmpayments.domain.booth.service.BoothService;
import com.github.kodomo.dsmpayments.infra.token.JWTRequired;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/booth")
@RestController
public class BoothController {
    private final BoothService boothService;

    @PostMapping("/auth")
    @ResponseStatus(HttpStatus.OK)
    public BoothLoginResponse login(@RequestBody BoothLoginRequest request) {
        return BoothLoginResponse.builder()
                .accessToken(boothService.login(request.getId(), request.getPassword()))
                .build();
    }

    @PostMapping("/menu")
    @JWTRequired
    @ResponseStatus(HttpStatus.CREATED)
    public void createMenu(@AuthenticationPrincipal Object boothId, @RequestBody CreateMenuRequest request) {
        boothService.createMenu((String) boothId, request.getName(), request.getPrice());
    }

    @GetMapping("/menu")
    @JWTRequired
    @ResponseStatus(HttpStatus.CREATED)
    public GetMenusResponse getMenus(@AuthenticationPrincipal Object boothId) {
        return GetMenusResponse.of(boothService.getMenus((String) boothId));
    }

    @DeleteMapping("/menu")
    @JWTRequired
    @ResponseStatus(HttpStatus.CREATED)
    public void deleteMenus(@AuthenticationPrincipal Object boothId, @RequestParam("menu-id") String menuId) {
        boothService.deleteMenu((String) boothId, Integer.parseInt(menuId));
    }
}
