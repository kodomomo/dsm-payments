package com.github.kodomo.dsmpayments.domain.admin.controller;

import com.github.kodomo.dsmpayments.domain.receipt.integrate.ReceiptIntegrate;
import com.github.kodomo.dsmpayments.domain.receipt.service.dto.ReceiptDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/admin/receipt")
@RestController
public class AdminReceiptController {

    private final ReceiptIntegrate receiptIntegrate;

    @GetMapping
    public List<ReceiptDTO> findReceipts(@RequestParam("search") String searchWord) {
        return receiptIntegrate.findAllByQuery(searchWord);
    }

}
