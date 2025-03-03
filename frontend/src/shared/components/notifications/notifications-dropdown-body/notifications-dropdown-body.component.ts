import { Component, Input } from '@angular/core';
import { Notification } from '../../../../libs/generated/typescript-angular';
import { CommonModule } from '@angular/common';
import { NotificationItemComponent } from '../notification-item/notification-item.component';

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
