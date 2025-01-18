import { Component, OnDestroy, OnInit } from '@angular/core';
import { WebSocketService } from '../../../core/services/web-socket.service';
import { SessionService } from '../../../features/auth/services/session.service';
import { Subscription } from 'rxjs';
import { NotificationService } from '../../../core/services/notification.service';
import { Notification } from '../../../../libs/generated/typescript-angular';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-notifications',
  standalone: true,
  imports: [
    CommonModule
  ],
  templateUrl: './notifications.component.html',
  styleUrls: ['./notifications.component.css']
})
export class NotificationsComponent implements OnInit, OnDestroy {

  private userIdSubscription: Subscription | undefined;
  private notificationsSubscription: Subscription | undefined;
  public notifications: Notification[] = []

  constructor(
    private webSocketService: WebSocketService,
    private sessionService: SessionService,
    private notificationService: NotificationService
  ) { }


  ngOnInit(): void {
    this.userIdSubscription = this.sessionService.userId$.subscribe({
      next: (userId: string | null) => {
        if (userId) {
          this.webSocketService.connect(userId);
          this.notificationService.getNotificationsForUser().subscribe();
          this.notificationsSubscription = this.notificationService.notifications$.subscribe({
            next: (notifications: Notification[]) => {
              this.notifications = notifications}
          });
        }
      },
      error: () => console.log('Unable to pull userId from session. Not attempting to connect to socket for notifications.')
    });
  }

  ngOnDestroy(): void {
    this.userIdSubscription?.unsubscribe();
    this.notificationsSubscription?.unsubscribe();
    this.webSocketService.disconnect();
  }
}
