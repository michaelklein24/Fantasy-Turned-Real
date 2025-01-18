import { Component, Input } from '@angular/core';
import { Notification } from '../../../../../libs/generated/typescript-angular';
import { NotificationItemComponent } from '../notification-item/notification-item.component';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-notifications-dropdown-body',
  standalone: true,
  imports: [
    CommonModule,
    NotificationItemComponent
  ],
  templateUrl: './notifications-dropdown-body.component.html',
  styleUrl: './notifications-dropdown-body.component.css'
})
export class NotificationsDropdownBodyComponent {
  @Input() notifications: Notification[] = [];

}
