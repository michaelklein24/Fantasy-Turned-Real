import { Component } from '@angular/core';
import { ParticipantInviteStatusEntryComponent } from '../../../components/league-settings/participant-invite-status-entry/participant-invite-status-entry.component';

@Component({
  selector: 'app-league-settings',
  standalone: true,
  imports: [
    ParticipantInviteStatusEntryComponent
  ],
  templateUrl: './league-settings.component.html',
  styleUrl: './league-settings.component.css'
})
export class LeagueSettingsComponent {
  
}
