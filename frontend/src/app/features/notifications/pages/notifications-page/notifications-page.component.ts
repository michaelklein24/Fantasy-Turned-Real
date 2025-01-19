import { Component, OnDestroy, OnInit } from '@angular/core';
import { NotificationsPageHeaderComponent } from "../../components/notifications-page-header/notifications-page-header.component";
import { NotificationService } from '../../../../core/services/notification.service';
import { Notification } from '../../../../../libs/generated/typescript-angular';
import { NotificationItemComponent } from "../../../../shared/components/notifications/notification-item/notification-item.component";
import { CommonModule } from '@angular/common';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-notifications-page',
  standalone: true,
  imports: [
    NotificationsPageHeaderComponent,
    NotificationItemComponent,
    CommonModule
],
  templateUrl: './notifications-page.component.html',
  styleUrl: './notifications-page.component.css'
})
export class NotificationsPageComponent implements OnInit, OnDestroy {

  constructor(
    private notificationService: NotificationService
  ) { }

  filteredNotifications: Notification[] = []
  filteredNotificationSubscription: Subscription | undefined;
  
  ngOnInit(): void {
    this.filteredNotificationSubscription = this.notificationService.filteredNotifications$.subscribe({
      next: (notifications: Notification[]) => {
        this.filteredNotifications = notifications}
    });
    this.notificationService.getNotificationsWithSpecForUser().subscribe()
  }

  ngOnDestroy(): void {
    this.filteredNotificationSubscription?.unsubscribe()
  }

}
