package com.github.kodomo.dsmpayments.domain.receipt.service;

import com.corundumstudio.socketio.SocketIOServer;
import com.github.kodomo.dsmpayments.domain.receipt.entity.Receipt;
import com.github.kodomo.dsmpayments.domain.receipt.integrate.ReceiptIntegrate;
import com.github.kodomo.dsmpayments.domain.receipt.repository.ReceiptRepository;
import com.github.kodomo.dsmpayments.domain.receipt.service.dto.ReceiptDTO;
import com.github.kodomo.dsmpayments.domain.receipt.service.dto.ReceiptPageDTO;
import com.github.kodomo.dsmpayments.domain.booth.entity.Booth;
import com.github.kodomo.dsmpayments.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReceiptService implements ReceiptIntegrate {

    private final ReceiptRepository repository;

    private final SocketIOServer socketIOServer;

    @Override
    public void registerReceipt(ReceiptDTO receiptDTO) {
        Receipt receipt = repository.save(Receipt.of(receiptDTO));
        User user = receiptDTO.getUser();

        if (user != null) {
            String userId = user.getUserNumber().toString();
            socketIOServer.getRoomOperations(userId)
                    .sendEvent("receipt", ReceiptDTO.of(receipt));
        }
    }

    @Override
    public ReceiptPageDTO findAll(Pageable pageable) {
        Page<Receipt> receiptPages = repository.findAll(pageable);
        return ReceiptPageDTO.of(receiptPages);
    }

    @Override
    public ReceiptPageDTO findAllByQuery(String query, Pageable pageable) {
        Page<Receipt> receiptPages = repository.findAllByQuery(query, pageable);
        return ReceiptPageDTO.of(receiptPages);
    }

    @Override
    public ReceiptPageDTO findAllForUser(User user, Pageable pageable) {
        Page<Receipt> receiptPages = repository.findAllByUser(user, pageable);
        return ReceiptPageDTO.of(receiptPages);
    }

    @Override
    public ReceiptPageDTO findAllForSeller(Booth booth, Pageable pageable) {
        Page<Receipt> receiptPages = repository.findAllByBooth(booth, pageable);
        return ReceiptPageDTO.of(receiptPages);
    }

}
