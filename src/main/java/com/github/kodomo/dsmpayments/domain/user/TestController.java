package com.github.kodomo.dsmpayments.domain.user;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.github.kodomo.dsmpayments.infra.config.socket.annotation.SocketConnect;
import com.github.kodomo.dsmpayments.infra.config.socket.annotation.SocketController;
import com.github.kodomo.dsmpayments.infra.config.socket.annotation.SocketMapping;

@SocketController
public class TestController {

    @SocketConnect
    public void connect(SocketIOClient client) {
        System.out.println(client.getSessionId() + " 접속함");
    }

    @SocketMapping(value = "join", requestDTO = TestRequest.class)
    public void joinRoom(SocketIOServer server, SocketIOClient client, TestRequest request) {
        client.joinRoom(request.getRoomName());
        server.getRoomOperations(request.getRoomName()).sendEvent("join", client.getSessionId() + "입장");
    }

}
