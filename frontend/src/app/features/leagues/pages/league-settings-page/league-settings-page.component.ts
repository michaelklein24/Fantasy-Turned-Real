import { Component, OnDestroy, OnInit } from '@angular/core';
import { InviteUserToLeagueFormComponent } from '../../../../forms/invite-user-to-league-form/invite-user-to-league-form.component';
import { InvitePanelComponent } from '../../components/invite-panel/invite-panel.component';
import { InviteStatusEntryComponent } from '../../components/invite-status-entry/invite-status-entry.component';

@Component({
  selector: 'app-league-settings-page',
  standalone: true,
  imports: [
    InvitePanelComponent
  ],
  templateUrl: './league-settings-page.component.html',
  styleUrl: './league-settings-page.component.css'
})
export class LeagueSettingsPageComponent implements OnInit, OnDestroy {

  constructor() {}

  ngOnInit(): void {

  }
  ngOnDestroy(): void {

  }
  


}
