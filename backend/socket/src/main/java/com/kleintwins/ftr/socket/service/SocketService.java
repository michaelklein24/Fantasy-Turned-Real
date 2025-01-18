package com.kleintwins.ftr.socket.service;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SocketService {

    private final SimpMessagingTemplate messagingTemplate;

    public void sendNotificationToUser(String userId, Object payload) {
        sendToUser(userId, "/notifications", payload);
    }

    public void sendToUser(String userId, String destination, Object payload) {
        messagingTemplate.convertAndSend("/user/" + userId + destination, payload);
    }
}
