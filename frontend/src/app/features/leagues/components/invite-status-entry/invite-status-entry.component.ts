import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Invite } from '../../../../../libs/generated/typescript-angular';

@Component({
  selector: 'app-invite-status-entry',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './invite-status-entry.component.html',
  styleUrl: './invite-status-entry.component.css'
})
export class InviteStatusEntryComponent  {

  @Input()
  invite: Invite = {}

  constructor() {}

}
