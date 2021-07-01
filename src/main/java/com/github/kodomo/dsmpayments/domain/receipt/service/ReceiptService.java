package com.github.kodomo.dsmpayments.domain.receipt.service;

import com.corundumstudio.socketio.SocketIOServer;
import com.github.kodomo.dsmpayments.domain.receipt.entity.Receipt;
import com.github.kodomo.dsmpayments.domain.receipt.integrate.ReceiptIntegrate;
import com.github.kodomo.dsmpayments.domain.receipt.repository.ReceiptJDBCRepository;
import com.github.kodomo.dsmpayments.domain.receipt.repository.ReceiptRepository;
import com.github.kodomo.dsmpayments.domain.receipt.service.dto.ReceiptDTO;
import com.github.kodomo.dsmpayments.domain.receipt.service.dto.ReceiptPageDTO;
import com.github.kodomo.dsmpayments.domain.booth.entity.Booth;
import com.github.kodomo.dsmpayments.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReceiptService implements ReceiptIntegrate {

    private final ReceiptRepository repository;
    private final ReceiptJDBCRepository receiptJDBCRepository;

    private final SocketIOServer socketIOServer;

    @Override
    public ReceiptDTO registerReceipt(ReceiptDTO receiptDTO) {
        Receipt receipt = repository.save(Receipt.of(receiptDTO));
        User user = receiptDTO.getUserEntity();

        if (user != null) {
            String userId = user.getUserNumber().toString();
            socketIOServer.getRoomOperations(userId)
                    .sendEvent("pay-successful", ReceiptDTO.of(receipt));
        }

        return ReceiptDTO.of(receipt);
    }

    @Override
    public ReceiptPageDTO findAllByQuery(String query, Pageable pageable) {
        Page<Receipt> receipts = repository.findAllByQuery("%" + query + "%", pageable);
        return ReceiptPageDTO.of(receipts);
    }

    @Override
    public List<ReceiptDTO> findAllForUser(User user) {
        return repository.findAllByUser(user)
                .stream().map(ReceiptDTO::of)
                .collect(Collectors.toList());
    }

    @Override
    public ReceiptPageDTO findAllForBooth(Booth booth, Pageable pageable) {
        Page<Receipt> receiptPages = repository.findAllByBooth(booth, pageable);
        return ReceiptPageDTO.of(receiptPages);
    }

    @Override
    public Integer getNumOfBoothsUsedByUser(User user) {
        return repository.countBoothsUsedByUser(user);
    }

    @Override
    public Integer getNumOfUsersUsingBooth(Booth booth) {
        return repository.countUserByBooth(booth);
    }

    @Override
    public List<Long> userCoinUseOfHour() {
        return repository.userCoinUseOfHour();
    }

    @Override
    public List<Long> boothCoinIncomeOfHour() {
        return receiptJDBCRepository.boothCoinIncomeOfHour();
    }

    @Override
    public List<Long> boothCoinExpensesOfHour() {
        return receiptJDBCRepository.boothCoinExpensesOfHour();
    }

}
