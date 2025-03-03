import { Component, OnDestroy, OnInit } from '@angular/core';
import { InvitePanelComponent } from '../../components/invite-panel/invite-panel.component';

@Component({
  selector: 'app-league-settings-page',
  standalone: true,
  imports: [
    InvitePanelComponent
  ],
  templateUrl: './league-settings-page.component.html',
  styleUrl: './league-settings-page.component.css',
})
export class LeagueSettingsPageComponent implements OnInit, OnDestroy {
  constructor() {}

  ngOnInit(): void {}
  ngOnDestroy(): void {}
}
