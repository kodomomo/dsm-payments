package com.github.kodomo.dsmpayments.domain.admin.controller;

import com.github.kodomo.dsmpayments.domain.admin.service.status.AdminStatusService;
import com.github.kodomo.dsmpayments.domain.admin.service.status.dto.BoothStatus;
import com.github.kodomo.dsmpayments.domain.admin.service.status.dto.CoinStatus;
import com.github.kodomo.dsmpayments.domain.admin.service.status.dto.UserStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/admin/status")
@RestController
public class AdminStatusController {

    private final AdminStatusService adminStatusService;

    @GetMapping
    public CoinStatus getAllStatus() {
        return adminStatusService.getAllStatus();
    }

    @GetMapping("/user")
    public List<UserStatus> findAllUserStatus(@RequestParam(value = "search", defaultValue = "") String searchWord) {
        return adminStatusService.findAllUserStatus(searchWord);
    }

    @GetMapping("/booth")
    public List<BoothStatus> findAllBoothStatus(@RequestParam(value = "search", defaultValue = "") String searchWord) {
        return adminStatusService.findAllBoothStatus(searchWord);
    }

}
