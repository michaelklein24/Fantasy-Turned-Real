import { Component, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-notifications-dropdown-header',
  standalone: true,
  imports: [],
  templateUrl: './notifications-dropdown-header.component.html',
  styleUrl: './notifications-dropdown-header.component.css'
})
export class NotificationsDropdownHeaderComponent {

  @Output("closeNotificationDropdown") closeNotificationDropdownEventEmitter: EventEmitter<void> = new EventEmitter();

  closeNotificationDropdown() {
    this.closeNotificationDropdownEventEmitter.emit();
  }
}
