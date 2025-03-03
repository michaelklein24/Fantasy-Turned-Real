import { Injectable } from '@angular/core';
import { Client, Message } from '@stomp/stompjs';
import SockJS from 'sockjs-client';
import { NotificationService } from './notification.service';

@Injectable({
  providedIn: 'root',
})
export class WebSocketService {
  private stompClient: Client;

  constructor(
    private notificationService: NotificationService
  ) {
    // Initialize stompClient without webSocketFactory
    this.stompClient = new Client({
      brokerURL: "ws://localhost:8080/ws",
      // The brokerURL should be the STOMP endpoint URL
      webSocketFactory: () => new SockJS('http://localhost:8080/ws'),
      connectHeaders: {},
      reconnectDelay: 5000,
      heartbeatIncoming: 4000,
      heartbeatOutgoing: 4000,
    });
  }

  connect(userId: string): void {
    // Activate the STOMP client connection
    this.stompClient.activate();

    this.stompClient.onConnect = (frame) => {
      console.log('STOMP client connected: ' + frame);

      console.log(userId)
      this.stompClient.subscribe('/user/' + userId + '/notifications', (message: Message) => {
        console.log(message)
        this.handleNotification(message);
      });
    };

    this.stompClient.onStompError = (frame) => {
      console.error('STOMP Error:', frame);
    };
  }

  // Method to handle incoming messages (e.g., notifications)
  private handleNotification(message: Message): void {
    const notification : Notification = JSON.parse(message.body);

    this.notificationService.addNotification(notification);
    console.log('Received notification:', notification);
    // Process the notification (e.g., update UI, show a toast, etc.)
  }

  // Disconnect from WebSocket
  disconnect(): void {
    if (this.stompClient.active) {
      this.stompClient.deactivate();
      console.log('Disconnected from WebSocket');
    }
  }
}
