import { Component, HostListener, OnDestroy, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NotificationsDropdownComponent } from './notifications-dropdown/notifications-dropdown.component';
import { Subscription } from 'rxjs';
import { WebSocketService } from '../../../services/web-socket.service';
import { SessionService } from '../../../features/auth/services/session.service';
import { NotificationService } from '../../../services/notification.service';
import { Notification } from '../../../libs/generated/typescript-angular';

@Component({
  selector: 'app-notifications',
  standalone: true,
  imports: [
    CommonModule,
    NotificationsDropdownComponent
  ],
  templateUrl: './notifications.component.html',
  styleUrls: ['./notifications.component.css']
})
export class NotificationsComponent implements OnInit, OnDestroy {

  private userIdSubscription: Subscription | undefined;
  private notificationsSubscription: Subscription | undefined;
  notifications: Notification[] = []
  isNotificationListVisible: boolean = false;


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

  @HostListener('document:click', ['$event'])
  onDocumentClick(event: MouseEvent): void {
    const dropdown = document.querySelector('#notification-dropdown');
    const notificationIcon = document.querySelector('.material-icons-outlined');
    const viewAllButton = document.querySelector('#viewAllNotifications')

    // Check if the click target is outside the dropdown and notification icon
    if (this.isNotificationListVisible && dropdown && 
        !dropdown.contains(event.target as Node) && 
        !notificationIcon?.contains(event.target as Node) ||
        viewAllButton?.contains(event.target as Node)) {
      this.closeNotificationList();
    }
  }

  toggleNotificationList(): void {
    if (this.isNotificationListVisible) {
      this.closeNotificationList();
    } else {
      this.isNotificationListVisible = true;
    }
  }

  closeNotificationList(): void {
    this.notificationService.markNotificationsAsRead().subscribe()
    this.isNotificationListVisible = false;
  }

  getNumberOfUnacknowledgedNotifications(): number {
    return this.notifications.filter((n: Notification) => !n.acknowledged).length
  }
}
