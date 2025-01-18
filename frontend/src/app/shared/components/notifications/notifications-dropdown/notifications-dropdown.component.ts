import { Component, Input } from '@angular/core';
import { NotificationsDropdownHeaderComponent } from '../notifications-dropdown-header/notifications-dropdown-header.component';
import { NotificationsDropdownBodyComponent } from '../notifications-dropdown-body/notifications-dropdown-body.component';
import { Notification } from '../../../../../libs/generated/typescript-angular';

@Component({
  selector: 'app-notifications-dropdown',
  standalone: true,
  imports: [
    NotificationsDropdownHeaderComponent,
    NotificationsDropdownBodyComponent
  ],
  templateUrl: './notifications-dropdown.component.html',
  styleUrl: './notifications-dropdown.component.css'
})
export class NotificationsDropdownComponent {
  @Input() notifications: Notification[] = [];
}
