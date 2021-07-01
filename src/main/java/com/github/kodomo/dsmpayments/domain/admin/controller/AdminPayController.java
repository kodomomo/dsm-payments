package com.github.kodomo.dsmpayments.domain.admin.controller;

import com.github.kodomo.dsmpayments.domain.admin.service.pay.dto.ValueDTO;
import com.github.kodomo.dsmpayments.domain.admin.service.pay.AdminPayService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/admin/pay")
@RestController
public class AdminPayController {

    private final AdminPayService adminPayService;

    @PostMapping("/user/{userNumber}")
    public void depositToUser(@PathVariable String userNumber, @RequestBody ValueDTO valueDTO) {
        adminPayService.depositToUser(userNumber, valueDTO.getValue());
    }

    @PostMapping("/booth/{boothId}")
    public void depositToBooth(@PathVariable String boothId, @RequestBody ValueDTO valueDTO) {
        adminPayService.depositTOBooth(boothId, valueDTO.getValue());
    }

}
