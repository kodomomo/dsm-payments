package com.github.kodomo.dsmpayments.domain.booth.controller;

import com.github.kodomo.dsmpayments.domain.booth.controller.payload.request.BoothLoginRequest;
import com.github.kodomo.dsmpayments.domain.booth.controller.payload.request.CreateMenuRequest;
import com.github.kodomo.dsmpayments.domain.booth.controller.payload.request.PaymentPermissionRequest;
import com.github.kodomo.dsmpayments.domain.booth.controller.payload.request.PaymentRequest;
import com.github.kodomo.dsmpayments.domain.booth.controller.payload.response.*;
import com.github.kodomo.dsmpayments.domain.booth.entity.Booth;
import com.github.kodomo.dsmpayments.domain.booth.entity.Menu;
import com.github.kodomo.dsmpayments.domain.booth.service.BoothService;
import com.github.kodomo.dsmpayments.domain.receipt.integrate.ReceiptIntegrate;
import com.github.kodomo.dsmpayments.domain.receipt.service.dto.ReceiptDTO;
import com.github.kodomo.dsmpayments.infra.token.JWTRequired;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/booth")
@RestController
public class BoothController {
    private final BoothService boothService;
    private final ReceiptIntegrate receiptIntegrate;


    @JWTRequired
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public GetBoothResponse getBooth(@AuthenticationPrincipal Object boothId) {
        Booth booth = boothService.getBooth((String) boothId);

        return GetBoothResponse.builder()
                .id(booth.getBoothId())
                .name(booth.getBoothName())
                .coin(booth.getCoin())
                .totalCoin(booth.getTotalCoin())
                .numOfUsers(receiptIntegrate.getNumOfUsersUsingBooth(booth))
                .build();
    }

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
    public CreateMenuResponse createMenu(@AuthenticationPrincipal Object boothId, @RequestBody CreateMenuRequest request) {
        System.out.println(boothId);
        Menu menu = boothService.createMenu((String) boothId, request.getName(), request.getPrice());
        return CreateMenuResponse.builder()
                .menuId(menu.getMenuId())
                .name(menu.getName())
                .price(menu.getPrice())
                .boothId(menu.getBoothId())
                .build();
    }

    @GetMapping("/menu")
    @JWTRequired
    @ResponseStatus(HttpStatus.OK)
    public GetMenusResponse getMenus(@AuthenticationPrincipal Object boothId) {
        return GetMenusResponse.of(boothService.getMenus((String) boothId));
    }

    @DeleteMapping("/menu")
    @JWTRequired
    @ResponseStatus(HttpStatus.OK)
    public void deleteMenus(@AuthenticationPrincipal Object boothId, @RequestParam("id") String menuId) {
        boothService.deleteMenu((String) boothId, Integer.parseInt(menuId));
    }

    @GetMapping("/payment")
    @JWTRequired
    @ResponseStatus(HttpStatus.OK)
    public ReceiptsResponse getPayments(
            @AuthenticationPrincipal Object boothId,
            @RequestParam("page") String page,
            @RequestParam("size") String size
    ) {
        Booth booth = boothService.getBooth((String) boothId);
        return ReceiptsResponse.of(receiptIntegrate.findAllForBooth(
                booth, PageRequest.of(Integer.parseInt(page), Integer.parseInt(size), Sort.by("id").descending())));
    }

    @PostMapping("/menu/payment")
    @JWTRequired
    @ResponseStatus(HttpStatus.OK)
    public PaymentResponse payment(@AuthenticationPrincipal Object boothId, @RequestBody PaymentRequest request) {
        ReceiptDTO receipt = boothService.pay((String) boothId, request.getMenuId(), request.getUserUuid());
        return PaymentResponse.builder()
                .boothId(receipt.getBoothEntity().getBoothId())
                .userUuid(receipt.getUserEntity().getUserUuid())
                .requestedCoin(receipt.getRequestValue())
                .tax(receipt.getTax())
                .finalCoin(receipt.getFinalValue())
                .build();
    }

    @PostMapping("/menu/payment/permission")
    @JWTRequired
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void permitPayment(@AuthenticationPrincipal Object boothId, @RequestBody PaymentPermissionRequest request) {
        boothService.permitPayment((String) boothId, request.getUserUuid());
    }
}
