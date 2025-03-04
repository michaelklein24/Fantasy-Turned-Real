import { Component, EventEmitter, Output } from '@angular/core';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-notifications-dropdown-header',
  standalone: true,
  imports: [
    RouterModule
  ],
  templateUrl: './notifications-dropdown-header.component.html',
  styleUrl: './notifications-dropdown-header.component.css'
})
export class NotificationsDropdownHeaderComponent {

  @Output("closeNotificationDropdown") closeNotificationDropdownEventEmitter: EventEmitter<void> = new EventEmitter();

  closeNotificationDropdown() {
    this.closeNotificationDropdownEventEmitter.emit();
  }
}
