package com.github.kodomo.dsmpayments.domain.receipt.controller;

import com.corundumstudio.socketio.SocketIOClient;
import com.github.kodomo.dsmpayments.domain.receipt.service.dto.ReceiptJoinDTO;
import com.github.kodomo.dsmpayments.infra.config.socket.annotation.SocketController;
import com.github.kodomo.dsmpayments.infra.config.socket.annotation.SocketMapping;

@SocketController
public class ReceiptSocketController {

    @SocketMapping(value = "receipt-join", requestDTO = ReceiptJoinDTO.class)
    public void joinReceiptRoom(SocketIOClient client, ReceiptJoinDTO receiptJoinDTO) {
        client.joinRoom(String.valueOf(receiptJoinDTO.getNumber()));
    }

}
