package com.github.kodomo.dsmpayments.domain.admin.controller;

import com.github.kodomo.dsmpayments.domain.admin.service.main.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/admin")
@RestController
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/auth")
    public void auth(@RequestHeader("Authorization") String password) {
        adminService.auth(password);
    }

}
