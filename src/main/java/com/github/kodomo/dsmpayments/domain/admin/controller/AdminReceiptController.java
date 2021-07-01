package com.github.kodomo.dsmpayments.domain.admin.controller;

import com.github.kodomo.dsmpayments.domain.receipt.integrate.ReceiptIntegrate;
import com.github.kodomo.dsmpayments.domain.receipt.service.dto.ReceiptPageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/admin/receipt")
@RestController
public class AdminReceiptController {

    private final ReceiptIntegrate receiptIntegrate;

    @GetMapping
    public ReceiptPageDTO findReceipts(@RequestParam(value = "search", defaultValue = "") String searchWord, Pageable pageable) {
        return receiptIntegrate.findAllByQuery(searchWord, pageable);
    }

}
