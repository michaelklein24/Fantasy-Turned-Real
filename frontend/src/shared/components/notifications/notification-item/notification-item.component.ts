import { Component, Input } from '@angular/core';
import {
  Notification,
  NotificationAction,
} from '../../../../libs/generated/typescript-angular';
import { CommonModule } from '@angular/common';
import { ApiService } from '../../../../services/api.service';
import { NotificationService } from '../../../../services/notification.service';


@Component({
  selector: 'app-notification-item',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './notification-item.component.html',
  styleUrl: './notification-item.component.css',
})
export class NotificationItemComponent {
  @Input() notification!: Notification;

  constructor(
    private apiService: ApiService,
    private notificationService: NotificationService
  ) {}

  handleAction(action: NotificationAction) {
    this.apiService
      .performAction(action.endpoint!, action.requestBody!, action.httpMethod!)
      .subscribe({
        next: () => {
          this.notificationService.updateNotificationCompletedActionLabel(
            this.notification.notificationId!,
            action.label! + 'd'
          );
          this.notificationService
            .markNotificationsAsRead(this.notification.notificationId)
            .subscribe();
        },
      });
  }
}
