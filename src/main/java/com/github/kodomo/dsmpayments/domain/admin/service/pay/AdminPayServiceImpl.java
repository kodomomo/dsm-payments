package com.github.kodomo.dsmpayments.domain.admin.service.pay;

import com.github.kodomo.dsmpayments.domain.booth.entity.Booth;
import com.github.kodomo.dsmpayments.domain.booth.exception.BoothNotFoundException;
import com.github.kodomo.dsmpayments.domain.booth.repository.BoothRepository;
import com.github.kodomo.dsmpayments.domain.receipt.entity.ReceiptSender;
import com.github.kodomo.dsmpayments.domain.receipt.integrate.ReceiptIntegrate;
import com.github.kodomo.dsmpayments.domain.receipt.service.dto.ReceiptDTO;
import com.github.kodomo.dsmpayments.domain.user.entity.User;
import com.github.kodomo.dsmpayments.domain.user.exception.UserNotFoundException;
import com.github.kodomo.dsmpayments.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AdminPayServiceImpl implements AdminPayService {

    private final ReceiptIntegrate receiptIntegrate;
    private final UserRepository userRepository;
    private final BoothRepository boothRepository;

    @Override
    public void depositToUser(String userNumber, int value) {
        User user = userRepository.findByUserNumber(userNumber).orElseThrow(UserNotFoundException::new);
        ReceiptDTO resultDTO = registerReceipt(user, null, value);

        userRepository.save(user.giveCoin(resultDTO.getFinalValue()));
    }

    @Override
    public void depositTOBooth(String boothId, int value) {
        Booth booth = boothRepository.findById(boothId).orElseThrow(BoothNotFoundException::new);
        ReceiptDTO resultDTO = registerReceipt(null, booth, value);
        int finalValue = resultDTO.getFinalValue();
        if (finalValue > 0) booth.giveCoin(finalValue);
        else booth.takeCoin(-1 * finalValue);

        boothRepository.save(booth);
    }

    private ReceiptDTO registerReceipt(User user, Booth booth, int value) {
        ReceiptDTO requestDTO = new ReceiptDTO(user, booth, value, ReceiptSender.ADMIN);
        return receiptIntegrate.registerReceipt(requestDTO);
    }

}
