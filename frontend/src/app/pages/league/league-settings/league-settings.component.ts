import { Component, Input, OnDestroy, OnInit } from '@angular/core';
import { InvitePanelComponent } from '../../../components/league-settings/invite-panel/invite-panel.component';
import { InviteStatusEntryComponent } from '../../../components/league-settings/invite-panel/invite-status-entry/invite-status-entry.component';
import { InviteUserToLeagueFormComponent } from '../../../forms/invite-user-to-league-form/invite-user-to-league-form.component';

@Component({
  selector: 'app-league-settings',
  standalone: true,
  imports: [
    InvitePanelComponent,
    InviteStatusEntryComponent,
    InviteUserToLeagueFormComponent
  ],
  templateUrl: './league-settings.component.html',
  styleUrl: './league-settings.component.css'
})
export class LeagueSettingsComponent implements OnInit, OnDestroy {

  constructor() {}

  ngOnInit(): void {

  }
  ngOnDestroy(): void {

  }
  


}
