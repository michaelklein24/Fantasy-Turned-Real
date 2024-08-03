import { Component, OnDestroy, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { LoginComponent } from './components/auth/login/login.component';
import { RegisterComponent } from './components/auth/register/register.component';
import { LandingPageComponent } from './components/landing-page/landing-page.component';
import { FooterComponent } from './components/screen-parts/footer/footer.component';
import { NavbarComponent } from './components/screen-parts/navbar/navbar.component';
import { SessionService } from './services/session.service';
import { MatDialogModule } from '@angular/material/dialog'; // Import MatDialogModule
import { APIS } from './swagger';


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, LoginComponent, RegisterComponent, LandingPageComponent, NavbarComponent, FooterComponent, MatDialogModule],
  providers: [APIS],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit, OnDestroy {
  title = 'fantasy-turned-real';

  constructor(private sessionService: SessionService) { }

  ngOnInit(): void {

  }

  ngOnDestroy(): void {

  }
}
