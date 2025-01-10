import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Invite } from '../../../shared/generated';

@Component({
  selector: 'app-participant-invite-status-entry',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './participant-invite-status-entry.component.html',
  styleUrl: './participant-invite-status-entry.component.css'
})
export class ParticipantInviteStatusEntryComponent  {

  @Input()
  invite: Invite = {}

  constructor() {}

}
