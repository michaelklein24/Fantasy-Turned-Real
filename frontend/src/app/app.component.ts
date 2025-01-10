import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { LeagueEntryComponent } from './features/leagues/components/league-entry/league-entry.component';
import { CreateLeagueFormComponent } from './forms/create-league-form/create-league-form.component';
import { LoginUserFormComponent } from './forms/login-user-form/login-user-form.component';
import { RegisterUserFormComponent } from './forms/register-user-form/register-user-form.component';
import { ModalComponent } from './shared/components/modal/modal.component';
import { NavbarComponent } from './shared/components/navbar/navbar.component';
import { ToastComponent } from './shared/components/toast/toast.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    CommonModule,
    RouterOutlet,
    NavbarComponent,
    ToastComponent
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'frontend';
}
