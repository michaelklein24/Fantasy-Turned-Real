import { Component, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-league-notification-part',
  standalone: true,
  imports: [],
  templateUrl: './league-notification-part.component.html',
  styleUrl: './league-notification-part.component.css'
})
export class LeagueNotificationPartComponent {

  @Output() close: EventEmitter<void> = new EventEmitter<void>();

  public onClose(): void {
    this.close.emit();
  }
}
