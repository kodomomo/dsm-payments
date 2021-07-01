package com.github.kodomo.dsmpayments.domain.booth.controller.socket;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.github.kodomo.dsmpayments.domain.booth.controller.socket.request.AuthRequest;
import com.github.kodomo.dsmpayments.infra.config.socket.annotation.SocketConnect;
import com.github.kodomo.dsmpayments.infra.config.socket.annotation.SocketController;
import com.github.kodomo.dsmpayments.infra.config.socket.annotation.SocketMapping;
import com.github.kodomo.dsmpayments.infra.token.TokenProvider;
import lombok.RequiredArgsConstructor;

@SocketController
@RequiredArgsConstructor
public class BoothSocketController {
    private final TokenProvider tokenProvider;

    @SocketMapping(value = "auth", requestDTO = AuthRequest.class)
    public void auth(SocketIOServer server, SocketIOClient client, AuthRequest request) {
        if (checkToken(request.getAccessToken())) {
            String boothId = tokenProvider.parseAccessToken(request.getAccessToken());
            client.joinRoom(boothId);
            server.getRoomOperations(boothId).sendEvent(
                    "auth", client.getSessionId() + boothId);
        }
    }

    private Boolean checkToken(String token) {
        return tokenProvider.validateToken(token)
                && tokenProvider.checkTokenType(token, "booth");
    }
}
